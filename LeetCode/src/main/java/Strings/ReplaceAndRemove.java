package src.main.java.Strings;

import org.junit.Assert;
import org.junit.Test;

/**
 * replace each a with double d.
 * remove every b
 * Constant Space
 * Linear Time
 * */
public class ReplaceAndRemove {

    public void replaceAndRemove(int size, char[] chars) {
        int write = 0;
        int aCount = 0;
        for(int i = 0; i < size ; i++) {
            if(chars[i] != 'b'){
                chars[write++] = chars[i];
            }
            if(chars[i] == 'a') {
                aCount++;
            }
        }
        int finalIndex = write + aCount-1;
        for(int i = write -1; i >= 0; i--) {
            if(chars[i] != 'a') {
                chars[finalIndex--] = chars[i];
            } else {
                chars[finalIndex--] = 'd';
                chars[finalIndex--] = 'd';
            }
        }
    }

    @Test
    public void testReplaceANdRemove() {
        char[] arr = "aabbdddcab   ".toCharArray();
        replaceAndRemove(10, arr);
        Assert.assertEquals("dddddddcdd" , new String(arr));
    }
}
