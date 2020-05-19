package src.main.java.DataStructures.arrays;

import org.junit.Test;

public class MaximumSwap {

    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }

//    public int maximumSwap(int num) {
//        if(num < 10 ) {
//            return num;
//        }
//        int maxIndex=0;
//        int max = -1;
//        int curr = num;
//        int index = 0;
//        while(curr > 0) {
//            if(max < curr%10) {
//                max = curr%10;
//                maxIndex = index;
//            }
//            index++;
//            curr = curr/10;
//        }
//        curr = Double.valueOf(num/Math.pow(10,maxIndex)).intValue();
//        int rep = Integer.MAX_VALUE;
//        int repIndex = maxIndex;
//        index = maxIndex;
//        while(curr > 0) {
//            if(max > curr%10) {
//                rep = curr%10;
//                repIndex = index;
//            }
//            index++;
//            curr = curr/10;
//        }
//        return swap(num, maxIndex, max, repIndex, rep);
//    }

    // private List<Integer> numToList(int num) {
    //     if(num == 0) return Collections.singleton(0);
    //     List<Integer> lst = new ArrayList<>();
    //     while(num > 0) {
    //         lst.add(num%10);
    //         num = num/10;
    //     }
    //     return lst;
    // }

//    private int indexValue(int num, int index) {
//        return Double.valueOf((num - num%Math.pow(10,index))/Math.pow(10,index)).intValue()%10;
//    }

//    private int swap(int num, int maxIndex, int max, int repIndex, int rep) {
//        return Double.valueOf(num - max*Math.pow(10,maxIndex) + max*Math.pow(10,repIndex)
//                - rep*Math.pow(10,repIndex) + rep*Math.pow(10,maxIndex)).intValue();
//    }


    @Test
    public void testMaximumSwap() {
        int result = maximumSwap(99890);
        System.out.println(result);
    }
}
