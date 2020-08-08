package MathProblems;

/*
https://leetcode.com/problems/bulb-switcher/
There are n bulbs that are initially off. You first turn on all the bulbs.
Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Input: 3
Output: 1
Explanation:
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.
 */
/*
A bulb ends up turned on if it is switched an odd number of times.
A bulb is left on only if it has an odd number of factors/divisors.
This only happens in case of perfect sq 6*6(here), 4*9, 36*1. As all the factors comes in pairs of 2.
So we need to find all the perfect squares <= n. which is sqrt(n).
 */
public class BulbSwitcher {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
