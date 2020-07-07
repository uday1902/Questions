package src.main.java.backtracking;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {

    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solveSudoku(char[][] problem) {
        int[][] intProblem = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                intProblem[i][j] = (problem[i][j] == '.')? 0:(problem[i][j] - '0');
            }
        }
        int[][] currSolution = solveSudoku(intProblem);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(problem[i][j] == '.') {
                    problem[i][j] = (char)('0' + currSolution[i][j]);
                }
            }
        }
    }

    public int[][] solveSudoku(int[][] problem) {
        int[][] currSolution = new int[9][9];
        List<Position> emptyPositions = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(problem[i][j] == 0) {
                    emptyPositions.add(new Position(i,j));
                    continue;
                }
                currSolution[i][j] = problem[i][j];
            }
        }
        backTrack(currSolution, emptyPositions, 0);
        return currSolution;
    }

    private boolean backTrack(int[][] currSolution, List<Position> positions,int index) {
        if(index == positions.size()) {
            return true;
        }
        for(int i=1;i<=9;i++) {
            Position position = positions.get(index);
            //choose
            if(!isValidEntry(currSolution,position,i)) {
                continue;
            }
            currSolution[position.x][position.y] = i;
            // backtrack
            boolean res = backTrack(currSolution, positions,index+1);
            if(res) {
                return true;
            }
            // Unchoose
            currSolution[position.x][position.y] = 0;
        }
        return false;
    }

    private boolean isValidEntry(int[][] currSolution, Position p, int val) {
        return isValidRowAdd(currSolution,p,val) &&
                isValidColumnAdd(currSolution,p,val) &&
                isValidBoxAdd(currSolution,p,val);
    }

    private boolean isValidRowAdd(int[][] currSolution, Position p, int val) {
        for(int i= 0; i < 9 ; i++) {
            if(i== p.y) {
                continue;
            }
            if(currSolution[p.x][i] == val) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidColumnAdd(int[][] currSolution, Position p, int val) {
        for(int i= 0; i < 9 ; i++) {
            if(i== p.x) {
                continue;
            }
            if(currSolution[i][p.y] == val) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidBoxAdd(int[][] currSolution, Position p, int val) {
        int xstart = 3*(p.x/3);
        int ystart = 3*(p.y/3);
        for(int i = xstart ; i < xstart +3; i++) {
            for(int j = ystart ; j < ystart +3; j++) {
                if(currSolution[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testSudoku() {
        char[][] prb = {
                {'.','.','4','6','8','.','.','.','1'},
                {'.','.','.','.','.','.','.','2','.'},
                {'.','8','.','.','.','1','5','6','3'},
                {'1','.','.','.','3','9','.','.','.'},
                {'.','.','.','.','2','.','.','.','.'},
                {'.','.','.','1','6','.','.','.','7'},
                {'7','9','5','2','.','.','.','3','.'},
                {'.','2','.','.','.','.','.','.','.'},
                {'6','.','.','.','1','7','4','.','.'}
        };
        solveSudoku(prb);
        System.out.println("\nSolution:");
        for(int i = 0; i < 9; i++) {
            System.out.println("");
            for(int j = 0; j < 9; j++) {
                System.out.print(prb[i][j]);
            }
        }
    }
}