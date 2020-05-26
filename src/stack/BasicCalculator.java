package stack;

import java.util.HashMap;
import java.util.Stack;

/*
https://leetcode.com/problems/basic-calculator-ii/
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class BasicCalculator {
    static HashMap<Character, Integer> operation = new HashMap<>();
    static {
        operation.put('-',  1);
        operation.put('+', 1);
        operation.put('*', 2);
        operation.put('/', 2);
    }
    public int calculate(String s) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operator = new Stack<>();
        int i = 0;
        int currOperand = 0;
        while(i<s.length()){
            if(isOperator(s.charAt(i))){
                operands.push(currOperand);
                currOperand = 0;
                while(!operator.isEmpty() && isPriority(operator.peek(), s.charAt(i))){
                    int secondOperand = operands.pop();
                    int firstOperand = operands.pop();
                    operands.push(doOperation(firstOperand, secondOperand, operator.pop()));
                }
                operator.push(s.charAt(i));
            } else if (s.charAt(i) != ' '){
                currOperand = (currOperand * 10) + (s.charAt(i) - '0');
            }
            ++i;
        }
        operands.add(currOperand);
        while(!operator.isEmpty()){
            int secondOperand = operands.pop();
            int firstOperand = operands.pop();
            operands.push(doOperation(firstOperand, secondOperand, operator.pop()));
        }
        return operands.pop();
    }
    int getInteger(char c){
        return c-'0';
    }

    boolean isOperator(char c){
        return operation.containsKey(c);
    }

    boolean isPriority(char c1, char c2){
        return operation.get(c1) >= operation.get(c2);
    }

    int doOperation(int a, int b, char operator){
        switch(operator){
            case '-':
                return a-b;
            case '+':
                return a+b;
            case '*':
                return a*b;
            case '/':
                return a/b;
        }
        return 0;
    }
}
