package src.main.java.backtracking;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class EightQueens {

    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int numOfSolutions = 0;
    private int noOfFunctionCalls = 0;

    public List<List<String>> nQueens(int count) {
        numOfSolutions =0;
        noOfFunctionCalls = 0;
        List<List<Position>> solutions = new ArrayList<>();
        List<Position> currentPositions = new ArrayList<>();
        Set<Integer> remainingRows = new HashSet<>();
        for(int i = 0; i< count ;i++) {
            remainingRows.add(i);
        }
        placeQueens(currentPositions, solutions, 0,count);
        return convertToMatrix(solutions, count);
    }

    private void placeQueens(List<Position> currentPositions, List<List<Position>> solutions,int row,int count) {
        noOfFunctionCalls++;
        if(row == count) {
            numOfSolutions++;
            solutions.add(new ArrayList<>(currentPositions));
        } else {
            for(int i = 0; i < count; i++) {
                if(isConflict(currentPositions, row,i)){
                    continue;
                }
                Position position = new Position(row, i);
                currentPositions.add(position);
                placeQueens(currentPositions,solutions, row+1, count);
                currentPositions.remove(currentPositions.size() -1);
            }
        }

    }

    private boolean isConflict(List<Position> currentPositions, int x, int y) {
        for(Position pos: currentPositions) {
            if(isStraightLine(pos.x,pos.y,x,y) || isDiagonal(pos.x,pos.y,x,y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonal(int x1,int y1, int x2, int y2) {
        return Math.abs(x1-x2) == Math.abs(y1-y2);
    }

    private boolean isStraightLine(int x1,int y1, int x2, int y2) {
        return x1==x2 || y1==y2;
    }

    private List<List<String>> convertToMatrix(List<List<Position>> solutionList, int count) {

        List<List<String>> result = new ArrayList<>();
        for(List<Position> solution: solutionList) {
            List<String> sol = new ArrayList<>();
            for(Position p: solution) {
                 sol.add(p.x, addQueen(count, p.y));
            }
            result.add(sol);
        }
        return result;
    }

    private String addQueen(int count, int index) {
        return addDots(index) + "Q" + addDots(count - index -1);
    }

    private String addDots(int count) {
        StringBuilder row= new StringBuilder();
        for(int j =0; j<count; j++) {
            row.append(".");
        }
        return row.toString();
    }

    @Test
    public void test8queens() {
        List<List<String>> result = nQueens(5);
        System.out.println("Count of functioncalls -> " + noOfFunctionCalls);
        System.out.println("Count of Solutions -> " + numOfSolutions);
        result.stream().forEach(lst -> {
            System.out.println("\n sol : ");
            lst.stream().forEach( System.out::println);
        });
        //System.out.println(result);
    }
}