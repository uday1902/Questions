package src.main.java.DataStructures.graphs;

import org.junit.Test;

import java.util.*;

public class ShortestPathGrid {

    public class Point {
        int x;
        int y;
        Point parent;
        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) {
                return true;
            }
            if(other == null || getClass() != other.getClass()) {
                return false;
            }
            Point that =  (Point)other;
            return this.x == that.x && this.y == that.y;
        }
    }

    public List<Point> fetchPath(int[][] input, int startx, int starty, int endx, int endy) {
        Set<Point> visited = new HashSet<>();
        Deque<Point> queue = new ArrayDeque<>();
        Point start = new Point(startx, starty, null);
        return traverse(start,new Point(endx, endy, null),visited, queue,input);
    }

    public List<Point> traverse(Point start, Point end, Set<Point> visited, Deque<Point> queue, int[][] input) {
        visited.add(start);
        queue.addLast(start);
        while(!queue.isEmpty()) {
            Point curr = queue.poll();
            if(isDestination(curr, end)) {
                return buildPath(curr);
            }
            List<Point> neighbours = getUnvisitedNeighbours(input, curr,visited);
            for(Point neighbour: neighbours) {
                queue.addLast(neighbour);
            }
        }
        return new LinkedList<>();
    }

    public boolean isDestination(Point point, Point end) {
        return point.x == end.x && point.y == end.y;
    }

    public List<Point> buildPath(Point point) {
        LinkedList<Point> path = new LinkedList<>();
        while(point != null) {
            path.addFirst(point);
            point = point.parent;
        }
        return path;
    }

    private List<Point> getUnvisitedNeighbours(int[][] input, Point point, Set<Point> visited) {
        List<Point> neighbours = new ArrayList<>();
        addIfValid(input,new Point(point.x-1,point.y,point), neighbours, visited);
        addIfValid(input,new Point(point.x+1,point.y,point), neighbours, visited);
        addIfValid(input,new Point(point.x,point.y-1,point), neighbours, visited);
        addIfValid(input,new Point(point.x,point.y+1,point), neighbours, visited);
        return neighbours;
    }

    private void addIfValid(int[][] input, Point point, List<Point> neighbours, Set<Point> visited) {
        if(isWithinBoundaries(input, point)
                && !visited.contains(point)
                && input[point.x][point.y] != -1) {
            neighbours.add(point);
        }
    }

    private boolean isWithinBoundaries(int[][] input, Point point) {
        return point.x >= 0 && point.x < input.length && point.y >= 0 && point.y < input[0].length;
    }


    @Test
    public void testFetchPath() {
//        int[][] input = {
//                {0,0,0,0},
//                {0,0,-1,0},
//                {0,-1,0,0},
//                {0,-1,0,0}
//        };
        int[][] input = {
                {0,0,0,0},
                {0,0,0,0},
                {0,-1,0,0},
                {0,-1,0,0}
        };
        List<Point> path = fetchPath(input, 2,0,1,3);
        path.stream()
                .forEach(ele -> System.out.println(ele.x + ", " + ele.y));
//        System.out.println(path);
    }

}
