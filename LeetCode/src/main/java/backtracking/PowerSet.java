package src.main.java.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PowerSet {

    int helperCount = 0;

    public List<List<Integer>> powerSet(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<>();
        helperCount = 0;
        Collections.sort(input);
        powerSetHelper(input , result, new ArrayList<>(),0);
        System.out.println("Powerset Helper Count " + helperCount);
        return result;
    }

    private void powerSetHelper(List<Integer> input,List<List<Integer>> result, List<Integer> current, int nextIndex) {
        helperCount++;
        result.add(new ArrayList<>(current));
        for (int i = nextIndex; i < input.size(); i++) {
            if( i> 0 && input.get(i) == input.get(i-1) && nextIndex < i){
                continue;
            }
            current.add(input.get(i));
            powerSetHelper(input, result, current, i + 1);
            current.remove(input.get(i));
        }
    }

    @Test
    public void testPowerSet() {
        Integer[] arrInput = {3,2,2,1};
        List<List<Integer>> result = powerSet(Arrays.asList(arrInput));
        System.out.println("Powerset Result Size " + result.size());
        //Assert.assertTrue(Double.compare(Math.pow(2,arrInput.length), result.size()) == 0);
        result.stream().forEach(s -> System.out.println(s));
    }
}
