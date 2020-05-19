package src.main.java.DataStructures.graphs;

import org.junit.Test;

import java.util.*;

public class CriticalRouterAmazon {

    public List<Integer> fetchCriticalRouter(List<Integer> routers, List<List<Integer>> links) {
        Map<Integer, Set<Integer>> graph = createGraph(routers, links);
        List<Integer> result = new ArrayList<>();
        for(int key: graph.keySet()) {
            if(!isCriticalRouter(graph, key, routers.size())) {
                result.add(key);
            }
        }
        return result;
    }

    private Map<Integer, Set<Integer>> createGraph(List<Integer> routers, List<List<Integer>> links) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(List<Integer> link: links) {
            addLink(graph, link);
        }
        return graph;
    }

    private void addLink(Map<Integer, Set<Integer>> graph, List<Integer> link) {
        addLink(graph,link.get(0),link.get(1));
        addLink(graph,link.get(1),link.get(0));
    }

    private void addLink(Map<Integer, Set<Integer>> graph, int source, int destination) {
        Set<Integer> valueSet = new HashSet<>();
        if(graph.containsKey(source)){
            valueSet = graph.get(source);
        }
        valueSet.add(destination);
        graph.put(source, valueSet);
    }

    private boolean isCriticalRouter(Map<Integer, Set<Integer>> graph, int key, int size) {
        Set<Integer> visited = new HashSet<>();
        visited.add(key);
//        Deque<Integer> stack =  new LinkedList<>();
//        stack.addFirst(graph.get(key).iterator().next());
//        dfs(graph, visited, stack);
        dfsr(graph, visited, graph.get(key).iterator().next());
        if(visited.size() < size) {
            return false;
        }
        return true;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, Deque<Integer> stack) {
        while(!stack.isEmpty()) {
            int ele = stack.removeFirst();
            visited.add(ele);
            for(int connEle: graph.get(ele)){
                if(!visited.contains(connEle)) {
                    stack.addFirst(connEle);
                }
            }
        }
    }

    private void dfsr(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int top) {
        visited.add(top);
        for(int connEle: graph.get(top)){
            if(!visited.contains(connEle)) {
                dfsr(graph,visited,connEle);
            }
        }
    }

    @Test
    public void testFetchCriticalRouter() {
        List<Integer> routers = Arrays.asList(1,2,3,4,5,6,7);
        List<List<Integer>> links = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(1,3),
                Arrays.asList(2,4),
                Arrays.asList(3,4),
                Arrays.asList(3,6),
                Arrays.asList(4,5)
                ,Arrays.asList(6,7)
                );
        System.out.println(fetchCriticalRouter(routers, links));
    }
}
