import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings chronologically by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Min-heap for unused rooms: stores room indices
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }
        
        // Min-heap for engaged rooms: stores [endTime, roomIndex]
        // Sorted primarily by endTime, secondarily by roomIndex
        PriorityQueue<long[]> engagedRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        
        int[] meetingCount = new int[n];
        
        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;
            
            // Release rooms whose meetings have finished before or at the current meeting's start time
            while (!engagedRooms.isEmpty() && engagedRooms.peek()[0] <= start) {
                unusedRooms.offer((int) engagedRooms.poll()[1]);
            }
            
            if (!unusedRooms.isEmpty()) {
                // Case 1: An idle room is available immediately
                int room = unusedRooms.poll();
                meetingCount[room]++;
                engagedRooms.offer(new long[]{end, room});
            } else {
                // Case 2: No rooms available, delay meeting until the earliest room finishes
                long[] earliestFreeRoom = engagedRooms.poll();
                long newStart = earliestFreeRoom[0];
                int room = (int) earliestFreeRoom[1];
                
                meetingCount[room]++;
                engagedRooms.offer(new long[]{newStart + duration, room});
            }
        }
        
        // Find the room with the maximum meeting count (lowest index on tie)
        int maxMeetingsRoom = 0;
        for (int i = 1; i < n; i++) {
            if (meetingCount[i] > meetingCount[maxMeetingsRoom]) {
                maxMeetingsRoom = i;
            }
        }
        
        return maxMeetingsRoom;
    }
}
