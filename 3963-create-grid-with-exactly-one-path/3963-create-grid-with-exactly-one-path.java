import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // 1. Initialize all cells with obstacles '#'
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // 2. Open up the entire top row (index 0) with '.'
        Arrays.fill(grid[0], '.');
        
        // 3. Open up the entire rightmost column (index n - 1) with '.'
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // 4. Convert the char matrix into the required String[] format
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }
        
        return ans;
    }
}
