package ArraysProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/problems/next-greater-element-iii/
Given a positive 32-bit integer n,
you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21


Example 2:

Input: 21
Output: -1
 */
/*
Running time is O(N)
Space is O(n)
Note: the approach can be simplified by using a charArray.
 */
public class NextGreaterElement {
    public int nextGreaterElement(int n) {
        List<Integer> nList = new ArrayList<>();
        while(n >0){
            nList.add(n%10);
            n /= 10;
        }
        int swappedIndex = -1;
        for(int i =1; i<nList.size(); i++){
            if(nList.get(i-1)>nList.get(i)){
                // i is the index that needs to be swapped
                swappedIndex = i;
                // find index whose value is just greater than i
                int indexValGreaterThanI = i-1;
                for(int j = i-1; j>=0; j--){
                    if(nList.get(j)>nList.get(i)){
                        indexValGreaterThanI = j;
                    } else {
                        break;
                    }
                }
                Collections.swap(nList, i, indexValGreaterThanI);
                break;
            }
        }
        if(swappedIndex == -1){
            return -1;
        }
        long ret = 0;
        int index = nList.size()-1;
        while(index>=swappedIndex){
            ret = Math.addExact(Math.multiplyExact(ret , 10) ,nList.get(index));
            index--;
        }
        index = 0;
        while(index<=swappedIndex-1){
            ret = Math.addExact(Math.multiplyExact(ret , 10) ,nList.get(index));
            index++;
        }

        return ret>Integer.MAX_VALUE?-1: (int)ret;
    }
}
