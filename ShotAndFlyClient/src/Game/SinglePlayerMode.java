package Game;

import Builder.GuessBuilder;
import Client.ConnectionHandler;
import Communication.CommunicationHandler;
import Communication.ServerAnswer;
import Entities.Guess;
import Entities.Player;

import java.io.IOException;

import Enum.MessageType;

public class SinglePlayerMode implements Runnable{

    private Player player;
    private final ConnectionHandler connectionHandler;

    public SinglePlayerMode(Player player, ConnectionHandler connectionHandler){
        this.player = player;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void run() {
        try {
            singlePLayerGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void singlePLayerGame() throws IOException {
        ServerAnswer initGameAnswer = CommunicationHandler.getCommunication(connectionHandler).getMessage();
        if(initGameAnswer.getMessageType().equals(MessageType.INIT_GAME)){
            while(true){
                ServerAnswer answer = CommunicationHandler.getCommunication(connectionHandler).getMessage();

                if(answer.getMessageType().equals(MessageType.YOUR_TURN)){
                    Guess guess = GuessBuilder.buildGuess();

                    CommunicationHandler.getCommunication(connectionHandler).sendMessage(
                            guess.getValue().toString(),
                            MessageType.GUESS
                    );

                    ServerAnswer guessResult = CommunicationHandler.getCommunication(connectionHandler).getMessage();

                    if(guessResult.getMessageType().equals(MessageType.YOU_WIN)){
                        System.out.println("Parabéns, voce acertou!");
                        break;
                    }else if(guessResult.getMessageType().equals(MessageType.RESULT)){
                        String message = guessResult.getValue().toString();
                        System.out.println(message);
                        System.out.println("Tente novamente");
                    }

                }
            }
        }
    }
}
