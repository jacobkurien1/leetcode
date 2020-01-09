package Search;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
/*
Find the partition in the smaller array and then calculate the partition in the larger array such that:
1. the size of elements in left side of both arrays is equal or 1 greater than right side.
2. leftPart1 <=rightPart2 && leftPart2<=rightPart1, indicates that
    the median is avg(max(leftpart1, leftpart2), min(rightpart1, rightpart2)) for even number of elements
    the median is max(leftPart1, leftpart2) for odd number of elements

if 2. is not satisfied move the partition in the smaller array to get 2. satisfied.

Running time is O(log(min(array1.length, array2.length))
Space is O(1)
 */
public class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length){
            return findMedian(nums1, nums2);
        }
        return findMedian(nums2, nums1);
    }

    double findMedian(int[] n1, int[] n2){
        //Assumption: n1.length <= n2.length
        int s1 = 0;
        int e1 = n1.length;
        while(true){
            int part1 = (s1 + e1 + 1)/2;
            int part2 = (n1.length + n2.length + 1)/2 - part1;

            int leftPart1 = (part1-1<0)?Integer.MIN_VALUE:n1[part1-1];
            int rightPart1 = (part1==n1.length)?Integer.MAX_VALUE:n1[part1];

            int leftPart2 = (part2-1<0)?Integer.MIN_VALUE:n2[part2-1];
            int rightPart2 = (part2==n2.length)?Integer.MAX_VALUE:n2[part2];
            boolean isEven = (n1.length + n2.length)%2 == 0;
            if(leftPart1 <=rightPart2 && leftPart2<=rightPart1){
                if(isEven){
                    return (Math.max(leftPart1, leftPart2) + Math.min(rightPart1, rightPart2))/2.0;
                }
                return Math.max(leftPart1, leftPart2);
            } else if (leftPart1 > rightPart2){
                e1 = part1-1;
            } else {
                s1 = part1 + 1;
            }
        }
    }
}
