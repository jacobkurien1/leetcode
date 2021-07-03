
package TreeMapProblems;

import java.util.*;

/*
https://leetcode.com/problems/range-module/
A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.

A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

Implement the RangeModule class:

RangeModule() Initializes the object of the data structure.
void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).


Example 1:

Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)


Constraints:

1 <= left < right <= 109
At most 104 calls will be made to addRange, queryRange, and removeRange.
 */
/*
The add and removeRange is O(n) whereas queryRange() is O(log(n))
Space needed is O(n)
 */
public class RangeModule {
    TreeMap<Integer, Integer> ranges;

    public RangeModule() {
        ranges = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer leftFloor = ranges.floorKey(left);
        Integer rightFloor = ranges.floorKey(right);
        if(leftFloor == null && rightFloor  == null){
            ranges.put(left, right);
        } else if (leftFloor == null || left > ranges.get(leftFloor)) {
            ranges.put(left, Math.max(right, ranges.get(rightFloor)));
        } else {
            ranges.put(leftFloor, Math.max(right, ranges.get(rightFloor)));
        }
        cleanupIntermediate(left, right, false, true);
    }

    private void cleanupIntermediate(int left, int right, boolean leftInclusive, boolean rightInclusive){
        Set<Integer> keysToRemove = new HashSet(ranges.subMap(left, leftInclusive, right, rightInclusive).keySet());
        ranges.keySet().removeAll(keysToRemove);
    }

    public boolean queryRange(int left, int right) {
        Integer leftFloor = ranges.floorKey(left);
        if(leftFloor == null){
            return false;
        }
        return ranges.get(leftFloor)>= right;
    }

    public void removeRange(int left, int right) {
        Integer leftFloor = ranges.floorKey(left);
        Integer rightFloor = ranges.floorKey(right);
        Map<Integer, Integer> mp = new HashMap<>();
        if(leftFloor != null && ranges.get(leftFloor)>left){
            mp.put(leftFloor, left);
        }
        if ( rightFloor != null && ranges.get(rightFloor)>right){
            mp.put(right, ranges.get(rightFloor));
        }
        ranges.putAll(mp);
        cleanupIntermediate(left, right, true, false);
    }
}
