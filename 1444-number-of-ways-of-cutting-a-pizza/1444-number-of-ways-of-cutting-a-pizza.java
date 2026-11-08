import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int m, n;
    private int[][] prefixSum;
    private Integer[][][] memo;

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        prefixSum = new int[m + 1][n + 1];
        memo = new Integer[m][n][k + 1];

        // Build 2D prefix sum for apples
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int hasApple = (pizza[i].charAt(j) == 'A') ? 1 : 0;
                prefixSum[i][j] = hasApple 
                                + prefixSum[i + 1][j] 
                                + prefixSum[i][j + 1] 
                                - prefixSum[i + 1][j + 1];
            }
        }

        return dp(0, 0, k - 1);
    }

    private int dp(int r, int c, int cutsLeft) {
        // Base case: 0 cuts left, check if current piece has at least 1 apple
        if (cutsLeft == 0) {
            return (prefixSum[r][c] > 0) ? 1 : 0;
        }

        if (memo[r][c][cutsLeft] != null) {
            return memo[r][c][cutsLeft];
        }

        long waysCount = 0;

        // Try horizontal cuts
        for (int nextR = r + 1; nextR < m; nextR++) {
            // Top piece has apples: prefixSum[r][c] - prefixSum[nextR][c] > 0
            if (prefixSum[r][c] - prefixSum[nextR][c] > 0) {
                waysCount = (waysCount + dp(nextR, c, cutsLeft - 1)) % MOD;
            }
        }

        // Try vertical cuts
        for (int nextC = c + 1; nextC < n; nextC++) {
            // Left piece has apples: prefixSum[r][c] - prefixSum[r][nextC] > 0
            if (prefixSum[r][c] - prefixSum[r][nextC] > 0) {
                waysCount = (waysCount + dp(r, nextC, cutsLeft - 1)) % MOD;
            }
        }

        return memo[r][c][cutsLeft] = (int) waysCount;
    }
}
