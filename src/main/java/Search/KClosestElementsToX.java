package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/find-k-closest-elements/
Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

 */
/*
We will do a binary search and get an index of the element equalto or just lower than x.
Then using 2 pointers find the k closest elements

Running time O(log(n) + k)
space is O(k) for storing the output
 */
public class KClosestElementsToX {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int beforeIndex = getBeforeIndex(arr, x);
        if(beforeIndex == -1){
            return getList(Arrays.copyOfRange(arr, 0, k));
        } else if (beforeIndex == arr.length){
            return getList(Arrays.copyOfRange(arr, arr.length-k, arr.length));
        } else {
            // use 2 pointers
            int l = beforeIndex;
            int r = beforeIndex+1;
            while(k >0){
                if(l>=0 && r<arr.length){
                    if(Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)){
                        l--;
                    } else {
                        r++;
                    }
                } else if(l<0){
                    r++;
                } else {
                    l--;// gaurenteed that this will not go beyond 0 since k<=arr.length
                }
                --k;
            }
            return getList(Arrays.copyOfRange(arr, l+1, r));
        }
    }

    List<Integer> getList(int[] arr){
        List<Integer> ret = new ArrayList<>();
        for(int num: arr){
            ret.add(num);
        }
        return ret;
    }

    //Binary Search to find the Index of an element just below the x
    int getBeforeIndex(int[] arr, int x){
        if(arr[0]>x){
            return -1;
        } else if(arr[arr.length-1]<x){
            return arr.length;
        }
        int l = 0;
        int r = arr.length-1;
        while(l<r){
            int mid = l + (int)Math.ceil((r-l)/2.0);
            if(arr[mid] == x ){
                return mid;
            } else if(arr[mid] < x ){
                l = mid;
            } else{
                r = mid-1;
            }
        }
        return l;
    }
}
