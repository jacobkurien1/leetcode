package main.java.MathProblems;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?



Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6


Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
 */
/*
running time is O(n+m) where n and m are the size of the input sparse vectors
Space needed is O(n+m) to store the list of pairs
 */
public class DotProductOfSparseVectors {
    List<Pair> pairs;

    DotProductOfSparseVectors(int[] nums) {
        pairs = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                pairs.add(new Pair(i, nums[i]));
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(DotProductOfSparseVectors vec) {
        int dotProd = 0;
        int thisIndex = 0;
        int vecIndex = 0;
        while(thisIndex<pairs.size() && vecIndex<vec.pairs.size()){
            Pair thisPair = pairs.get(thisIndex);
            Pair vecPair = vec.pairs.get(vecIndex);
            if(thisPair.index == vecPair.index){
                dotProd += (thisPair.value * vecPair.value);
                thisIndex++; vecIndex++;
            } else if(thisPair.index<vecPair.index){
                thisIndex++;
            } else {
                vecIndex++;
            }
        }
        return dotProd;
    }

    class Pair {
        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    // Your SparseVector object will be instantiated and called as such:
    // SparseVector v1 = new SparseVector(nums1);
    // SparseVector v2 = new SparseVector(nums2);
    // int ans = v1.dotProduct(v2);
}
