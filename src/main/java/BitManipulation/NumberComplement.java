package BitManipulation;

/*
https://leetcode.com/problems/number-complement/
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.



Example 1:

Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.


Example 2:

Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.


Note:

The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
/*
Running time is O(1) as there are at max 36 digits in the int
Space is O(1)
 */
public class NumberComplement {
    public int findComplement(int num) {
        int complement = 0;
        int todo = num;
        int bit =1;
        while(todo>0){
            num = num^bit;
            bit <<=1;
            todo >>=1;
        }
        return num;
    }
}
