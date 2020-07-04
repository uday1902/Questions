package src.main.java.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class PowerSet {

    int helperCount = 0;

//    public List<List<Integer>> powerSet(List<Integer> input) {
//        List<List<Integer>> result = new ArrayList<>();
//        helperCount = 0;
//        Collections.sort(input);
//        powerSetHelper(input , result, new ArrayList<>(),0);
//        System.out.println("Powerset Helper Count " + helperCount);
//        return result;
//    }
//
//    private void powerSetHelper(List<Integer> input,List<List<Integer>> result, List<Integer> current, int nextIndex) {
//        helperCount++;
//        result.add(new ArrayList<>(current));
//        for (int i = nextIndex; i < input.size(); i++) {
//            if( i> 0 && input.get(i) == input.get(i-1) && nextIndex < i){
//                continue;
//            }
//            current.add(input.get(i));
//            powerSetHelper(input, result, current, i + 1);
//            current.remove(input.get(i));
//        }
//    }

    public List<List<Integer>> powerSet(List<Integer> input) {
        //helperCount = 0;
        int count, j;
        int len = input.size();
        Set<List<Integer>> ans = new HashSet<>();
        for (count = 0; count < Math.pow(2, len); count++) {
            List<Integer> s = new ArrayList<>();
            for(j = 0; j < len; j++) {
                //check jth bit is set. if yes, select it
                //helperCount++;
                if((count & (1 << j)) > 0) {
                    //System.out.print(input.get(j) + " ");
                    s.add(input.get(j));
                }

            }
            //System.out.println();
            ans.add(s);
            //System.out.println("Ans:" + ans);

        }
//        System.out.println(ans.size());
//        for(List<Integer> s: ans) {
//            System.out.println(s);
//        }
        return new ArrayList<>(ans);
    }


    @Test
    public void testPowerSet() {
        Integer[] arrInput = {3,2,5,1,4,55,66,22};
        List<List<Integer>> result = powerSet(Arrays.asList(arrInput));
        System.out.println("Powerset Result Size " + result.size());
        System.out.println("Powerset Helper Count " + helperCount);
        //Assert.assertTrue(Double.compare(Math.pow(2,arrInput.length), result.size()) == 0);
        result.stream().forEach(s -> System.out.println(s));
    }
}
