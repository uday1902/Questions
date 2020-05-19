package src.main.java.DataStructures.arrays;

import org.junit.Test;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (allSame(nums)) {
            return;
        }
        if (isReverse(nums)) {
            invert(nums, 0, nums.length - 1);
            return;
        }
        for (int curr = nums.length - 1; curr >= 1; curr--) {
            if (nums[curr-1] < nums[curr]) {
                sortAndSwap(nums, curr-1, nums.length - 1);
                return;
            }
        }
    }

    public boolean allSame(int[] nums) {
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (temp != nums[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isReverse(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void invert(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            swapOne(nums, i, end--);
        }
    }

    private void sortAndSwap(int[] nums, int start, int end) {
        if (start == end - 1) {
            swapOne(nums, start, start + 1);
            return;
        }
        int index = findPosition(nums, start, end);
        swapOne(nums, start, index);
        invert(nums, start + 1, end);
    }

    private int findPosition(int[] nums, int start, int end) {
        for(int i= end; i> start ; i--) {
            if(nums[i] > nums[start]) {
                return i;
            }
        }
        return start+1;
    }

    private void swapOne(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    @Test
    public void testNextPermutation() {
        int[] nums = {2,3,1};
        Arrays.asList(nums).stream()
                .forEach(ele -> System.out.print(ele + " "));
        nextPermutation(nums);
        Arrays.asList(nums).stream()
                .forEach(ele -> System.out.print(ele + " "));
    }
}
