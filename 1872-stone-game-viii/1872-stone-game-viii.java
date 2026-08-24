public class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Calculate the prefix sums
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }
        
        // Step 2: Initialize DP from the last possible choice
        // If Alice/Bob takes all stones, they get pref[n - 1]
        int dp = pref[n - 1];
        
        // Step 3: Bottom-up DP from right to left
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, pref[i] - dp);
        }
        
        return dp;
    }
}
