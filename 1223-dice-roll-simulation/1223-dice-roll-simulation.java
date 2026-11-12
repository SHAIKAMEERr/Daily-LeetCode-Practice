class Solution {
  
    private Integer[][][] f;
    private int[] rollMax;
    private final int MOD = 1_000_000_007;

    public int dieSimulator(int n, int[] rollMax) {
        // f[i][j][x]: 
        // i = current roll index (0 to n-1)
        // j = last rolled number (0 for initial/none, 1 to 6 for the face)
        // x = consecutive count of face j
  
        f = new Integer[n][7][16];
  
        this.rollMax = rollMax;
  
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int x) {
  
        if (i >= f.length) {
  
            return 1;
        }
  
        if (f[i][j][x] != null) {
  
            return f[i][j][x];
        }
  
        long ans = 0;
  
        for (int k = 1; k <= 6; ++k) {
  
            if (k != j) {
  
                ans += dfs(i + 1, k, 1);
            }
             else if (x < rollMax[j - 1]) {
            
                ans += dfs(i + 1, j, x + 1);
            }
        }
        ans %= MOD;
        
        return f[i][j][x] = (int) ans;
    }
}
