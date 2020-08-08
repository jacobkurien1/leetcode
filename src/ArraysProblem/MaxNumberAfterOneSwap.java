package ArraysProblem;

import java.util.HashMap;

/*
https://leetcode.com/problems/maximum-swap/
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
 */
/*
Running time O(n)
Space needed is O(1 + O(log(n)) 1 as the elements in hashmap will be <=10
log(n) as we are converting num to string and storing as a charArray
 */
public class MaxNumberAfterOneSwap {
    public int maximumSwap(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i =0;i<numArr.length; i++){
            hm.put(numArr[i], i);
        }
        for(int i = 0;i<numArr.length;i++){
            for(char charVal = '9';charVal>numArr[i]; charVal--){
                int charIndex = hm.getOrDefault(charVal, -1);
                if(charIndex>i){
                    swap(numArr, i, charIndex);
                    return getInteger(numArr);
                }
            }
        }
        return num;
    }
    void swap(char[] numArr, int i, int j){
        char temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;
    }
    int getInteger(char[] numArr){
        int ret = 0;
        for(int i =0; i<numArr.length;i++){
            ret = ret*10 + (numArr[i] - '0');
        }
        return ret;
    }
}
