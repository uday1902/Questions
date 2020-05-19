package src.main.java.Strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MnemonicsPhoneNumber {

    Map<Integer, String> phoneNumberMap = getPhoneNumberMap();

    public List<String> getAllMnemonics(String phoneNumber) {
        List<String> blankList = new ArrayList<>();
        blankList.add("");
        return allCombinations(phoneNumber, blankList, 0);
    }

    private List<String> allCombinations(String phoneNumber, List<String> inputList, int digitIndex) {
        if(digitIndex == phoneNumber.length()){
            return inputList;
        }
        int digit = phoneNumber.charAt(digitIndex) - '0';
        List<String> appendedList = new ArrayList<>();
        for(String element : inputList) {
            appendedList.addAll(getAppendedList(digit, element));
        }
        return allCombinations(phoneNumber, appendedList, digitIndex + 1);
    }

    private List<String> getAppendedList(int digit, String element) {
        List<String> newList = new ArrayList<>();
        for(char ch : phoneNumberMap.get(digit).toCharArray()) {
            newList.add(element + ch);
        }
        return newList;
    }

    Map<Integer, String> getPhoneNumberMap() {
        Map<Integer, String> phoneNumberMap = new HashMap<>();
        phoneNumberMap.put(2, "ABC");
        phoneNumberMap.put(3, "DEF");
        phoneNumberMap.put(4, "GHI");
        phoneNumberMap.put(5, "JKL");
        phoneNumberMap.put(6, "MNO");
        phoneNumberMap.put(7, "PQRS");
        phoneNumberMap.put(8, "TUV");
        phoneNumberMap.put(9, "WXYZ");
        return phoneNumberMap;
    }

    @Test
    public void testMnemonics() {
        System.out.println(getAllMnemonics("23"));
    }
}
