package src.main.java;

import java.sql.Timestamp;
import java.util.*;

public class KeyValueByTimeStamp {
    public Map<String, Map<Long, String>> keyValueMap = new HashMap<>();
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        KeyValueByTimeStamp sol = new KeyValueByTimeStamp();
        long timeStamp1 = sol.setKeyValue("foo", "bar1");
        System.out.println(timeStamp1);
        Thread.sleep(5);
        System.out.println(sol.setKeyValue("foo", "bar2"));
        Thread.sleep(5);
        System.out.println(sol.setKeyValue("foo", "bar3"));
        Thread.sleep(5);
        System.out.println(sol.setKeyValue("foo", "bar4"));
        Thread.sleep(5);
        System.out.println(sol.setKeyValue("foo", "bar5"));
        Thread.sleep(5);
        System.out.println(sol.setKeyValue("foo", "bar6"));
        System.out.println(sol.getValue("foo"));
        System.out.println(sol.getValue("foo", timeStamp1));
        System.out.println(sol.getValue("foo", timeStamp1 + 5));
        System.out.println(sol.getValue("foo", timeStamp1 + 7));
        System.out.println(sol.getValue("foo", timeStamp1 + 8));
        System.out.println(sol.getValue("foo", timeStamp1 + 16));
        System.out.println(sol.getValue("foo", timeStamp1 + 17));
        System.out.println(sol.getValue("foo", timeStamp1 + 19));
        System.out.println(sol.getValue("foo", timeStamp1 + 21));
    }

    public long setKeyValue(String key, String value) {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        long timeStampValue = timeStamp.getTime();
        Map<Long, String> currentTimeStampMap = new LinkedHashMap<>();
        if(keyValueMap.containsKey(key)){
            currentTimeStampMap = keyValueMap.get(key);
        }
        currentTimeStampMap.put(timeStampValue, value);
        keyValueMap.put(key, currentTimeStampMap);
        return timeStampValue;
    }

    public String getValue(String key) {
        Map<Long, String> currentTimeStampMap = keyValueMap.get(key);
        if(currentTimeStampMap == null) {
            return null;
        }
        long timeStampKey = getLatestTimeStamp(new ArrayList<Long>(currentTimeStampMap.keySet()));
        return currentTimeStampMap.get(timeStampKey);
    }

    public String getValue(String key, long timeStamp) {
        Map<Long, String> currentTimeStampMap = keyValueMap.get(key);
        if(currentTimeStampMap == null) {
            return null;
        }
        if(currentTimeStampMap.containsKey(timeStamp)){
            return currentTimeStampMap.get(timeStamp);
        }
        return getClosestValue(key, timeStamp);
    }

    private String getClosestValue(String key, long timeStamp) {
        Map<Long, String> currentTimeStampMap = keyValueMap.get(key);
        List<Long> timeStampList = new ArrayList<>(currentTimeStampMap.keySet());
        return getValue(key, getClosestTimestamp(timeStamp, timeStampList));
    }

    private long getClosestTimestamp(long key, List<Long> timeStampList) {
        int start = 0;
        int end = timeStampList.size() -1;
        if(timeStampList.size() == 1){
            return timeStampList.get(0);
        }
        if(timeStampList.get(start) > key){
            return timeStampList.get(start);
        } else if(timeStampList.get(end) < key){
            return  timeStampList.get(end);
        }
        while(start < end -1) {
            int mid = (start + end )/2;
            if(timeStampList.get(mid) < key ){
                start = mid;
            } else {
                end = mid;
            }
        }
        if(timeStampList.get(end) - key > key - timeStampList.get(start)){
            return timeStampList.get(start);
        }
        return timeStampList.get(end);
    }

    private long getLatestTimeStamp(List<Long> lst){
        if(lst.isEmpty()){
            return 0;
        }
        return lst.get(lst.size()-1).longValue();
    }
}
