package src.main.java.Strings;

import org.junit.Assert;
import org.junit.Test;

public class Conversion {

    public int stringToInt(String str) {

        int val = 0;
        boolean isNegative = false;
        String input = str;
        if(str == null || str.isEmpty()) {
            return 0;
        } else if(str.charAt(0) == '-') {
            isNegative = true;
            input = str.substring(1);
        }

        for(char ch : input.toCharArray()) {
            val = val*10 +  (ch - '0');
        }
        return isNegative ? -1*val : val;
    }

    public String intToString(int input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        return sb.toString();
    }

    @Test
    public void testStringToInt() {
        Assert.assertEquals(14567, stringToInt("14567"));
        Assert.assertEquals(14567333, stringToInt("14567333"));
        Assert.assertEquals(0, stringToInt(""));
        Assert.assertEquals(0, stringToInt("00"));
        Assert.assertEquals(0, stringToInt(null));
        Assert.assertEquals(-456, stringToInt("-456"));
    }

    @Test
    public void testIntToString() {
        Assert.assertEquals("1456", intToString(1456));
        Assert.assertEquals("-1456", intToString(-1456));
    }
}
