package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
https://leetcode.com/problems/stamping-the-sequence/
You want to form a target string of lowercase letters.

At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.
You can make up to 10 * target.length turns.

For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.
(Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.
If the sequence is not possible to stamp, return an empty array.

For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.
Any answers specifying more than this number of moves will not be accepted.



Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
([1,0,2] would also be accepted as an answer, as well as some other answers.)
Example 2:

Input: stamp = "abca", target = "aabcaca"
Output: [3,0,1]


Note:

1 <= stamp.length <= target.length <= 1000
stamp and target only contain lowercase letters.
 */
/*
think in reverse, find the last stamp and keep moving around that stamp
Running time is O(target.length() * stamp.length()), cause at most we can have all target index in queue and we might have to check 2* stamp.length for each.
Space needed is O(target.length())
 */
public class StampingSequence {
    public int[] movesToStamp(String stamp, String target) {
        Stack<Integer> moves = new Stack<>();
        char[] targetArr = target.toCharArray();
        Queue<Integer> q = new LinkedList<>();
        int i = 0;
        while(i<=target.length()-stamp.length()){
            if(isMatch(stamp, targetArr, i)){
                stamp(targetArr, i, stamp.length());
                q.add(i);
                i += stamp.length();
            } else {
                i++;
            }
        }

        while(!q.isEmpty()){
            int stampedIndex = q.poll();
            moves.push(stampedIndex);
            int stIndex = (stampedIndex - (stamp.length()-1)<0?0:stampedIndex-(stamp.length()-1));
            int endIndex = (stampedIndex + stamp.length()-1>=target.length()?target.length()-1:stampedIndex + stamp.length()-1);
            while(stIndex<=endIndex){
                if(stIndex != stampedIndex){
                    if(isMatch(stamp, targetArr, stIndex)){
                        stamp(targetArr, stIndex, stamp.length());
                        q.add(stIndex);
                    }
                }
                stIndex++;
            }
        }
        if(isAnyCharacterPresent(targetArr)){
            return new int[0]; // case where target cannot be stamped
        }
        int[] movesArr = new int[moves.size()];
        int moveIndex = 0;
        while(!moves.isEmpty()){
            movesArr[moveIndex++] = moves.pop();
        }
        return movesArr;
    }

    boolean isMatch(String stamp, char[] target, int tIndex){
        int sIndex = 0;
        boolean charPresent = false;
        while(sIndex<stamp.length() && tIndex<target.length && (stamp.charAt(sIndex) == target[tIndex] || target[tIndex] == '?')){
            charPresent |= (stamp.charAt(sIndex) == target[tIndex]);
            sIndex++;
            tIndex++;
        }
        return sIndex == stamp.length() && charPresent;
    }

    void stamp(char[] target, int targetIndex, int stampLen){
        int i = targetIndex;
        while(i<Math.min(target.length, targetIndex + stampLen)){
            target[i++] = '?';
        }
    }

    boolean isAnyCharacterPresent(char[] target){
        for(char t: target){
            if(t!='?'){
                return true;
            }
        }
        return false;
    }
}
