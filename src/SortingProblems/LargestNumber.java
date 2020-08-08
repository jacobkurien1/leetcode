package SortingProblems;

import java.util.Arrays;

/*
https://leetcode.com/problems/largest-number/
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
 */
/*
Running time is O(nlogn)
Space needed is O(n)
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        for(int i = 0; i<nums.length; i++){
            numStr[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(numStr, (a, b)->{
            String ab = a+b;
            String ba = b+a;
            return ba.compareTo(ab);
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<numStr.length;i++){
            if(i ==0 && numStr[i].equals("0")){
                return "0"; // to handle case "00000"
            }
            sb.append(numStr[i]);
        }
        return sb.toString();
    }
}
