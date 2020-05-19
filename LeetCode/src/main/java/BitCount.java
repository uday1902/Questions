package src.main.java;

import org.junit.Assert;
import org.junit.Test;

public class BitCount {

    public short countBits(int x) {
        short numBits = 0;
        while (x > 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    public short parity(long x){
        short result = 0;
        while (x != 0) {
            result++;
            x &= (x-1);
        }
        return result;
    }

    @Test
    public void testParity(){
        Assert.assertEquals(4,parity(15));
        Assert.assertEquals(4,parity(30));
    }

    @Test
    public void testSolution(){
        BitCount problem = new BitCount();
        Assert.assertEquals(4, problem.countBits(15));
        Assert.assertEquals(6, problem.countBits(175));
    }
}
