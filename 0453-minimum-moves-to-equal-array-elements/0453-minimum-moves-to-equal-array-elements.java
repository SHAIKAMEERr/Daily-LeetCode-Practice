import java.util.Arrays;

class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        
        // Find the minimum value and total sum of the array
        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }
        
        // Apply the mathematical formula
        return (int) (sum - (long) min * nums.length);
    }
}
