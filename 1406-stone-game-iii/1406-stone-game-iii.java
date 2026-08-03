class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        // dp1, dp2, dp3 store values for dp[i+1], dp[i+2], dp[i+3]
        int dp1 = 0, dp2 = 0, dp3 = 0;

        // Loop backwards from end to start
        for (int i = n - 1; i >= 0; i--) {
            int currDp = Integer.MIN_VALUE;
            int take = 0;

            // Option 1: Take 1 stone
            if (i + 1 <= n) {
                take += stoneValue[i];
                currDp = Math.max(currDp, take - dp1);
            }
            // Option 2: Take 2 stones
            if (i + 2 <= n) {
                take += stoneValue[i + 1];
                currDp = Math.max(currDp, take - dp2);
            }
            // Option 3: Take 3 stones
            if (i + 3 <= n) {
                take += stoneValue[i + 2];
                currDp = Math.max(currDp, take - dp3);
            }

            // Shift DP values
            dp3 = dp2;
            dp2 = dp1;
            dp1 = currDp;
        }

        // Result check based on Alice's score difference (dp1 holds dp[0])
        if (dp1 > 0) {
            return "Alice";
        } else if (dp1 < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}