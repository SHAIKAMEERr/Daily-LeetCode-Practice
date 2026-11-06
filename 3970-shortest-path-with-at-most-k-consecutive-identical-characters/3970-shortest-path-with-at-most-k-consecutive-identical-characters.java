import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build the adjacency list for the directed graph
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[] {e[1], e[2]}); // {neighbor, weight}
        }

        // dist[u][cur] stores minimum weight to reach node u with trailing run length 'cur'
        int[][] dist = new int[n][k + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        // Node 0 starts with a consecutive run length of 1
        dist[0][1] = 0;
        
        // Min-Heap stores arrays of format: {distance, node, current_streak}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, 0, 1});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int d = p[0], u = p[1], cur = p[2];

            // If we reach the target node, it's guaranteed to be the shortest valid path
            if (u == n - 1) {
                return d;
            }

            // Skip if a better path to this exact state was already processed
            if (d > dist[u][cur]) {
                continue;
            }

            // Explore neighbors
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                
                // Determine the next character streak length
                int nxt = labels.charAt(v) != labels.charAt(u) ? 1 : cur + 1;
                int nd = d + w;

                // Move forward if it's within the 'k' constraint and yields a shorter path
                if (nxt <= k && nd < dist[v][nxt]) {
                    dist[v][nxt] = nd;
                    pq.offer(new int[] {nd, v, nxt});
                }
            }
        }

        return -1;
    }
}
