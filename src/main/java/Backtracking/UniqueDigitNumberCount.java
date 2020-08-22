package Backtracking;

import java.util.HashSet;

/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
             excluding 11,22,33,44,55,66,77,88,99
 */
public class UniqueDigitNumberCount {
    //----------------------------------------------------------------------
    //Algo1: use backtracking
    //----------------------------------------------------------------------
    public int countNumbersWithUniqueDigits(int n) {
        HashSet<Integer> hs = new HashSet<Integer>();
        return doCount(n, hs);
    }

    int doCount(int n, HashSet<Integer> visited){
        if (n == 0) {
            return 1;
        }
        else if(n == 1){
            return 10-visited.size();
        }
        int count =0;
        for(int i = 0;i<=9;i++){
            if(!visited.contains(i)){
                if(i != 0 || (i == 0 && !visited.isEmpty())) {
                    // Do not add 0 to visited when it is added to the left
                    // for exammple 00054 is a valid number
                    visited.add(i);
                }
                count += doCount(n-1, visited);
                visited.remove(i);
            }
        }
        return count;
    }
    //----------------------------------------------------------------------
    //End Algo1: use backtracking
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    //Algo1: use math
    // for n=1 , count(1) = 10
    // for n=2, count(2) = 9*9 (here the second 9 is due to the possibility of using 0)
    // for n=3, count(3) = 9*9*8
    // for n=4, count(4) = 9*9*8*7
    // we need to add all the count() from 1 to 4 for getting count of numbers with unique digits
    // Time is O(1), Space O(1)
    //----------------------------------------------------------------------
    public int countNumbersWithUniqueDigitsAlgo2(int n) {
        if(n==0) {
            return 1;
        }
        int count = 10; // for n=1
        int prev = 9;
        int decrementVal = 9;
        for(int i=2; i<=n;i++){
            if(decrementVal >= 1) {
                prev *= decrementVal;
                count += prev;
                decrementVal--;
            }
        }
        return count;
    }

    //----------------------------------------------------------------------
    //End Algo1: use math
    //----------------------------------------------------------------------
}
