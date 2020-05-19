package src.main.java;

import org.junit.Assert;
import org.junit.Test;

public class ThirdMax {

    public int thirdMax(int[] nums) {
        int max = nums[0];
        int max2 = Integer.MIN_VALUE;
        boolean set2 = false;
        int max3 = Integer.MIN_VALUE;
        int set3 = 0;
        for( int i=1; i < nums.length ; i++) {
            int element = nums[i];
            if(element == max || element == max2){
                continue;
            }
            if(element > max) {
                max3 = max2;
                set3++;
                max2=max;
                set2 = true;
                max = element;
            } else if(element > max2){
                max3 = max2;
                set3++;
                set2 = true;
                max2 = element;
            } else if(element > max3){
                max3 = element;
                set3+=2;
            }
        }
        return (set3 >=2 ? max3 : max);
    }

    @Test
    public void thirdMax() {
        Assert.assertEquals(1, thirdMax(new int[]{3,2,1}));
        Assert.assertEquals(2, thirdMax(new int[]{1,2}));
        Assert.assertEquals(2, thirdMax(new int[]{2,1}));
        Assert.assertEquals(1, thirdMax(new int[]{2,2,3,1}));
    }
}
