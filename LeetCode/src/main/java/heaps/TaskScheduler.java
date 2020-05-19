package src.main.java.heaps;

import org.junit.Test;

import java.util.*;

public class TaskScheduler {

    private PriorityQueue<Job> pq;
    private Deque<Job> queue;
    private Map<Character,Integer> map;

    class Job implements Comparable<Job>{
        char task;
        int freq;
        Job(char task,int freq){
            this.task = task;
            this.freq = freq;
        }

        @Override
        public int compareTo(Job other) {
            if(this.freq >= other.freq) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public int leastInterval(char[] tasks, int n) {
        queue = new LinkedList<Job>();
        buildMap(tasks);
        buildHeap(map);
        List<Character> result = new ArrayList<>();
        int count = 0;
        while((queue.size() - count != 0) || !pq.isEmpty()) {
            if(pq.isEmpty()){
                result.add(null);
                queue.addFirst(null);
                count++;
            } else {
                Job job = pq.poll();
                result.add(job.task);
                int remainingFreq = map.get(job.task) - 1;
                if(remainingFreq == 0){
                    queue.addFirst(null);
                    count++;
                } else {
                    queue.addFirst(new Job(job.task, remainingFreq));
                }
                map.put(job.task, remainingFreq);
            }
            if(queue.size() == n + 1){
                Job last = queue.removeLast();
                if(last == null) {
                    count--;
                } else if(last != null && map.get(last.task) > 0) {
                    pq.offer(last);
                }
            }
        }
//        for(int i =0 ; i< result.size(); i++) {
//            System.out.println(i+1 + "." + result.get(i));
//        }
        //result.stream().forEach(e -> System.out.print(e + " "));
        return result.size();
    }

    private void buildMap(char[] tasks) {
        map = new HashMap<>();
        for(char ch: tasks) {
            int val = 0;
            if(map.containsKey(ch)) {
                val = map.get(ch);
            }
            map.put(ch, val + 1);
        }
    }

    private void buildHeap(Map<Character,Integer> map) {
        pq = new PriorityQueue<Job>(map.size(), Collections.reverseOrder());
        for(Map.Entry<Character,Integer> ele: map.entrySet()) {
            Job job = new Job(ele.getKey(), ele.getValue());
            pq.offer(job);
        }
    }

    @Test
    public void testLeastInterval() {
        char[] tasks = { 'A','A','A','B','B','B'};
        int result = leastInterval(tasks,50);
        System.out.println(result);
    }
}
