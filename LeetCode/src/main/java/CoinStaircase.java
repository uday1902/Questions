package src.main.java;

import org.junit.Assert;
import org.junit.Test;

public class CoinStaircase {

    public int arrangeCoins(int n) {
        long nf = 2*n;
        int sq = (int)Math.sqrt(nf) + 1;
        System.out.println("SQRT is " + sq);
        for(int i=sq; i >= 0 ; i--){
            if(i*(i+1)/2 <= n){
                return i;
            }
        }
        return 0;
    }

    @Test
    public void testArrangeCoins(){
        int n = 1804289383;
        Assert.assertEquals(345, arrangeCoins(n));
    }
}
