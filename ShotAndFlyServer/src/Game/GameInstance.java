package Game;

import Communication.CommunicationHandler;
import Entities.ClientAnswer;
import Entities.Player;
import Entities.PlayerQueue;
import Enum.*;
import Server.ConnectionManager;
import Util.NumberUtil;

import java.io.IOException;
import java.util.List;

public class GameInstance implements Runnable{
    private PlayerQueue playerQueue;
    private GameMode gameMode;
    private int targetNumber;
    private List<Integer> targetDigits;

    public GameInstance(List<Player> players, GameMode gameMode){
        this.playerQueue = new PlayerQueue(players);
        this.gameMode = gameMode;
        this.targetNumber = NumberUtil.generateNumber();
        this.targetDigits = NumberUtil.getDigits(targetNumber);

        System.out.println(targetNumber);
    }

    @Override
    public void run() {
        try{
            startGame();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startGame() throws IOException {
        messageAll(MessageType.INIT_GAME, "O número alvo foi definido, o jogo irá começar. Boa sorte!");
        while (true){
            Player turnPlayer = playerQueue.next();
            ConnectionManager connectionManager = turnPlayer.getConnectionManager();

            for(Player p : playerQueue.toList()){
                if(!p.equals(turnPlayer)){
                    CommunicationHandler.getCommunication(p.getConnectionManager()).sendMessage(
                            "Vez do adversário",
                            MessageType.OPPONENT_TURN
                    );
                }
            }

            CommunicationHandler.getCommunication(connectionManager).sendMessage(
                    "Sua vez de jogar",
                    MessageType.YOUR_TURN
            );

            ClientAnswer turnPlayerGuess = CommunicationHandler.getCommunication(connectionManager).getMessage();

            if(turnPlayerGuess.getMessageType().equals(MessageType.GUESS)){
                int guess = Integer.parseInt((String) turnPlayerGuess.getValue());
                List<Integer> guessDigits = NumberUtil.getDigits(guess);

                int shots = NumberUtil.getTotalCorrectNumbersInWrongPositions(guessDigits, targetDigits);
                int fly = NumberUtil.getTotalInCorrectPositions(guessDigits, targetDigits);

                if(fly == 3){
                    CommunicationHandler.getCommunication(connectionManager).sendMessage(
                            String.valueOf(targetNumber),
                            MessageType.YOU_WIN
                    );

                    for(Player p : playerQueue.toList()){
                        if(!p.equals(turnPlayer)){
                            CommunicationHandler.getCommunication(p.getConnectionManager()).sendMessage(
                                    "Seu adversário ganhou",
                                    MessageType.OPPONENT_WIN
                            );
                        }
                    }
                    break;
                }else {
                    CommunicationHandler.getCommunication(connectionManager).sendMessage(
                            String.format("%d Tiro(s) e %d Mosca(s)", shots, fly),
                            MessageType.RESULT
                    );
                }
            }
        }
    }

    private void messageAll(MessageType messageType, String message) throws IOException {
        List<Player> playersToMessage = playerQueue.toList();

        for(Player p : playersToMessage){
            CommunicationHandler.getCommunication(p.getConnectionManager()).sendMessage(message, messageType);
        }
    }
}
