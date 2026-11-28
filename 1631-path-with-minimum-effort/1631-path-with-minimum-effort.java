import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
  
    public int minimumEffortPath(int[][] heights) {
  
        int rows = heights.length;
        int cols = heights[0].length;
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int[][] minEffort = new int[rows][cols];

        for (int[] row : minEffort) {
          
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        minEffort[0][0] = 0;
        minHeap.offer(new int[]{0, 0, 0});
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!minHeap.isEmpty()) {
         
            int[] current = minHeap.poll();
            int effort = current[0];
            int r = current[1];
            int c = current[2];
            
            if (r == rows - 1 && c == cols - 1) {
                return effort;
            }
            
            if (effort > minEffort[r][c]) {
                continue;
            }
            
            for (int[] dir : directions) {
             
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
               
                    int stepDiff = Math.abs(heights[r][c] - heights[nr][nc]);
                    int nextEffort = Math.max(effort, stepDiff);
                    
                    if (nextEffort < minEffort[nr][nc]) {
                  
                        minEffort[nr][nc] = nextEffort;
                        minHeap.offer(new int[]{nextEffort, nr, nc});
                    }
                }
            }
        }
        
        return 0;
    }
}
