package src.main.java.Strings;

import org.junit.Assert;
import org.junit.Test;

public class BaseConversion {

    public String convertBase(String numString, int base1, int base2) {
        int val = convertFromBase(numString, base1);
        return convertIntToBase(val, base2);
    }

    private int convertFromBase(String numString, int base) {
        int intValue = 0;
        for(char ch: numString.toCharArray()) {
            if(Character.isDigit(ch)) {
                intValue = intValue*base + ch - '0';
            } else {
                intValue = intValue*base + ch - 'A';
            }
        }
        return intValue;
    }

    private String convertIntToBase(int input, int base) {
        String result = "";
        int curr = input;
        while(curr > 0) {
            int rem = curr%base;
            if(rem < 10){
                result = rem + result;
            } else {
                result = (char)(rem - 10 + 'A') + result;
            }
            curr = curr/base;
        }
        return result;
    }

    @Test
    public void testConvertBase() {
        Assert.assertEquals("3F",convertBase("77", 8, 16));
        Assert.assertEquals("1A7",convertBase("615", 7, 13));
//        Assert.assertEquals("3F",convertBase("77", 8, 16));
    }
}
