package Search;

import java.util.HashMap;

/*
https://leetcode.com/problems/online-election/
In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.



Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation:
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.


Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].
 */
/*
Running time is O(N + QlogN) where Q is the number of queries
Space needed is O(N)
 */
public class OnlineElection {
    int[] winningCandidate;
    int[] times;

    public OnlineElection(int[] persons, int[] times) {
        this.times = times;
        winningCandidate = new int[persons.length];
        int winnerVoteCount = 0;
        HashMap<Integer, Integer> candidateVotes = new HashMap<Integer, Integer>();
        for(int personIndex = 0; personIndex < persons.length; personIndex++){
            int candidateVote = candidateVotes.getOrDefault(persons[personIndex], 0)+1;
            candidateVotes.put(persons[personIndex], candidateVote);
            if(candidateVote>=winnerVoteCount){
                winningCandidate[personIndex] = persons[personIndex];
                winnerVoteCount = candidateVote;
            } else {
                winningCandidate[personIndex] = winningCandidate[personIndex-1];
            }
        }
    }

    public int q(int t) {
        int closestIndex = getIndexClosest(t);
        return winningCandidate[closestIndex];
    }

    int getIndexClosest(int time){
        int st = 0;
        int end = times.length-1;
        int maxIndexLessThanTime = end;
        while(st<=end){
            int mid = st + (end-st)/2;
            if (times[mid] < time){
                maxIndexLessThanTime = mid;
                st = mid+1;
            } else if(times[mid] >time){
                end = mid-1;
            } else {
                return mid;
            }
        }
        return maxIndexLessThanTime;
    }
}
