import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // f[i] stores the minimum cost to reach step i
        int[] f = new int[n + 1];
        
        // Use a safe value for infinity to prevent integer overflow during addition
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(f, inf);
        
        // Base case: starting at step 0 costs 0
        f[0] = 0; 
        
        // Compute minimum cost for each subsequent step
        for (int i = 1; i <= n; ++i) {
            int currentStepCost = costs[i - 1]; // costs array is 1-indexed relative to steps
            
            // Look back at most 3 steps (i-1, i-2, i-3)
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                int jumpDistance = i - j;
                int transitionCost = currentStepCost + (jumpDistance * jumpDistance);
                
                f[i] = Math.min(f[i], f[j] + transitionCost);
            }
        }
        
        return f[n];
    }
}
