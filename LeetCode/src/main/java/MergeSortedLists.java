package src.main.java;


//Definition for singly-linked list.

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.util.*;

public class MergeSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode start = null;
        ListNode temp = null;
        if (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                start = l1;
            } else {
                start = l2;
            }
        }
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp = l1;
                l1 = l1.next;
            } else {
                temp = l2;
                l2 = l2.next;
            }
            result = append(result, temp);
        }

        if (l1 == null) {
            appendList(result, l2);
        } else if (l2 == null) {
            appendList(result, l1);
        }
        if (start == null) {
            start = result;
        }
        return start;
    }

    private ListNode append(ListNode result, ListNode node) {
        if (result != null) {
            result.next = node;
        }
        return node;
    }

    private void appendList(ListNode result, ListNode lst) {
        if (result == null) {
            result = lst;
            return;
        }
        result.next = lst;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getList(List<Integer> lst){
        ListNode start = null;
//        if (lst != null || !lst.isEmpty()) {
//            start = new ListNode(lst.get(0));
//        }
        ListNode temp = null;
        for(Integer val : lst){
            if(start == null){
                start = new ListNode(val);
                temp = start;
                continue;
            }
            temp.next = new ListNode(val);
            temp = temp.next;
        }
        return start;
    }

    @Test
    public void testMerge(){
        ListNode l1 =  getList(Arrays.asList(1,2,3));
        ListNode l2 =  getList(Arrays.asList(1,3,4));
        ListNode result = mergeTwoLists(l1,l2);
    }
}
