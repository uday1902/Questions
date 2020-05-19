package src.main.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WordPattern {

    public boolean wordPatternNew(String pattern, String str) {
        List<String> inputList = sentenceToStringList(str);
        if(inputList.size() != pattern.length()){
            return false;
        }
        String[] stringMap = new String[26];
        Map<String, Character> revMap = new HashMap<>();
        for(int i=0 ; i < pattern.length();i++ ){
            char ch = pattern.charAt(i);
            String cur = stringMap[ch-'a'];
            String strEntry = inputList.get(i);
            if( null != cur && !strEntry.equals(cur)){
                return false;
            }
            if(revMap.containsKey(strEntry) && !revMap.get(strEntry).equals(ch)){
                return false;
            }
            if( null == stringMap[ch-'a'] ){
                stringMap[ch-'a'] = strEntry;
                revMap.put(strEntry, ch);
            }

        }
        return true;
    }

    public List<String> sentenceToStringList(String input){
        return new ArrayList(Arrays.asList(input.split("\\s+")));
    }

    @Test
    public void testWorkPattern() {
        Assert.assertEquals(true, wordPatternNew("abb", "dog cat cat"));
        Assert.assertEquals(true, wordPatternNew("abba", "dog cat cat dog"));
        Assert.assertEquals(false, wordPatternNew("abba", "dog cat cat fish"));
        Assert.assertEquals(false, wordPatternNew("abb", "dog dog dog"));
    }
}
