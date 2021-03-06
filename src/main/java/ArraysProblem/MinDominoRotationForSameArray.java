package ArraysProblem;

/*
https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.



Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
 */
/*
Running time is O(n)
Space is O(1)
 */
public class MinDominoRotationForSameArray {
    public int minDominoRotations(int[] A, int[] B) {
        if(A.length != B.length){
            return -1;
        }
        int a_0 = checkDomino(A[0], A, B);
        int b_0 = checkDomino(B[0], A, B);
        if(a_0 == -1 && b_0 == -1){
            return -1;
        }
        return Math.min(a_0 == -1?Integer.MAX_VALUE:a_0, b_0 == -1?Integer.MAX_VALUE:b_0);
    }

    int checkDomino(int x, int[] A, int[] B){
        int rotationsA = 0;
        int rotationsB = 0;
        for(int i = 0; i<A.length; i++){
            if(A[i]!=x && B[i]!=x){
                return -1;
            }
            if(A[i] != x){
                rotationsA++;
            }
            if(B[i] != x){
                rotationsB++;
            }
        }
        return Math.min(rotationsA, rotationsB);
    }
}
