package Game;

import Communication.CommunicationHandler;
import Entities.ClientAnswer;
import Entities.Player;
import Server.ConnectionManager;
import Util.NumberUtil;

import Enum.*;

import java.io.IOException;
import java.util.List;

public class SinglePlayerGameMode implements Runnable{
    private Integer targetNumber = 123;
    private Player player;

    public SinglePlayerGameMode(Player playerr){
        this.player = player;
    }

    @Override
    public void run() {
        try{
            runSinglePlayerMode();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Integer getTargetNumber(){
        return targetNumber;
    }

    public void runSinglePlayerMode() throws IOException{
        //defineGuessNumber();
        while(true) {
            CommunicationHandler.getCommunication(player.getConnectionManager()).sendMessage(
                    "Sua vez de jogar",
                    MessageType.YOUR_TURN
            );

            System.out.println("Enviado para o cliente");

            ClientAnswer guess = CommunicationHandler.getCommunication(player.getConnectionManager()).getMessage();

            System.out.println("a");

            System.out.println(guess.getMessageType());
            System.out.println(guess.getValue());

            if(guess.getMessageType().equals(MessageType.GUESS)){
                int playerGuess = Integer.parseInt((String) guess.getValue());

                List<Integer> targetDigits = NumberUtil.getDigits(targetNumber);
                List<Integer> guessDigits = NumberUtil.getDigits(playerGuess);

                int shots = NumberUtil.getTotalCorrectNumbersInWrongPositions(
                        guessDigits,
                        targetDigits
                );

                int fly = NumberUtil.getTotalInCorrectPositions(
                        guessDigits,
                        targetDigits
                );

                if(fly == 3){
                    CommunicationHandler.getCommunication(player.getConnectionManager()).sendMessage(
                            "Voce venceu, o número era: " + targetNumber,
                            MessageType.YOU_WIN
                    );
                }
            }
        }
    }

    private void defineGuessNumber(){
        this.targetNumber = NumberUtil.generateNumber();
        System.out.println(targetNumber);
    }


}
