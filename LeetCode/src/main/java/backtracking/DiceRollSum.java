package src.main.java.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DiceRollSum {

    int diceRollHelperCount = 0;

    public List<List<Integer>> rollDiceSum(int diceCount, int desiredSum){
        List<List<Integer>> result = new ArrayList<>();
        diceRollHelperCount = 0;
        rollDiceHelper(diceCount, result, new ArrayList<>(),desiredSum, 0 );
        System.out.println("Dice Roll Helper Count " + diceRollHelperCount);
        return result;
    }

    private void rollDiceHelper(int diceCount, List<List<Integer>> possibilities, List<Integer> current,int sum, int currentSum){
        diceRollHelperCount++;
        if(diceCount == 0) {
            possibilities.add(new ArrayList<>(current));
            currentSum = 0;
//            return;
        } else {
            for(int i=1; i<=6;i++) {
                if(currentSum + i + (diceCount-1)*1 <= sum && currentSum + i + (diceCount-1)*6 >= sum) {
                    //choose
                    current.add(i);
                    // Explore
                    rollDiceHelper(diceCount - 1, possibilities, current, sum, currentSum + i);
                    //unchoose
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    @Test
    public void testRollDice() {
        int numberOfDice = 3;
        List<List<Integer>> result = rollDiceSum(numberOfDice, 9);
        System.out.println("Count of Possibilities " + result.size());
        //Assert.assertTrue(Double.compare(Math.pow(6,numberOfDice), result.size()) == 0);
        result.stream().forEach(set -> System.out.println(set.toString()));
    }
}
