package stack;

import java.util.Arrays;
import java.util.Stack;

/*
https://leetcode.com/problems/next-greater-element-ii/
Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element.
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
 */
/*
Here we will do 2 loops in the array and use a stack to store the elements whose next greatest is not known.
When we encounter a value greater than stack.peek(), we store this value in the stack.peek() index.
Running time is O(n)
Space requirement is O(n)
 */
public class NextGreatestElementInCircularArray {
    public int[] nextGreaterElements(int[] nums) {
        int[] nextG = new int[nums.length];
        Arrays.fill(nextG, -1);
        if(nums.length==0){
            return nextG;
        }
        Stack<Integer> st = new Stack<>();
        // Here we will will do a loop twice on the array nums.
        for(int i = 0; i<nums.length*2; i++){
            while(!st.isEmpty() && nums[st.peek()]<nums[i%nums.length]){
                int indexToFill = st.pop();
                if(nextG[indexToFill] == -1){
                    nextG[indexToFill] = nums[i%nums.length];
                }
            }
            st.push(i%nums.length);
        }
        return nextG;
    }
}
