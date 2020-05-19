package src.main.java.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DiceRoll {

    int diceRollHelperCount = 0;

    public List<List<Integer>> rollDice(int diceCount){
        List<List<Integer>> result = new ArrayList<>();
        diceRollHelperCount = 0;
        rollDiceHelper(diceCount, result, new ArrayList<>());
        System.out.println("Dice Roll Helper Count " + diceRollHelperCount);
        return result;
    }

    private void rollDiceHelper(int diceCount, List<List<Integer>> possibilities, List<Integer> current){
        diceRollHelperCount++;
        if(diceCount == 0) {
            possibilities.add(new ArrayList<>(current));
//            return;
        } else {
            for(int i=1; i<=6;i++) {
                //choose
                current.add(i);
                // Explore
                rollDiceHelper(diceCount-1, possibilities, current);
                //unchoose
                current.remove(current.size() -1);
            }
        }
    }

    @Test
    public void testRollDice() {
        int numberOfDice = 3;
        List<List<Integer>> result = rollDice(numberOfDice);
        System.out.println("Count of Possibilities " + result.size());
        Assert.assertTrue(Double.compare(Math.pow(6,numberOfDice), result.size()) == 0);
        //result.stream().forEach(set -> System.out.println(set.toString()));
    }
}
