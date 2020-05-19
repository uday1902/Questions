package src.main.java.DataStructures.arrays;

import org.junit.Test;

public class MountainArray {

    public int peakIndexInMountainArray(int[] A) {
        int min = 0;
        int max = A.length - 1;
        while (min < max) {
            int mid = (max + min) / 2;
            if (A[mid] > A[mid + 1]) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

//    public int peakIndexInMountainArray(int[] A) {
//        int min = 0;
//        int max = A.length -1;
//        while(max - min > 4){
//            int[] B = findTiles(A,min,max);
//            int[] C = findMinMax(B, A);
//            min = C[0];
//            max = C[1];
//        }
//        return findMax(A, min, max);
//    }
//
//    private int[] findTiles(int[] A, int min, int max){
//        int[] tiles = new int[5];
//        tiles[0] = min;
//        tiles[4] = max;
//        tiles[2] = (min + max)/2;
//        tiles[1] = (min*3 + max) / 4;
//        tiles[3] = (min + max*3) / 4;
//        return tiles;
//    }
//
//    private int[] findMinMax(int[] B, int[] A){
//        int[] minMax = new int[2];
//        if(A[B[1]] <= A[B[2]] && A[B[2]] >= A[B[3]]){
//            minMax[0] = B[1];
//            minMax[1] = B[3];
//        } else if(A[B[1]] > A[B[2]]){
//            minMax[0] = B[0];
//            minMax[1] = B[2];
//        } else if (A[B[2]] < A[B[3]]){
//            minMax[0] = B[2];
//            minMax[1] = B[4];
//        }
//        return minMax;
//    }
//
//    private int findMax(int[] A, int min, int max){
//        int maxV = min;
//        for(int i=min+1;i <= max; i++ ){
//            if(A[maxV] < A[i]){
//                maxV = i;
//            }
//        }
//        return maxV;
//    }

    @Test
    public void testPeakIndexInMountainArray(){
        int[] arr = {8,18,24,31,37,42,43,56,65,73,93,98,100,98,76,72,69,24,23};
        System.out.println(peakIndexInMountainArray(arr));
    }
}
