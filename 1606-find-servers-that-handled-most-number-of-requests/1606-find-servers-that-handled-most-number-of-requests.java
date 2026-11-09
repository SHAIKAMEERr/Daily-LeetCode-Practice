import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> idleServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            idleServers.add(i);
        }

        // Min-heap storing int[] -> {end_time, server_id}
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int currLoad = load[i];

            // Free up servers that have finished their tasks by currTime
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] <= currTime) {
                int[] finished = busyHeap.poll();
                idleServers.add(finished[1]);
            }

            if (idleServers.isEmpty()) {
                // All servers are busy, drop this request
                continue;
            }

            // Find the preferred server starting from index (i % k)
            Integer targetServer = idleServers.ceiling(i % k);
            if (targetServer == null) {
                targetServer = idleServers.first(); // Wrap around to the smallest server ID
            }

            // Assign request to targetServer
            idleServers.remove(targetServer);
            busyHeap.offer(new int[] { currTime + currLoad, targetServer });
            requestCount[targetServer]++;
            maxRequests = Math.max(maxRequests, requestCount[targetServer]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
