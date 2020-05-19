package src.main.java.map;

import org.junit.Test;

public class CustomMap {
    public static void main(String[] args) {
//     ArrayList<String> strings = new ArrayList<String>();
//     strings.add("Hello, World!");
//     strings.add("Welcome to CoderPad.");
//     strings.add("This pad is running Java " + Runtime.version().feature());

//     for (String string : strings) {
//       System.out.println(string);
//     }
    }

    class Node {
        Integer key;
        Integer value;
        Node next;
    }

    private Node[] hashTable;
    private int LARGE_PRIME = 101;
    private long LIST_THRESHOLD = 10;

    public CustomMap() {
        hashTable = new Node[LARGE_PRIME];
    }

    private int hash(Integer key) {
        int hashValue = key % LARGE_PRIME;
        return hashValue;
    }

    private Integer get(Integer key) {
        int hashValue = hash(key);
        Node entry = hashTable[hashValue];
        while (entry != null && entry.key.compareTo(key) != 0) {
            entry = entry.next;
        }
        if (entry == null) {
            return -1;
        }
        return entry.value;
    }

    private void put(Integer key, Integer value) {
        int hashValue = hash(key);
        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;
        Node entry = hashTable[hashValue];
        if (entry == null) {
            hashTable[hashValue] = newNode;
            return;
        }
        Node prev = entry;
        while (entry != null && entry.key != key) {
            prev = entry;
            entry = entry.next;
        }
        if (entry != null) {
            entry.value = value;
        } else {
            prev.next = newNode;
        }
    }

    @Test
    public void testMap() {
        CustomMap map = new CustomMap();
        map.put(101, 101);
        map.put(202, 201);
        map.put(303, 305);
        System.out.println(map.get(101));
        System.out.println(map.get(202));
        System.out.println(map.get(303));
    }
}
