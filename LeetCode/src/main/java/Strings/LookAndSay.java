package src.main.java.Strings;


import org.junit.Assert;
import org.junit.Test;

/**
 * The look-and-say sequence starts with 1. Subsequent numbers are derived by
 * describing the previous number in terms of consecutive digits.
 * Specifically, to generate an entry of the sequence from the previous entry,
 * read off the digits of the previ¬ ous entry, counting the number of digits in groups of the same digit.
 * For exam¬ ple, 1; one 1; two Is; one 2 then one 1; one 1, then one 2, then two Is; three Is, then two 2s, then one 1.
 * The first eight numbers in the look-and-say sequence are <1,11, 21,1211,111221,312211,13112221,1113213211>.
 * Write a program that takes as input an integer n and returns the nth integer in the look-and-say sequence.
 * Return the result as a string.
 * */
public class LookAndSay {

    public String getNthNumber(int n) {
        String curr = "1";
        for(int i = 1; i < n ; i++){
            curr = getNextInSeq(curr);
        }
        return curr;
    }

    private String getNextInSeq(String prev) {
        String result = "";
        int count = 0;
        char prevChar = prev.charAt(0);
        for( char ch : prev.toCharArray()){
            if(prevChar == ch) {
                count++;
            } else {
                result += "" + count + prevChar;
                count = 1;
            }
            prevChar = ch;
        }
        result += "" + count + prevChar;
        return result;
    }

    @Test
    public void testGetNthNumber() {
        Assert.assertEquals("11", getNthNumber(2));
        Assert.assertEquals("21", getNthNumber(3));
        Assert.assertEquals("1211", getNthNumber(4));
        Assert.assertEquals("111221", getNthNumber(5));
        Assert.assertEquals("312211", getNthNumber(6));
        Assert.assertEquals("13112221", getNthNumber(7));
        Assert.assertEquals("1113213211", getNthNumber(8));
    }
}
