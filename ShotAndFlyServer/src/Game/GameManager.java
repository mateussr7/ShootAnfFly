package Game;

import Communication.CommunicationHandler;
import Entities.ClientAnswer;
import Entities.Player;
import Server.ConnectionManager;
import Enum.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager{

    public ConnectionManager connectionManager;
    public Lobby lobby;

    public GameManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
        this.lobby = Lobby.getInstance();
    }

   public void initGame() throws IOException {
        while(true){
            ClientAnswer clientAnswer = CommunicationHandler.getCommunication(connectionManager).getMessage();


            if(clientAnswer.getMessageType().equals(MessageType.PLAYER_REGISTER)){
                CommunicationHandler.getCommunication(connectionManager).sendMessage(
                        "Player registrado com sucesso",
                        MessageType.PLAYER_REGISTER_SUCCESS
                );

                Player player = new Player(clientAnswer.getValue().toString(), connectionManager);
                choiceGameMode(player);
                break;
            }

            if(clientAnswer.getMessageType().equals(MessageType.END_GAME)){
                break;
            }
        }
   }

   public void choiceGameMode(Player player) throws IOException{
        ConnectionManager playerConnectionManager = player.getConnectionManager();
        while (true){

            CommunicationHandler.getCommunication(player.getConnectionManager()).sendMessage(
                    "Pronto para escolher o modo de jogo",
                    MessageType.READY_CHOICE_GAME_MODE
            );

            ClientAnswer clientAnswer = CommunicationHandler.getCommunication(playerConnectionManager).getMessage();


            if(clientAnswer.getMessageType().equals(MessageType.CHOICE_GAME_MODE)){
                GameMode mode = GameMode.valueOf(clientAnswer.getValue().toString());

                CommunicationHandler.getCommunication(player.getConnectionManager()).sendMessage(
                        "Voce escolheu o modo de jogo com sucesso",
                        MessageType.CHOICE_GAME_MODE_SUCCESS
                );

                startChallenge(mode, player);
                break;
            }
        }
   }

   public void startChallenge(GameMode gameMode, Player player) throws IOException{
        if(gameMode.equals(GameMode.SINGLE_PLAYER)){
            List<Player> players = new ArrayList<>();
            players.add(player);
            GameInstance gameInstance = new GameInstance(players, GameMode.SINGLE_PLAYER);
            Thread thread = new Thread(gameInstance);
            thread.start();
        }else if(gameMode.equals(GameMode.MULTIPLAYER)){

            CommunicationHandler.getCommunication(player.getConnectionManager()).sendMessage(
                    "Você foi adicionado ao Lobby, em breve acharemos alguem para jogar com você",
                    MessageType.ON_LOBBY
            );

            lobby.addPlayer(player);
        }
   }

}
