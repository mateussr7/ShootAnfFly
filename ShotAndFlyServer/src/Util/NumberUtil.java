package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberUtil {

    public static List<Integer> getDigits(int number){
        List<Integer> digits = new ArrayList<>();

        digits.add(number/100);
        digits.add((number%100)/10);
        digits.add((number%100)%10);

        return digits;
    }

    public static int generateNumber(){
        int first = new Random().nextInt(10);
        int second = new Random().nextInt(10);
        int third = new Random().nextInt(10);

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);

        return (first*100)+(second*10)+third;
    }
    
    public static int getTotalInCorrectPositions(List<Integer> guess, List<Integer> target){
        int total = 0;
        for(int i = 0; i < 3; i++)
            if(guess.get(i).equals(target.get(i)))
                total++;

        return total;
    }

    public static int getTotalCorrectNumbersInWrongPositions(List<Integer> guess, List<Integer> target){
        int total = 0;
        for(int i = 0; i < 3; i++)
            if(!guess.get(i).equals(target.get(i)) && target.contains(guess.get(i)))
                total++;

        return total;
    }
}
