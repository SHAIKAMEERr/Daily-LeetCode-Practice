class Solution {
    
    public int numTrees(int n) {

        int[] dp = new int[n + 1];
      
        dp[0] = 1;
      
        for (int nodeCount = 1; nodeCount <= n; nodeCount++) {

            for (int leftSubtreeSize = 0; leftSubtreeSize < nodeCount; leftSubtreeSize++) {

                int rightSubtreeSize = nodeCount - leftSubtreeSize - 1;
              
                dp[nodeCount] += dp[leftSubtreeSize] * dp[rightSubtreeSize];
            }
        }
      
        return dp[n];
    }
}