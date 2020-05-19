package src.main.java.DataStructures.Stacks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Evaluate the expression "+,*,+,3,4,2,1"
 * */
public class PolishExpression {

    public int eval(String exp){
        if(exp == null || exp.isEmpty()){
            return 0;
        }
        Deque<String> symbols = new LinkedList<>();
        String delimiter = ",";
        String[] tokens = exp.split(delimiter);
        int tokenIndex = 0;
        boolean start = true;
        int currentVal = 0;
        while(tokenIndex < tokens.length) {
            String token = tokens[tokenIndex++];
            if(token.length() == 1 && "+-*/".contains(token)){
                symbols.addFirst(token);
            } else {
                int newVal = Integer.parseInt(token);
                if(start){
                    start = false;
                    token = tokens[tokenIndex++];
                    currentVal = newVal;
                    newVal = Integer.parseInt(token);
                }
                String arthSymbol = symbols.removeFirst();
                switch (arthSymbol) {
                    case "+":
                        currentVal = currentVal + newVal;
                        break;
                    case "-":
                        currentVal = currentVal - newVal;
                        break;
                    case "*":
                        currentVal = currentVal * newVal;
                        break;
                    case "/":
                        currentVal = currentVal / newVal;
                        break;
                }
            }
        }
        return currentVal;
    }

    @Test
    public void testEval() {
        Assert.assertEquals(0,eval(""));
        Assert.assertEquals(15,eval("+,*,+,3,4,2,1"));
        Assert.assertEquals(9,eval("/,+,/,-,73,3,2,1,4"));
    }
}
