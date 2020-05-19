package src.main.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
/* Could not finish the solution.*/
public class NoOfAtoms {

    private class CountVal {
        int count;
        int length;

        CountVal(int count, int length) {
            this.count = count;
            this.length = length;
        }
    }

    public String countOfAtoms(String formula) {
        Map<String, Integer> countMap = countOfAtomsMap(formula);
        return mapToString(countMap);
    }

    public Map<String, Integer> countOfAtomsMap(String formula) {

        if (formula.isEmpty()) {
            return new HashMap<>();
        }
        if (formula.charAt(0) == '(') {
            return getBracketCount(formula);
        } else {
            return getIndividualCount(formula);
        }
    }

    public String mapToString(Map<String, Integer> map) {
        String result = "";
        for (String ele : setToList(map.keySet())) {
            int val = map.get(ele);
            result += val==1 ? ele : ele + val;
        }
        return result;
    }

    public void multiply(Map<String, Integer> map, int multiple) {
        for (String key : map.keySet()) {
            map.put(key, map.get(key) * multiple);
        }
    }

    public List<String> setToList(Set<String> metals) {
        List<String> lst = new ArrayList<>();
        lst.addAll(metals);
        Collections.sort(lst);
        return lst;
    }

    public void addMap(Map<String, Integer> original, Map<String, Integer> newMap) {
        for (String key : newMap.keySet()) {
            int val = newMap.get(key);
            if (original.containsKey(key)) {
                val += original.get(key);
            }
            original.put(key, val);
        }
    }

    public Map<String, Integer> getIndividualCount(String formula) {
        String metalName = getMetalName(formula);
        Map<String, Integer> countMap = new HashMap<>();
        if (formula.length() == metalName.length()) {
            countMap.put(metalName, 1);
            return countMap;
        }
        char nextChar = formula.charAt(metalName.length());
        CountVal countVal = new CountVal(1, 0);
        if (nextChar >= 48 && nextChar <= 57) {
            countVal = getCount(formula.substring(metalName.length()));
        }
        countMap.put(metalName, countVal.count);
        int nextIndex = metalName.length() + countVal.length;
        if (formula.length() <= nextIndex) {
            return countMap;
        }
        addMap(countMap, countOfAtomsMap(formula.substring(nextIndex)));
        return countMap;
    }

    public Map<String, Integer> getBracketCount(String formula) {
        int index = formula.lastIndexOf(")");
        CountVal countVal = getCount(formula.substring(index + 1));
        Map<String, Integer> countMap = countOfAtomsMap(formula.substring(1, index));
        multiply(countMap, countVal.count);
        addMap(countMap, countOfAtomsMap(formula.substring(index + 1 + countVal.length)));
        return countMap;
    }

    public String getMetalName(String formula) {
        int curr = 1;
        String name = "" + formula.charAt(0);
        while (curr < formula.length() && formula.charAt(curr) >= 97 && formula.charAt(curr) <= 122) {
            name = name + formula.charAt(curr);
            curr++;
        }
        return name;
    }

    public CountVal getCount(String formula) {
        if (formula.isEmpty() || (formula.charAt(0) < 48 || formula.charAt(0) > 57)) {
            return new CountVal(1, 0);
        }
        int curr = 1;
        int count = formula.charAt(0) - 48;
        while (curr < formula.length() && formula.charAt(curr) >= 48 && formula.charAt(curr) <= 57) {
            count = count * 10 + (formula.charAt(curr) - 48);
            curr++;
        }
        return new CountVal(count, curr);
    }

    @Test
    public void testGetMetalName() {
        Assert.assertEquals(getMetalName("MghFD"), "Mgh");
        Assert.assertEquals(getMetalName("MFD"), "M");
        Assert.assertEquals(getMetalName("M2D"), "M");
        Assert.assertEquals(getMetalName("MD3"), "M");
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(getCount("764GHg").count, 764);
        Assert.assertEquals(getCount("764GHg").length, 3);
        Assert.assertEquals(getCount("764").count, 764);
        Assert.assertEquals(getCount("764GHg").length, 3);
        Assert.assertEquals(getCount("").count, 1);
        Assert.assertEquals(getCount("").length, 0);
    }

    @Test
    public void testGetIndividualCount() {
        Map<String, Integer> cnt = getIndividualCount("Md43T54");
        Assert.assertTrue(cnt.containsKey("Md"));
        Assert.assertEquals(cnt.get("Md").intValue(),43);
        Assert.assertTrue(cnt.containsKey("T"));
        Assert.assertEquals(cnt.get("T").intValue(),54);
    }

    @Test
    public void testGetBracketCount() {
        Map<String, Integer> cnt = getBracketCount("(MgD)2");
        Assert.assertTrue(cnt.containsKey("Mg"));
        Assert.assertEquals(cnt.get("Mg").intValue(),2);
        Assert.assertTrue(cnt.containsKey("D"));
        Assert.assertEquals(cnt.get("D").intValue(),2);
        Map<String, Integer> cnt2 = getBracketCount("(MgD2)2Mg5");
        Assert.assertTrue(cnt2.containsKey("Mg"));
        Assert.assertEquals(cnt2.get("Mg").intValue(),7);
        Assert.assertTrue(cnt2.containsKey("D"));
        Assert.assertEquals(cnt2.get("D").intValue(),4);
    }

    @Test
    public void testAtomsCountMap() {
        Map<String, Integer> cnt2 = countOfAtomsMap("D32(MgD2)2Mg5");
        Assert.assertTrue(cnt2.containsKey("Mg"));
        Assert.assertEquals(cnt2.get("Mg").intValue(),7);
        Assert.assertTrue(cnt2.containsKey("D"));
        Assert.assertEquals(cnt2.get("D").intValue(),36);
    }

    @Test
    public void testAtomsCount() {
//        Assert.assertEquals("D36H4Mg7",countOfAtoms("D32(MgD2)2Mg5H4"));
//        Assert.assertEquals("Be7956H306He8874",countOfAtoms("((HHe28Be26He)9)34"));
        Assert.assertEquals("Be7956H306He8874",countOfAtoms("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14"));
    }
}
