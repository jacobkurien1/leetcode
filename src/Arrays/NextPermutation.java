package Arrays;

/*
https://leetcode.com/problems/next-permutation/
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
/*
Algo:
1. go from right to left and find the index where the nums[i] < nums[i+1]
2. Now go from index i to nums.length and find the min number greater than nums[i]
3. swap the i and index found in 2.
4. now reverse nums from index i+1 to nums.length-1
Running time is O(n)
space is O(1)
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        boolean isSwapped = false;
        for(int i = nums.length-2; i>=0; i--){
            if(nums[i]<nums[i+1]){
                int indexToSwap = i+1;
                for(int j = indexToSwap +1 ; j<nums.length; j++){
                    if(nums[i]<nums[j] && nums[indexToSwap]>=nums[j]){
                        indexToSwap = j;
                    }
                }
                swap(nums, i, indexToSwap);
                reverse(nums, i+1);
                isSwapped = true;
                break;
            }
        }
        if(!isSwapped){
            reverse(nums, 0);
        }
    }

    void reverse(int[] nums, int st){
        for(int i = st; i<st+((nums.length-st)/2); i++){
            swap(nums, i, nums.length -1-(i-st));
        }
    }
    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
