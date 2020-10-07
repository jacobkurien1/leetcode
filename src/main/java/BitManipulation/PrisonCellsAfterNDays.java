package main.java.BitManipulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/prison-cells-after-n-days/
There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)



Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation:
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:

Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]


Note:

cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
 */
/*
Running time is O(min(N, 2^k) where k is < 32 and N is the total number of cells
Space needed is O(2^k)
 */
public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<Integer> prevPattern = new HashSet<>();
        Map<Integer, Integer> patternOrder = new HashMap<>();
        int pow2 = 1;
        int numVal = 0;
        for(int i =0; i<cells.length; i++){
            if(cells[i] == 1){
                numVal += pow2;
            }
            pow2 *= 2;
        }

        int bitMask = 0;
        for(int i = 0; i<6; i++){
            bitMask = bitMask<<1;
            bitMask |= 1;
        }
        bitMask = bitMask << 1;

        for (int i =0; i<N; i++){
            int shiftLeft = numVal << 1;
            int shiftRight = numVal >>> 1;
            numVal = ~(shiftRight^shiftLeft) & bitMask;
            if(!prevPattern.contains(numVal)){
                prevPattern.add(numVal);
                patternOrder.put(i, numVal);
            }else{
                return getIntArray(patternOrder.get((N-1)%i), cells.length);
            }
        }
        return getIntArray(numVal, cells.length);
    }

    int[] getIntArray(int val, int len){
        int[] ret = new int[len];
        int retIndex = 0;
        while (val != 0){
            ret[retIndex++] = val & 1;
            val = val >>1;
        }
        return ret;
    }
}
