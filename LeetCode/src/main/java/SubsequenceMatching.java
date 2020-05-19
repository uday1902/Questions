package src.main.java;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SubsequenceMatching {

    public Set<String> subSequences =  new HashSet<>();
    public int numMatchingSubseq(String S, String[] words) {
        boolean isSubseq = false;
        int count = 0;
        for(String word : words) {
            boolean ans = isSubsequence(word, S, word.length(), S.length());
            if(ans){
                count++;
            }
        }
        return count;
    }

    private boolean isSubsequence(String sub, String main, int subLen, int mainLen) {
        if(subLen == 0) {
            return true;
        }
        if(mainLen == 0) {
            return false;
        }
        //Check for already existing
        if(subSequences.contains(sub.substring(0,subLen))){
            return true;
        }
        if(sub.charAt(subLen -1) == main.charAt(mainLen - 1)) {
            boolean val = isSubsequence(sub, main, subLen -1, mainLen -1);
            return storeAndReturn(sub, subLen -1, val);
        }
        boolean val = isSubsequence(sub, main, subLen, mainLen -1);
        return storeAndReturn(sub, subLen, val);
    }

    private boolean storeAndReturn(String sub, int subLen, boolean val){
        if(val && subLen > 0){
            subSequences.add(sub.substring(0,subLen));
        }
        return val;
    }

    @Test
    public void testNumMatchingSubseq() {
        int res = "a".compareTo("b");
        String input = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        SubsequenceMatching subsequenceMatching = new SubsequenceMatching();
        System.out.println(subsequenceMatching.numMatchingSubseq(input, words));
    }
}
