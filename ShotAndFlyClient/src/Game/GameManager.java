package Game;

import Client.ConnectionHandler;
import Communication.CommunicationHandler;
import Communication.ServerAnswer;
import Connection.PlayerManager;
import Entities.NetworkTransferable;
import Entities.Player;

import java.io.IOException;
import java.util.Scanner;

import Enum.*;

public class GameManager {

    private final ConnectionHandler connectionHandler;

    public GameManager(ConnectionHandler connectionHandler){
        this.connectionHandler = connectionHandler;
    }

    public void loginPLayer() throws IOException {
        Player player;
        ServerAnswer serverAnswer;
        while(true){
            player = PlayerManager.getPlayer();
            CommunicationHandler.getCommunication(connectionHandler).sendMessage(
                    Player.networkTransferable(),
                    player,
                    MessageType.PLAYER_REGISTER
            );

            serverAnswer = CommunicationHandler.getCommunication(connectionHandler).getMessage(
                    ServerAnswer.networkTransferable()
            );

            if(!serverAnswer.getMessageType().equals(MessageType.PLAYER_REGISTER_FAILED_NAME_ALREADY_USED))
                break;

            System.out.println("Você escolheu um nome de usuário já existente, tente novamente");
        }

        choiceGameMode(player);

    }

    public void choiceGameMode(Player player) throws IOException{
        while(true){
            GameMode mode = getGameMode();
            CommunicationHandler.getCommunication(connectionHandler).sendMessage(
                    mode.toString(),
                    MessageType.CHOICE_GAME_MODE
            );

            ServerAnswer answer = CommunicationHandler.getCommunication(connectionHandler).getMessage(
                    ServerAnswer.networkTransferable()
            );
            if(answer.getMessageType().equals(MessageType.CHOICE_GAME_MODE_SUCCESS)){
                initGame(mode, player);
                break;
            }
        }
    }

    public GameMode getGameMode(){
        System.out.println("Escolha seu modo de jogo: \n1 - Single Player\n2 - Multi Player\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 2){
            System.out.println("Voce escolheu Multi Player!");
            return GameMode.MULTIPLAYER;
        }

        System.out.println("Voce escolheu Single Player!");
        return GameMode.SINGLE_PLAYER;
    }

    public void initGame(GameMode gameMode, Player player){
        if(gameMode.equals(GameMode.SINGLE_PLAYER)){
            SinglePlayerMode singlePlayerMode = new SinglePlayerMode(player, connectionHandler);
            Thread thread = new Thread(singlePlayerMode);
            thread.start();
        }
    }
}
