package Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]


Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
 */
/*
Solution: 1.Use a Priority queue as a minHeap on the start of the interval.
2.Add the employee intervals to the pq.
3.Poll the min and keep merging and getting the freetime interval.
4.Then add the next work interval for the employee for whom we polled in 3.
Running time O(n*log(E)) where n is all the intervals accross all employees and E is the number of employees
Space : O(E)
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> freeTime = new ArrayList<Interval>();
        Interval curr = null;
        PriorityQueue<Job> pq = new PriorityQueue<Job>((a, b) -> Integer.compare(a.interval.start, b.interval.start));
        for(int i = 0;i<schedule.size(); i++){
            pq.add(new Job(schedule.get(i).get(0), i, 0)); //Assumption: none of the employee schedule is empty
        }
        while(!pq.isEmpty()){
            Job job = pq.poll();
            Interval next = job.interval;
            if(curr == null){
                curr = next;
            } else{
                if(curr.end < next.start) {
                    freeTime.add(new Interval(curr.end, next.start));
                    curr = next;
                } else if(curr.end < next.end) {
                    curr.end = next.end;
                }
            }
            if(job.scheduleOrder+1 < schedule.get(job.employee).size()){
                pq.add(new Job(schedule.get(job.employee).get(job.scheduleOrder+1), job.employee, job.scheduleOrder+1));
            }
        }
        return freeTime;
    }

    class Job {
        Interval interval;
        int employee;
        int scheduleOrder;
        public Job(Interval interval, int employee, int scheduleOrder){
            this.interval = interval;
            this.employee = employee;
            this.scheduleOrder = scheduleOrder;
        }
    }

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
}
