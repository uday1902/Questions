package src.main.java.DataStructures.arrays;

import java.util.Arrays;
import java.util.List;

public class MaxProfitMultipleTimes {

    public MaxProfitMultipleTimes(){
        List<Long> input = Arrays.asList(10l, 40l, 15l, 10l, 9l, 15l, 89l, 74l);
        System.out.println(getMaxProfit(input));
    }

    public long getMaxProfit(List<Long> input){
        boolean hasShare = false;
        long purchasePrice = 0;
        long profit = 0;
        for(int i =0 ; i < input.size() ; i++){
            if(!hasShare && i < input.size() -1 && input.get(i) < input.get(i+1)){
                    hasShare = true;
                    purchasePrice = input.get(i);

            }else if(hasShare && (i == input.size() -1 || input.get(i) > input.get(i+1))){
                hasShare = false;
                profit += input.get(i) - purchasePrice;
            }
        }
        return profit;
    }

    public static void main(String[] args){
        MaxProfitMultipleTimes problem = new MaxProfitMultipleTimes();
    }
}
