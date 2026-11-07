import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: sorted by weight, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Min-heap for busy servers: sorted by freeTime, then weight, then index
        // Each element: {weight, index, freeTime}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Initially, all servers are free at time 0
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i, 0});
        }

        int currentTime = 0;
        for (int i = 0; i < m; i++) {
            // The i-th task is available at second i. 
            // Current time must be at least the task's arrival time.
            currentTime = Math.max(currentTime, i);

            // 1. Move all completed servers from busy to free pool
            while (!busyServers.isEmpty() && busyServers.peek()[2] <= currentTime) {
                freeServers.offer(busyServers.poll());
            }

            // 2. If no servers are free, fast-forward time to the earliest freeing server
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[2];
                while (!busyServers.isEmpty() && busyServers.peek()[2] <= currentTime) {
                    freeServers.offer(busyServers.poll());
                }
            }

            // 3. Assign the task to the best available server
            int[] server = freeServers.poll();
            ans[i] = server[1]; // Store the server index in result
            
            // Update the server's next free time and push to busy heap
            server[2] = currentTime + tasks[i];
            busyServers.offer(server);
        }

        return ans;
    }
}
