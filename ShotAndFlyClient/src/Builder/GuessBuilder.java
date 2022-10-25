package Builder;

import Entities.Guess;
import Util.NumberUtil;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

public class GuessBuilder {

    public static Guess buildGuess(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Informe um numero de 3 algarismos para ser o seu palpite");
            int guess = scanner.nextInt();
            if(guess > 99 && guess <= 999)
                return new Guess(guess);

            throw new RuntimeException("Voce deve informar um numero entre 100 e 999 para ser o seu palpite.");
        }catch (IllegalFormatException ex){
            throw new RuntimeException("Você deve informar um número inteiro");
        }
    }
}
