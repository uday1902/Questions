package src.main.java.backtracking;

import org.junit.Test;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words= new HashSet<>(wordDict);
        return recurse(s, words, new HashMap<String,Boolean>());
    }

    private boolean recurse(String s, Set<String> words, HashMap<String,Boolean> mp ) {
        if(s.isEmpty() || words.contains(s)) {
            return true;
        }
        if(mp.containsKey(s)) {
            return mp.get(s);
        }
        for(int i=1; i< s.length(); i++) {
            String str = s.substring(0,i);
            if(!words.contains(str)){
                continue;
            }
            String newS = s.substring(i,s.length());
            boolean result = recurse(newS, words, mp);
            if(result) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testWordBreak() {
        String str =  "leetcode";
        List<String> dict = Arrays.asList("leet", "code");
        boolean result = wordBreak(str, dict);
        System.out.println("Result is -> " + result);
    }
}
