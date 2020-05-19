package src.main.java.DataStructures.arrays;

import java.util.Arrays;
import java.util.List;

public class MaxProfitOnce {

    public MaxProfitOnce(){
        List<Integer> input = Arrays.asList(8, 40, 15, 10, 9, 15, 89, 74);
        System.out.println(getMaxProfit(input));
    }

    private long getMaxProfit(List<Integer> prices) {
        if(prices.size() < 2){
            return 0;
        }
        int maxProfit=0;
        int minPrice=prices.get(0);
        for(int i=1;i<prices.size() ; i++){
            if(prices.get(i) < minPrice){
                minPrice = prices.get(i);
            } else if(maxProfit < (prices.get(i) - minPrice)){
                maxProfit = (prices.get(i) - minPrice);
            }
        }
        return maxProfit;
    }


    public static void main(String[] args){
        MaxProfitOnce problem = new MaxProfitOnce();
    }
}
