package Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/nested-list-weight-sum-ii/
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf,
now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
/*
 a + ab + abc + abcd + ..
 Here abcd means a+b+c+d
 Here we can do a BFS on the nodes and keep adding to get the weighted sum.

 Running time = O(n) where n is all the count of all the elements in all the List of NestedIntegers
 Space is the Width of one level as those many elements will be stored in the queue at a time.
 */
public class NestedListWeightedSum {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<NestedInteger>();
        int levelSum = 0;
        for(NestedInteger nestInt : nestedList){
            if(nestInt.isInteger()){
                levelSum += nestInt.getInteger();
            } else {
                q.add(nestInt);
            }
        }
        int totalSum = levelSum;
        int prevSum = levelSum;
        while(!q.isEmpty()){
            int qSize = q.size();
            levelSum = 0;
            for(int i = 0; i<qSize; i++){
                NestedInteger uberInt = q.poll();
                for(NestedInteger nestInt : uberInt.getList()){
                    if(nestInt.isInteger()){
                        levelSum += nestInt.getInteger();
                    } else {
                        q.add(nestInt);
                    }
                }
            }
            int allLevelSum = prevSum + levelSum;
            prevSum = allLevelSum;
            totalSum += allLevelSum;
        }
        return totalSum;
    }
    
      // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
      interface NestedInteger {
          // Constructor initializes an empty nested list.
          //NestedInteger();
     
          // Constructor initializes a single integer.
          //public NestedInteger(int value);

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();
     
          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();
     
          // Set this NestedInteger to hold a single integer.
          public void setInteger(int value);
     
          // Set this NestedInteger to hold a nested list and adds a nested integer to it.
          public void add(NestedInteger ni);
     
          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
      }
}
