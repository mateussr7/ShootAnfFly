package Game;

import Builder.GuessBuilder;
import Client.ConnectionHandler;
import Communication.CommunicationHandler;
import Communication.ServerAnswer;
import Entities.Guess;
import Entities.Player;

import java.io.IOException;
import Enum.*;

public class MultiPlayerMode implements Runnable{

    private Player player;
    private ConnectionHandler connectionHandler;


    public MultiPlayerMode(Player player, ConnectionHandler connectionHandler) {
        this.player = player;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void run() {
        try{
            startGame();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void startGame() throws IOException{
        ServerAnswer initGame = CommunicationHandler.getCommunication(connectionHandler).getMessage();
        if(initGame.getMessageType().equals(MessageType.INIT_GAME)){
            while(true){
                ServerAnswer turnAnswer = CommunicationHandler.getCommunication(connectionHandler).getMessage();
                if(!turnAnswer.getMessageType().equals(MessageType.OPPONENT_TURN) && turnAnswer.getMessageType().equals(MessageType.YOUR_TURN)){
                    Guess guess = GuessBuilder.buildGuess();

                    CommunicationHandler.getCommunication(connectionHandler).sendMessage(
                            guess.getValue().toString(),
                            MessageType.GUESS
                    );

                    ServerAnswer guessResult = CommunicationHandler.getCommunication(connectionHandler).getMessage();
                    if(guessResult.getMessageType().equals(MessageType.YOU_WIN)){
                        System.out.printf("Você venceu, parabéns, o número era: %s%n", guessResult.getValue().toString());
                        break;
                    }else if(guessResult.getMessageType().equals(MessageType.RESULT)){
                        System.out.println(guessResult.getValue());
                    }
                }else {

                    if(turnAnswer.getMessageType().equals(MessageType.OPPONENT_TURN)){
                        System.out.println("É a vez do seu adversário jogar");
                    }

                    if(turnAnswer.getMessageType().equals(MessageType.OPPONENT_WIN)){
                        System.out.printf(
                                "Infelizmente seu adversário venceu.\n Boa sorte na próxima.\nO numero correto era %s",
                                turnAnswer.getValue().toString()
                        );
                        break;
                    }
                }
            }
        }
    }
}
