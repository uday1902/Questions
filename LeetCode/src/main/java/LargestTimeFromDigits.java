package src.main.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LargestTimeFromDigits {
    public String largestTimeFromDigits(int[] A) {
        String result = "";
        Set<Integer> indexSet = new HashSet<>();
        int hour = findHour(A, indexSet);
        if(hour == -1){
            return result;
        }
        int min = findMinute(A, indexSet);
        if(min == -1){
            return result;
        }
        return hour + ":" + min;
    }

    private int findMinute(int[] arr, Set<Integer> indexSet){
        int tenMinuteIndex = getIndex(5, arr, indexSet);
        if(tenMinuteIndex == -1){
            return -1;
        }
        indexSet.add(tenMinuteIndex);
        int minIndex = getIndex(9,arr,indexSet);
        return arr[minIndex] + 10*arr[tenMinuteIndex];
    }

    private int findHour(int[] arr, Set<Integer> indexSet){
        int tenHourIndex = getIndex(2, arr, indexSet);
        int hourIndex = -1;
        if(tenHourIndex == -1){
            return -1;
        }
        indexSet.add(tenHourIndex);
        if(arr[tenHourIndex] == 2){
            hourIndex =  getIndex(3,arr,indexSet) ;
            if(hourIndex == -1){
                indexSet.remove(tenHourIndex);
                tenHourIndex = getIndex(1, arr, indexSet);
                if(tenHourIndex == -1){
                    return -1;
                }
                indexSet.add(tenHourIndex);
            }
        }
        hourIndex = getIndex(9,arr,indexSet);
        indexSet.add(hourIndex);
        return arr[hourIndex] + 10*arr[tenHourIndex];
    }



    private int getIndex(int val, int[] arr, Set<Integer> indexSet){
        int result = -1;
        int max = -1;
        for(int i=0;i<4 ; i++){
            if(!indexSet.contains(i)) {
                if (arr[i] <= val && arr[i] > max){
                    max = arr[i];
                    result = i;
                }
            }
        }
        return result;
    }

    @Test
    public void testProblem() {
        int[] A  = {1,2,3,4};
        Assert.assertEquals("23:41",largestTimeFromDigits(A));
    }
}
