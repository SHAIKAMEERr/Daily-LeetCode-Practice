class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        int sum = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
            sum += x;
        }
        return maxVal * n - sum;
    }
}
