package Game;

import Entities.Player;
import Enum.*;
import Util.NumberUtil;

import java.util.List;

public class Game {

    private int PLAYER_ONE_SELECTED_NUMBER = 0;
    private int PLAYER_TWO_SELECTED_NUMBER = 0;
    private final Player playerOne;
    private final Player playerTwo;

    public Game(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public boolean selectNumber(PlayerType type, int number) throws IllegalArgumentException {
        if(Integer.toString(number).length() != 3)
            throw new IllegalArgumentException("Você precisa inserir um número com exatamente 3 dígitos!");

        if(type.equals(PlayerType.PLAYER_ONE)){
            PLAYER_ONE_SELECTED_NUMBER = number;
        }else {
            PLAYER_TWO_SELECTED_NUMBER = number;
        }
        return true;
    }

    public int getPlayerOneSelectedNumber(){
        return this.PLAYER_ONE_SELECTED_NUMBER;
    }

    public int getPlayerTwoSelectedNumber(){
        return this.PLAYER_TWO_SELECTED_NUMBER;
    }

    public Player getPlayerOne(){
        return this.playerOne;
    }

    public Player getPlayerTwo(){
        return this.playerTwo;
    }

    public GuessResult guess(PlayerType type, int number){
        List<Integer> guessDigits = NumberUtil.getDigits(number);
        List<Integer> targetDigits;

        if(type.equals(PlayerType.PLAYER_ONE))
            targetDigits = NumberUtil.getDigits(PLAYER_TWO_SELECTED_NUMBER);
        else
            targetDigits = NumberUtil.getDigits(PLAYER_ONE_SELECTED_NUMBER);

        if(guessDigits.get(0).equals(targetDigits.get(0))
                && guessDigits.get(1).equals(targetDigits.get(1))
                && guessDigits.get(2).equals(targetDigits.get(2))
        )
            return GuessResult.SQUARE;

        if(targetDigits.contains(guessDigits.get(0))
                && targetDigits.contains(guessDigits.get(1))
                && targetDigits.contains(guessDigits.get(2))
        ){
                if(guessDigits.get(0).equals(targetDigits.get(0))
                        ||guessDigits.get(1).equals(targetDigits.get(1))
                        ||guessDigits.get(2).equals(targetDigits.get(2)))
                    return GuessResult.TWO_SHOTS_ONE_FLY;

                return GuessResult.THREE_SHOTS_ZERO_FLY;
        }

        


        return GuessResult.ZERO_SHOTS_ZERO_FLY;
    }
}
