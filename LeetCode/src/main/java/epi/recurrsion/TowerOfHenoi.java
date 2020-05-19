package src.main.java.epi.recurrsion;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * Contains 3 towers
 * Tower 0 has weights from 1 to n from top to bottom.
 * Tower 1 & 2 are empty
 * Problem : Move the weights from Tower 1 to Tower 2
 * */
public class TowerOfHenoi {

    class Move {
        int start;
        int end;
        Move(int start,int end, List<Deque<Integer>> towers) {
            this.start = start;
            this.end = end;
            towers.get(end).push(towers.get(start).pop());
        }
    }

    public List<Deque<Integer>> move(int n) {
        List<Deque<Integer>> towers = setup(n);
        List<Move> moves = new ArrayList<>();
        move(towers,0,1,n,moves);
        printTransition(moves);
        return towers;
    }

    public void move(List<Deque<Integer>> towers, int currTower, int destTower, int num, List<Move> moves) {
        //base case
        if(num == 1){
            moves.add(new Move(currTower, destTower,towers));
            return;
        }
        // recurse
        int otherTower = findOtherTower(currTower, destTower);
        move(towers, currTower, otherTower, num-1, moves);
        moves.add(new Move(currTower,destTower,towers));
        move(towers, otherTower, destTower, num-1, moves);
    }

    public int findOtherTower(int currTower, int destTower) {
        for(int i= 0 ; i<3; i++){
            if(i!= currTower && i!= destTower){
                return i;
            }
        }
        return 2;
    }

    public List<Deque<Integer>> setup(int n) {
        List<Deque<Integer>> towers = new ArrayList<>();
        Deque<Integer> tower1 = new ArrayDeque<>();
        for(int i = n; i >= 1; i--) {
            tower1.push(i);
        }
        towers.add(tower1);
        towers.add(new ArrayDeque<>());
        towers.add(new ArrayDeque<>());
        System.out.println("Start");
        print(towers);
        return towers;
    }

    public void print(List<Deque<Integer>> towers) {
        int i = 1;
        for(Deque<Integer> tower : towers){
            System.out.println("\nTower - " + i++ + ":" + tower);
        }
    }

    public void printTransition(List<Move> moves){
        System.out.println("\n All Moves \n");
       for(Move mv: moves){
           System.out.print("("+mv.start + ", " + mv.end + ") ->");
       }
    }

    @Test
    public void testMove() {
        int n = 4;
        List<Deque<Integer>> towers = move(n);
        System.out.println("\nAfter Move");
        print(towers);
    }

    private int indexValue(int num, int index) {
        return Double.valueOf((num - num%Math.pow(10,index))/Math.pow(10,index)).intValue()%10;
    }

    @Test
    public void testF() {
        Assert.assertEquals(7,indexValue(2736,2));
    }
}
