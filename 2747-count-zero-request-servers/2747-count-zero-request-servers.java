import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort logs by timestamp ascending
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; ++i) {
            qs[i][0] = queries[i]; // query time
            qs[i][1] = i;          // original index
        }
        // Sort queries by query time ascending
        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));
        
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] ans = new int[m];
        int j = 0, k = 0;
        
        for (int[] q : qs) {
            int r = q[0];
            int i = q[1];
            int l = r - x;
            
            // Expand window to include logs up to time r
            while (k < logs.length && logs[k][1] <= r) {
                cnt.merge(logs[k][0], 1, Integer::sum);
                k++;
            }
            
            // Shrink window to exclude logs older than l (i.e. < l)
            while (j < logs.length && logs[j][1] < l) {
                int serverId = logs[j][0];
                cnt.put(serverId, cnt.get(serverId) - 1);
                if (cnt.get(serverId) == 0) {
                    cnt.remove(serverId);
                }
                j++;
            }
            
            // Number of servers with zero requests = total servers (n) - active servers in map size
            ans[i] = n - cnt.size();
        }
        
        return ans;
    }
}
