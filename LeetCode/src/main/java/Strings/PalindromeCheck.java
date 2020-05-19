package src.main.java.Strings;


import org.junit.Assert;
import org.junit.Test;

/**
 * input a String
 * Check if Palindrome ignoring nonalphanumeric characters
 * Ignore case check as well
 * */
public class PalindromeCheck {

    public boolean isPalindrome(String line) {
        int start = 0;
        int end = line.length() - 1;
        while (start < end) {
            start = getNextLetterOrDigit(line, start, 1);
            end = getNextLetterOrDigit(line, end, -1);
            if (start >= end) {
                return true;
            }
            if (!isSameChar(line.charAt(start++), line.charAt(end--))) {
                return false;
            }
        }
        return true;
    }

    private int getNextLetterOrDigit(String line, int currIndex, int change) {
        while (line.length() > currIndex && currIndex >= 0 && !Character.isLetterOrDigit(line.charAt(currIndex))) {
            currIndex += change;
        }
        return currIndex;
    }

    private boolean isSameChar(char ch1, char ch2) {
        return Character.toLowerCase(ch1) == Character.toLowerCase(ch2);
    }

    @Test
    public void testIsPalindrome() {
        Assert.assertEquals(false, isPalindrome("babai"));
        Assert.assertEquals(false, isPalindrome("baba"));
        Assert.assertEquals(true, isPalindrome("bacab"));
        Assert.assertEquals(true, isPalindrome("A man, a plan, a canal, Panama."));
        Assert.assertEquals(true, isPalindrome("Able was I, ere I saw Elba!"));
        Assert.assertEquals(false, isPalindrome("Ray a Ray"));
        Assert.assertEquals(true, isPalindrome(".,"));
        Assert.assertEquals(false, isPalindrome("ab"));
    }
}
