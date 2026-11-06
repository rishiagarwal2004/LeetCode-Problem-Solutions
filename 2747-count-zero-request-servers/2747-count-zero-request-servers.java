import java.util.Arrays;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        // 1. Sort logs based on time (index 1)
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 2. Store queries with their original index to process them offline
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i]; // Query time
            sortedQueries[i][1] = i;          // Original index
        }
        // Sort queries by time
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Sliding window variables
        int[] serverCount = new int[n + 1]; // Tracks frequency of server requests in the current window
        int activeServers = 0;             // Number of unique servers with >= 1 request in the window
        int left = 0;                      // Left pointer for the window
        int right = 0;                     // Right pointer for the window
        
        // 4. Process each query
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            int windowStart = queryTime - x;
            int windowEnd = queryTime;
            
            // Expand window: Add logs that fall within the query's upper bound
            while (right < logs.length && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }
            
            // Shrink window: Remove logs that fall outside the query's lower bound
            while (left < logs.length && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // Unused/Zero-request servers = Total servers - Active servers
            ans[originalIndex] = n - activeServers;
        }
        
        return ans;
    }
}
