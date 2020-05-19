package src.main.java.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EightQueens {

    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int  numOfSolutions =0;
    private int noOfFunctionCalls = 0;

    public List<List<Position>> eightQueens(int count) {
        numOfSolutions =0;
        noOfFunctionCalls = 0;
        List<List<Position>> solutions = new ArrayList<>();
        List<Position> currentPositions = new ArrayList<>();
        Set<Integer> remainingRows = new HashSet<>();
        for(int i = 0; i< count ;i++) {
            remainingRows.add(i);
        }
        //placeQueens(currentPositions, solutions, remainingRows, count);
        placeQueens2(currentPositions, solutions, 0,count);
        return solutions;
    }

    private void placeQueens2(List<Position> currentPositions, List<List<Position>> solutions,int row,int count) {
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
                        placeQueens2(currentPositions,solutions, row+1, count);
                        currentPositions.remove(currentPositions.size() -1);
                    }
                }

    }

//    private void placeQueens(List<Position> currentPositions, List<List<Position>> solutions, Set<Integer> remainingRows, int count) {
//        noOfFunctionCalls++;
//        if(remainingRows.isEmpty()) {
//            List<Position> aSolution = new ArrayList<>(currentPositions);
//            System.out.print("Sol -> ");
//            aSolution.stream().forEach(e -> System.out.print("("+ e.x+","+e.y+") " ));
//            solutions.add(aSolution);
//            numOfSolutions++;
//            System.out.print("\nFunctionCalls so far -> " + noOfFunctionCalls);
//            System.out.println(" Solutions so far -> " + numOfSolutions);
//        } else {
//            //solutions.add();
//            for(int i=0;i < count; i++) {
//                if(!remainingRows.contains(i)){
//                    continue;
//                }
//                for(int j=0;j < count; j++) {
//                    if(isConflict(currentPositions, i,j)){
//                        continue;
//                    }
//                    Position position = new Position(i, j);
//                    currentPositions.add(position);
//                    remainingRows.remove(i);
//                    placeQueens(currentPositions, solutions, remainingRows, count);
//                    remainingRows.add(i);
//                    currentPositions.remove(position);
//                }
//            }
//        }
//    }

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

    @Test
    public void test8queens() {
        List<List<Position>> result = eightQueens(8);
        System.out.println("Count of functioncalls -> " + noOfFunctionCalls);
        System.out.println("Count of Solutions -> " + numOfSolutions);
        result.stream().forEach(lst -> {
            System.out.print("\n sol : ");
            lst.stream().forEach( e -> System.out.print("("+ e.x+","+e.y+") " ));
        });
        //System.out.println(result);
    }
}