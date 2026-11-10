import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // f[i] stores the minimum cost to reach step i
        int[] f = new int[n + 1];
        
        // Use a safe large value to prevent integer overflow
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(f, inf);
        
        // Base case: starting step 0 has 0 cost
        f[0] = 0;
        
        // Compute the minimum cost for each step sequentially
        for (int i = 1; i <= n; ++i) {
            // costs is 1-indexed in the problem description, 
            // so step i corresponds to costs[i - 1]
            int stepCost = costs[i - 1];
            
            // Look back up to 3 steps (i-1, i-2, i-3)
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                int jumpDistance = i - j;
                int jumpCost = stepCost + (jumpDistance * jumpDistance);
                
                f[i] = Math.min(f[i], f[j] + jumpCost);
            }
        }
        
        return f[n];
    }
}
