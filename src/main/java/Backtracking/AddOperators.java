package Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
https://leetcode.com/problems/expression-add-operators/
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 */
/*
Running time is O(4^n)
Space needed is O(n) if the space needed for storing the output is not considered
 */
public class AddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> allExp = new ArrayList<>();
        if(num == null || num.length() == 0){
            return allExp;
        }
        getAllCombinations(allExp, num, 0, target, new StringBuilder(), 0, 0);
        return allExp;
    }

    void getAllCombinations(List<String> allExp, String num, int index, int target, StringBuilder currExp, long currSum, long valReversal){
        if(index == num.length()){
            if(currSum == target){
                allExp.add(currExp.toString());
            }
            return;
        }
        for(int i = index; i<num.length(); i++){
            String numValStr = num.substring(index, i+1);
            if(numValStr.charAt(0) == '0' && numValStr.length()!=1){
                continue; // eliminating cases like 05
            }
            long numVal = Long.parseLong(numValStr);
            if(index == 0){
                currExp.append(numValStr);
                getAllCombinations(allExp, num, i+1, target, currExp, numVal, numVal);
                currExp.setLength(currExp.length()-numValStr.length());
            } else {

                String nextOperationStr = "+" +Long.toString(numVal);
                currExp.append(nextOperationStr);
                getAllCombinations(allExp, num, i+1, target, currExp, currSum+numVal, numVal);
                currExp.setLength(currExp.length()-nextOperationStr.length());

                nextOperationStr = "-" +Long.toString(numVal);
                currExp.append(nextOperationStr);
                getAllCombinations(allExp, num, i+1, target, currExp, currSum-numVal, -numVal);
                currExp.setLength(currExp.length()-nextOperationStr.length());

                nextOperationStr = "*" +Long.toString(numVal);
                currExp.append(nextOperationStr);
                getAllCombinations(allExp, num, i+1, target, currExp, currSum-valReversal + valReversal*numVal, valReversal*numVal);
                currExp.setLength(currExp.length()-nextOperationStr.length());
            }
        }
    }
}
