package src.main.java.heaps;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Freq> freqMap = getFreqMap(nums);
        PriorityQueue<Freq> qu = new PriorityQueue<>();
        for (Map.Entry<Integer, Freq> entry : freqMap.entrySet()) {
            if (qu.size() < k) {
                qu.offer(entry.getValue());
            } else if (qu.size() == k && entry.getValue().frq > qu.peek().frq) {
                qu.poll();
                qu.offer(entry.getValue());
            }
        }

        return qu.stream()
                .map(ele -> ele.val)
                .collect(Collectors.toList());
    }

    class Freq implements Comparable<Freq> {
        int val;
        int frq;

        Freq(int val, int frq) {
            this.val = val;
            this.frq = frq;
        }

        @Override
        public int compareTo(Freq other) {
            return this.frq - other.frq;
        }
    }

    public Map<Integer, Freq> getFreqMap(int[] nums) {
        Map<Integer, Freq> freqMap = new HashMap<>();
        for (int i : nums) {
            int fr = 1;
            if (freqMap.containsKey(i)) {
                fr = freqMap.get(i).frq + 1;
            }
            freqMap.put(i, new Freq(i, fr));
        }
        return freqMap;
    }

    @Test
    public void testTopK() {
        int[] nums = {4,1,-1,2,-1,2,3};
        List<Integer> result = topKFrequent(nums, 2);
        Assert.assertEquals(true, result.contains(-1));
        Assert.assertEquals(true, result.contains(2));
        Assert.assertEquals(2, result.size());
    }
}
