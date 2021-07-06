package PatienceSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/russian-doll-envelopes/
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.



Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

Example 2:

Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1



Constraints:

    1 <= envelopes.length <= 5000
    envelopes[i].length == 2
    1 <= wi, hi <= 104


 */
/*
This is the 2-D version of Longest Increasing subsequence problem
Sort with width. for case where the width is same, sort the heights in descending order.
We do descending order, because we are not sure which doll to select but we have to make sure
that the dolls with same width are not selected as the next dolls.
Then this becomes a standard LIS problem
Running time is O(nlogn)
Space is O(n)
 */
public class RussianDolls {
    public int maxEnvelopes(int[][] envelopes) {
        // Sort by width and make it 1D for Longest Increasing subsequence problem
        Arrays.sort(envelopes, (a, b)-> {
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        List<Integer> heights = new ArrayList<>();
        for(int i = 0; i<envelopes.length; i++){
            heights.add(envelopes[i][1]);
        }
        // Now do LIS on heights
        List<Integer> tails = new ArrayList<>();
        for(int height: heights){
            if(tails.isEmpty() || tails.get(tails.size()-1)<height){
                tails.add(height);
            } else {
                int st = 0;
                int end = tails.size()-1;
                int index =-1;
                while(st<=end){
                    int mid = st + (end-st)/2;
                    if(tails.get(mid)>=height){
                        index = mid;
                        end = mid-1;
                    } else {
                        st = mid+1;
                    }
                }
                tails.set(index, height);
            }
        }
        return tails.size();
    }
}
