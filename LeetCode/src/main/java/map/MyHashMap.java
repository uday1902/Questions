package src.main.java.map;

import org.junit.Test;

import java.util.ArrayList;

public class MyHashMap {

    /**
     * Initialize your data structure here.
     */
    //private int defaultCapacity = 16;
    private Node[] hashTable;
    private int noOfBuckets;

    class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public MyHashMap() {
        noOfBuckets = 10000;
        hashTable = new Node[noOfBuckets];
    }

    public int hash(int key) {
        return key % noOfBuckets;
    }


    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hashVal = hash(key);
        Node curr = hashTable[hashVal];
        Node newNode = new Node(key, value);
        if (curr == null) {
            hashTable[hashVal] = newNode;
            return;
        }
        Node prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) {
            prev.next = newNode;
        } else {
            curr.value = value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hashVal = hash(key);
        Node curr = hashTable[hashVal];
        while (curr != null && curr.key != key) {
            curr = curr.next;
        }
        if (curr == null) {
            return -1;
        }
        return curr.value;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hashVal = hash(key);
        Node curr = hashTable[hashVal];
        if (curr == null) {
            return;
        }
        Node prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            return;
        }
        if (prev == null) {
            hashTable[hashVal] = curr.next;
        } else {
            prev.next = curr.next;
        }
    }

    @Test
    public void testMap() {
        MyHashMap mp = new MyHashMap();
        mp.put(1, 10);
        mp.put(2, 20);
        System.out.println(mp.get(1));
        System.out.println(mp.get(2));
        mp.remove(2);
        System.out.println(mp.get(2));
    }

//    @Test
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
