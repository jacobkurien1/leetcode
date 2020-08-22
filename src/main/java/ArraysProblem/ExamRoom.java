package ArraysProblem;

import java.util.Set;
import java.util.TreeSet;

/*
https://leetcode.com/problems/exam-room/
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in,
and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
​​​​​​​

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */
/*
Running time is O(people)
Space needed is O(people)
 */
public class ExamRoom {
    int n;
    Set<Integer> people;

    public ExamRoom(int N) {
        n = N;
        people = new TreeSet<Integer>();
    }

    public int seat() {
        if(people.isEmpty()){
            people.add(0);
            return 0;
        }
        int maxSpace =0;
        int maxPos = 0;
        int prevPos = -1;
        for(int pos: people){
            if(prevPos == -1){
                maxSpace = pos;
                maxPos = 0;
            }else if(maxSpace <(pos-prevPos)/2){
                maxSpace = (pos -prevPos)/2;
                maxPos = prevPos + (pos-prevPos)/2;
            }
            prevPos = pos;
        }
        if(prevPos != n-1){
            if(maxSpace<n-1-prevPos){
                maxSpace = n-1 -prevPos;
                maxPos = n-1;
            }
        }

        people.add(maxPos);
        return maxPos;
    }

    public void leave(int p) {
        people.remove(p);
    }
}
