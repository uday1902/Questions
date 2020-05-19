package src.main.java;

import org.junit.Test;

import java.util.*;

public class KeyboardRow {
    public String[] findWords(String[] words) {
        List<String> oneRowWords = new ArrayList<>();
        for(String word: words){
            if(isOneRowWord(word)){
                oneRowWords.add(word);
            }
        }

        return oneRowWords.toArray(new String[oneRowWords.size()]);
    }

    private final Set<Character> firstRow = new HashSet<>(Arrays.asList('q','w','e','r','t','y','u','i','o','p'));
    private final Set<Character> secondRow = new HashSet<>(Arrays.asList('a','s','d','f','g','h','j','k','l'));
    private final Set<Character> thirdRow = new HashSet<>(Arrays.asList('z','x','c','v','b','n','m'));

    private boolean isOneRowWord(String word){
        int row = identifyRow(word.charAt(0));
        for(int i = 1 ; i< word.length() ; i++){
            char ch = word.charAt(i);
            if(row != identifyRow(ch)){
                return false;
            }
        }
        return true;
    }

    private int identifyRow(char ch){
        if(firstRow.contains(lower(ch))){
            return 1;
        } else if(secondRow.contains(lower(ch))){
            return 2;
        }
        return 3;
    }

    private char lower(char ch){
        if('A' <= ch && ch <= 'Z'){
            return (char)( ch -'A' + 'a');
        }
        return ch;
    }

    @Test
    public void testCode(){
        String[] words = {"Hello","Alaska","Dad","Peace"};
        KeyboardRow problem = new KeyboardRow();
        System.out.println(Arrays.toString(problem.findWords(words)));
    }

//    public static void main(String[] args){
//        String[] words = {"Hello","Alaska","Dad","Peace"};
//        KeyboardRow problem = new KeyboardRow();
//        problem.findWords(words);
//    }
}
