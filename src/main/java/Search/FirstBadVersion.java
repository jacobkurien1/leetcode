package Search;

/*
https://leetcode.com/problems/first-bad-version/
You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check.
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad.
Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int firstBadVersion = -1;
        int st = 1;
        int end = n;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(isBadVersion(mid)){
                firstBadVersion = mid;
                end = mid-1;
            } else {
                st = mid+1;
            }
        }
        return firstBadVersion;
    }

    boolean isBadVersion(int val){
        return true;// This method is given in question and hence just a stub
    }
}
