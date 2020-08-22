package Misc;

/*
https://leetcode.com/problems/nim-game/
You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

Example:

Input: 4
Output: false
Explanation: If there are 4 stones in the heap, then you will never win the game;
             No matter 1, 2, or 3 stones you remove, the last stone will always be
             removed by your friend.
 */
/*
Solution: When we have n=1,2,3 then we can win, but when n=4, whatever number of stones we take we still loose.
For n =5,6,7 we can take enough stones that n=4 for the next player and we win the game. At n=8, we loose again.
So the pattern is if n is not divisible by 4 then we can win.
 */
public class NimGame {
    public boolean canWinNim(int n) {
        return !(n%4==0);
    }
}
