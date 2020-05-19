package src.main.java.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class PowerSetDistinct {

    int helperCount = 0;

    public List<List<Integer>> powerSetDistinct(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<>();
        helperCount = 0;
//        powerSetHelper(input, result, new ArrayList<>(),0);
        powerSetHelper2(input, result, new ArrayList<>());
        System.out.println("Powerset Helper Count " + helperCount);
        return result;
    }

//    private void powerSetHelper(List<Integer> input,List<List<Integer>> result, List<Integer> current, int nextIndex) {
//        helperCount++;
//        result.add(new ArrayList<>(current));
//        for (int i = nextIndex; i < input.size(); i++) {
//            current.add(input.get(i));
//            powerSetHelper(input, result, current, i + 1);
//            current.remove(input.get(i));
//        }
//    }

    private void powerSetHelper2(List<Integer> input,List<List<Integer>> result, List<Integer> current) {
        helperCount++;
        if(input.size() == 0) {
            result.add(new ArrayList<>(current));
        } else {
            int val = input.get(0);
            input.remove(0);


            powerSetHelper2(input, result, current);

            current.add(val);
            powerSetHelper2(input, result, current);

            current.remove(current.size() - 1);
            input.add(0, val);
        }
//        for (int i = nextIndex; i < input.size(); i++) {
//            current.add(input.get(i));
//            powerSetHelper2(input, result, current, i + 1);
//            current.remove(input.get(i));
//        }
    }



    @Test
    public void testPowerSetDistinct() {
        Integer[] arrInput = {1,2,3};
        List<List<Integer>> result = powerSetDistinct(new ArrayList<>(Arrays.asList(arrInput)));
        System.out.println("Powerset Result Size " + result.size());
        //Assert.assertTrue(Double.compare(Math.pow(2,arrInput.length), result.size()) == 0);
        result.stream().forEach(s -> System.out.println(s));
    }
}
