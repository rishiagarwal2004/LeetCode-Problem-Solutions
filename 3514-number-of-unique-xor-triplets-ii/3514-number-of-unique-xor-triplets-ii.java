class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int val : nums) {
            boolean[][] next = new boolean[4][MAX];

            // copy old states
            for (int i = 0; i < 4; i++) {
                System.arraycopy(dp[i], 0, next[i], 0, MAX);
            }

            // Take current value once
            for (int len = 0; len <= 2; len++) {
                for (int x = 0; x < MAX; x++) {
                    if (dp[len][x]) {
                        next[len + 1][x ^ val] = true;
                    }
                }
            }

            // Take current value twice
            // val ^ val = 0
            for (int len = 0; len <= 1; len++) {
                for (int x = 0; x < MAX; x++) {
                    if (dp[len][x]) {
                        next[len + 2][x] = true;
                    }
                }
            }

            // Take current value three times
            // val ^ val ^ val = val
            for (int x = 0; x < MAX; x++) {
                if (dp[0][x]) {
                    next[3][x ^ val] = true;
                }
            }

            dp = next;
        }

        int ans = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) ans++;
        }

        return ans;
    }
}