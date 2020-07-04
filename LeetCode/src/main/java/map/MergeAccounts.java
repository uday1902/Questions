package src.main.java.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MergeAccounts {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer,List<String>> idMap = new HashMap<>();
        Map<String, Integer> emailMap = new HashMap<>();
        int counter =0;
        for(List<String> lst : accounts) {
            idMap.put(counter++,lst);
        }
        Iterator<Map.Entry<Integer,List<String>>> it = idMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer,List<String>> entry = it.next();
            boolean start = true;

            for(String email: entry.getValue()) {
                if(start) {
                    start = false;
                    continue;
                }
                if(emailMap.containsKey(email)) {
                   int parent = emailMap.get(email);
                    merge(idMap, emailMap, parent, entry.getKey());
                    break;
                } else {
                    emailMap.put(email, entry.getKey());
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(int key : new HashSet<>(emailMap.values())) {
            result.add(sortList(idMap.get(key)));
        }
        return result;
    }

    private void merge(Map<Integer,List<String>> idMap, Map<String, Integer> emailMap , int parent, int removed) {
        List<String> removedEmails = idMap.get(removed);
        List<String> existingEmails = new ArrayList<>(idMap.get(parent));
        for(int i=1;i<removedEmails.size(); i++) {
            emailMap.put(removedEmails.get(i), parent);
            existingEmails.add(removedEmails.get(i));
        }
        idMap.put(parent, existingEmails);
        //idMap.remove(removed);
    }

    private List<String> sortList(List<String> lst) {
        List<String> sorted = new ArrayList<>();
        String name = lst.get(0);
        sorted.add(name);
        List<String> newLst= new ArrayList<String> (new HashSet<String>(lst));
        Collections.sort(newLst);
        for(String email: newLst) {
            if(!email.equals(name)) {
                sorted.add(email);
            }
        }
        return sorted;
    }

    @Test
    public void testMerging() {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","john00@mail.com","johnsmith@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith2@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        List<List<String>> result = accountsMerge(accounts);
        result.stream().forEach(e -> System.out.println(e));
        System.out.println(result);
        //Arrays.asList([Arrays.asList(["John","johnsmith@mail.com","john_newyork@mail.com"]))
        //["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]];

    }

    @Test
    public void dummy() {
        Assert.assertEquals( 0, -25%5);
        Assert.assertEquals( -4, -24%5);
        Assert.assertEquals( 4, Math.abs(-24)%5);
        Assert.assertEquals( 1, Math.abs(-21)%5);
        Assert.assertEquals( 1, Math.abs(-11)%5);
        Assert.assertEquals( 1, Math.abs(11)%5);

    }

}
