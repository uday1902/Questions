package src.main.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MergeIntervals {
    /**
     * Definition for an interval.
     */
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() <= 1){
            return intervals;
        }
        Collections.sort(intervals, new EndComparator());
        Iterator<Interval> iter = intervals.iterator();
        Interval prev = iter.next();
        for(; iter.hasNext();){
            Interval currInterval = iter.next();
            if(currInterval.start <= prev.end){
                prev.end = maxi(currInterval.end, prev.end);
                iter.remove();
            } else {
                prev = currInterval;
            }
        }
        return intervals;
    }

    private int maxi(int a , int b){
        return a > b ? a:b;
    }

    private class EndComparator implements Comparator<Interval>{
        @Override
        public int compare(Interval a, Interval b){
            if(a.start < b.start) {
                return -1;
            } else if(a.start > b.start){
                return 1;
            }
            return 0;
        }
    }

    @Test
    public void testMergeIntervals1(){
        List<Interval> intervals= new ArrayList<>();
        intervals.add(new Interval(1,4));
        intervals.add(new Interval(0,2));
        intervals.add(new Interval(3,5));
        //intervals.stream().forEach(it -> {System.out.println(it.start +" "+it.end);});
        merge(intervals).stream().forEach(it -> {System.out.println(it.start +" "+it.end);});
    }

    @Test
    public void testMergeIntervals2(){
        List<Interval> intervals= new ArrayList<>();
        intervals.add(new Interval(2,3));
        intervals.add(new Interval(2,2));
        intervals.add(new Interval(3,3));
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(5,7));
        intervals.add(new Interval(2,2));
        intervals.add(new Interval(4,6));
        //intervals.stream().forEach(it -> {System.out.println(it.start +" "+it.end);});
        merge(intervals).stream().forEach(it -> {System.out.println(it.start +" "+it.end);});
    }
}