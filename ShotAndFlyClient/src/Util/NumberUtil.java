package Util;

import java.util.ArrayList;
import java.util.List;

public class NumberUtil {
    public static List<Integer> getDigits(int number){
        List<Integer> digits = new ArrayList<>();

        digits.add(number/100);
        digits.add((number%100)/10);
        digits.add((number%100)%10);

        return digits;
    }
}
