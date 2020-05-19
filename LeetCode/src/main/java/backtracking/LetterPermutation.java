package src.main.java.backtracking;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LetterPermutation {

    public Set<String> permuteLetters(String word) {
        Set<String> result = new HashSet<>();
        permuteLettersHelper(result, word, "");
        return result;
    }

    private void permuteLettersHelper(Set<String> result, String remainingChars, String currentString) {
        if(remainingChars.length() == 0){
            result.add(currentString);
        } else {
            for(int i=0; i< remainingChars.length(); i++){
                currentString += remainingChars.charAt(i);
                permuteLettersHelper(result, fetchRemainingChars(remainingChars,i), currentString);
                currentString = currentString.substring(0,currentString.length() -1);
            }
        }
    }

    private String fetchRemainingChars(String current, int index) {
        return current.substring(0, index) + current.substring(index+1);
    }

    @Test
    public void testPermuteLetters(){
        String word = "abcd";
        Set<String> result = permuteLetters(word);
        System.out.println("Count of Permutations " + result.size());
        System.out.println(result);
    }
}
