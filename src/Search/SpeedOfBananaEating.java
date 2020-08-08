package Search;

/*
https://leetcode.com/problems/koko-eating-bananas/
Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.



Example 1:

Input: piles = [3,6,7,11], H = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], H = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], H = 6
Output: 23


Note:

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9
 */
/*
Running time is O(Nlog(W)) where n is the number of piles and W is the max bananas in any pile
Space needed is O(1)
 */
public class SpeedOfBananaEating {
    public int minEatingSpeed(int[] piles, int H) {
        int maxInPile = 0;
        for(int i = 0; i<piles.length; i++){
            maxInPile = Math.max(maxInPile, piles[i]);
        }
        int stSpeed = 1;
        int endSpeed = maxInPile;
        while(stSpeed<endSpeed){
            int mid = stSpeed+(endSpeed-stSpeed)/2;
            int hoursTaken = getHoursTaken(piles, mid);
            if(hoursTaken>H){
                stSpeed = mid+1;
            } else {
                endSpeed = mid;
            }
        }
        return endSpeed;
    }

    int getHoursTaken(int[] piles, int eatingSpeed){
        int hoursTaken= 0;
        for(int i = 0; i<piles.length; i++){
            hoursTaken += (int)Math.ceil(piles[i]/(double)eatingSpeed);
        }
        return hoursTaken;
    }
}
