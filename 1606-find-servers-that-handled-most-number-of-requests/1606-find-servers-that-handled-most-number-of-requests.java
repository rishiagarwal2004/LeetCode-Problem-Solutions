import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] times = new int[k]; // Count of requests handled by each server
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // {end_time, server_id}
        TreeSet<Integer> idleServers = new TreeSet<>();
        
        for (int i = 0; i < k; i++) {
            idleServers.add(i);
        }
        
        for (int i = 0; i < arrival.length; i++) {
            int currArrival = arrival[i];
            
            // Free up servers whose job has finished by the current arrival time
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] <= currArrival) {
                idleServers.add(busyHeap.poll()[1]);
            }
            
            if (idleServers.isEmpty()) {
                continue; // All servers are busy, drop the request
            }
            
            Integer server = idleServers.ceiling(i % k);
            if (server == null) {
                server = idleServers.first(); // Wrap around to index 0
            }
            
            idleServers.remove(server);
            times[server]++;
            busyHeap.offer(new int[] { currArrival + load[i], server });
        }
        
        int maxLoad = 0;
        for (int t : times) {
            maxLoad = Math.max(maxLoad, t);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (times[i] == maxLoad) {
                result.add(i);
            }
        }
        
        return result;
    }
}