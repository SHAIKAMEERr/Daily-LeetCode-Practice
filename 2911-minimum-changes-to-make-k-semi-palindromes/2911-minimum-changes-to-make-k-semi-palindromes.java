import java.util.*;

class Solution {
   
    public int minimumChanges(String s, int k) {
   
        int n = s.length();
        
        List<Integer>[] divisors = new List[n + 1];

        for (int len = 1; len <= n; len++) {

            divisors[len] = new ArrayList<>();

            for (int d = 1; d < len; d++) {

                if (len % d == 0) {

                    divisors[len].add(d);

                }
            }
        }

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) { // Length must be >= 2

                cost[i][j] = getMinChanges(s, i, j, divisors[j - i + 1]);
            }
        }

        int[][] dp = new int[n + 1][k + 1];

        int INF = 1_000_000;

        for (int[] row : dp) {

            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[j][p - 1] != INF) {

                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    private int getMinChanges(String s, int start, int end, List<Integer> divs) {

        int minChanges = Integer.MAX_VALUE;

        int len = end - start + 1;

        for (int d : divs) {

            int changes = 0;

            for (int offset = 0; offset < d; offset++) {

                int left = start + offset;

                int right = start + len - d + offset;

                while (left < right) {

                    if (s.charAt(left) != s.charAt(right)) {

                        changes++;
                    }

                    left += d;

                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }

        return minChanges;
    }
}