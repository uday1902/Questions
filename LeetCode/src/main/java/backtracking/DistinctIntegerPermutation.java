package src.main.java.backtracking;

import org.junit.Test;

import java.util.*;

public class DistinctIntegerPermutation {

    private int noOfCalls = 0;

    public List<List<Integer>> permute(int[] nums) {
        noOfCalls = 0;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        for(int i : nums){
            arr.add(i);
        }
        permute(result, new ArrayList<Integer>(), arr);
        return result;
    }

    private void permute(List<List<Integer>> result, List<Integer> curr, List<Integer> remaining) {
        noOfCalls++;
        if(remaining.isEmpty()) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for(int k=0; k< remaining.size(); k++) {
            Integer i = remaining.get(k);
            curr.add(i);
            remaining.remove(k);
            permute(result,curr,remaining);
            curr.remove(curr.size() -1);
            remaining.add(k,i);
        }
    }

//    public void backtrack(int n,
//                          ArrayList<Integer> nums,
//                          List<List<Integer>> output,
//                          int first) {
//        noOfCalls++;
//        // if all integers are used up
//        if (first == n)
//            output.add(new ArrayList<Integer>(nums));
//        for (int i = first; i < n; i++) {
//            // place i-th integer first
//            // in the current permutation
//            Collections.swap(nums, first, i);
//            // use next integers to complete the permutations
//            backtrack(n, nums, output, first + 1);
//            // backtrack
//            Collections.swap(nums, first, i);
//        }
//    }
//
//    public List<List<Integer>> permute(int[] nums) {
//        noOfCalls=0;
//        // init output list
//        List<List<Integer>> output = new LinkedList();
//
//        // convert nums into list since the output is a list of lists
//        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
//        for (int num : nums)
//            nums_lst.add(num);
//
//        int n = nums.length;
//        backtrack(n, nums_lst, output, 0);
//        return output;
//    }

    @Test
    public void testPermute() {
        int[] arr = {1,2,3,4};
        List<List<Integer>> result = permute(arr);
        System.out.println("permutations count is " + result.size());
        System.out.println("No Of Permute Calls count is " + noOfCalls);
        System.out.println(result);
    }
}
