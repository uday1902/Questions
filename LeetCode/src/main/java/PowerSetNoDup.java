package src.main.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Incorrect
 * */
public class PowerSetNoDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        Arrays.sort(nums);
        boolean prev = false;
        boolean same = false;
        int old = 0;
        for (int i : nums) {
            if (prev) {
                same = old == i;
            }
            old = i;
            prev = true;
            result.addAll(getNewLists(result, i, same));
        }
        return result;
    }

    public List<List<Integer>> getNewLists(List<List<Integer>> current, int newNumber, boolean same) {
        int currentSize = current.size();
        List<List<Integer>> newLsts = new ArrayList<>();
        for (int i = 0; i < currentSize; i++) {
            List<Integer> lst = current.get(i);
            if (shouldOmit(lst, newNumber, same)) {
                continue;
            }
            List<Integer> newLst = new ArrayList<>(lst);
            newLst.add(newNumber);
            newLsts.add(newLst);
        }
        return newLsts;
    }

    public boolean shouldOmit(List<Integer> lst, int newNumber, boolean same) {
        return same && (lst.size() == 0 || newNumber > lst.get(lst.size() - 1));
    }

    @Test
    public void testSubsetsWithDup() {
        int[] nums = {1, 1, 1};
        List<List<Integer>> result = subsetsWithDup(nums);
        System.out.println(result);
    }
}
