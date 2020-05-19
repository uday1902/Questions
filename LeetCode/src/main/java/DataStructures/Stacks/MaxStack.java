package src.main.java.DataStructures.Stacks;

import org.junit.Test;

import java.util.PriorityQueue;

public class MaxStack {

    private class Node implements Comparable<Node>{
        int val;
        Node next;
        Node prev;

        @Override
        public int compareTo(Node o) {
            if(this.val > o.val){
                return -1;
            } else if( this.val == o.val){
                return -1;
            }
            return 1;
        }
    }

    private PriorityQueue<Node> maxHeap;
    private Node topOfLinkedList;
    public MaxStack() {
        maxHeap = new PriorityQueue<>();
        topOfLinkedList = null;
    }

    public void push(int val) {
        Node n = new Node();
        n.val = val;
        push(n);
    }

    private void push(Node element) {
        if(topOfLinkedList != null) {
            element.next = topOfLinkedList;
            topOfLinkedList.prev = element;
        }
        topOfLinkedList = element;
        maxHeap.offer(element);
    }

    public int pop() {
        Node currTop = null;
        if(topOfLinkedList != null) {
            maxHeap.remove(topOfLinkedList);
            currTop = topOfLinkedList;
            topOfLinkedList = topOfLinkedList.next;
            if(topOfLinkedList != null){
                topOfLinkedList.prev = null;
            }
        }
        return currTop.val;
    }

    public int top() {
        if(topOfLinkedList != null) {
            return topOfLinkedList.val;
        }
        return -1;
    }

    public int peekMax() {
        if(!maxHeap.isEmpty()) {
            return maxHeap.peek().val;
        }
        return -1;
    }

    public int popMax() {
        Node maxEle = null;
        if(maxHeap.isEmpty()){
            return -1;
        }
        maxEle = maxHeap.poll();
        Node prev = maxEle.prev;
        Node next = maxEle.next;
        if(maxEle == topOfLinkedList) {
            topOfLinkedList = next;
        } else {
            prev.next=next;
        }
        if(next != null) {
            next.prev = prev;
        }
        return maxEle.val;
    }

    @Test
    public void testStack() {
        MaxStack mst = new MaxStack();
        mst.push(5);
        mst.push(3);
        mst.push(45);
        mst.push(13);
        mst.push(74);
        mst.push(8);
        mst.push(134);
        mst.push(43);
        System.out.println(mst.top());
        System.out.println(mst.peekMax());
        System.out.println(mst.pop());
        System.out.println(mst.top());
        System.out.println(mst.peekMax());
        System.out.println(mst.pop());
        System.out.println(mst.pop());
        System.out.println(mst.top());
        System.out.println(mst.pop());
        System.out.println(mst.top());
        System.out.println(mst.peekMax());
    }

    @Test
    public void testStack2() {
        MaxStack mst = new MaxStack();
        mst.push(5);
        mst.push(1);
        mst.push(5);
        System.out.println(mst.top());
        System.out.println(mst.popMax());
        System.out.println(mst.top());
        System.out.println(mst.peekMax());
        System.out.println(mst.pop());
        System.out.println(mst.top());
//        System.out.println(mst.pop());
//        System.out.println(mst.topOfLinkedList());
//        System.out.println(mst.peekMax());
    }

    @Test
    public void testStack3() {
        MaxStack mst = new MaxStack();
        mst.push(5);
        System.out.println(mst.peekMax());
        System.out.println(mst.pop());
//        System.out.println(mst.pop());
//        System.out.println(mst.peek());
//        System.out.println(mst.peekMax());
    }
}
