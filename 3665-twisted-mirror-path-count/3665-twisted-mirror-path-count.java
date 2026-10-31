
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // dp[i][j][0] = ways arriving while moving Right
        // dp[i][j][1] = ways arriving while moving Down
        int[][][] dp = new int[m + 1][n + 1][2];

        // Starting position
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int right = dp[i][j][0];
                int down = dp[i][j][1];

                if (grid[i][j] == 0) {

                    // Empty cell: both directions are possible
                    int total = (int) ((right + (long) down) % MOD);

                    dp[i][j + 1][0] =
                        (int) ((dp[i][j + 1][0] + (long) total) % MOD);

                    dp[i + 1][j][1] =
                        (int) ((dp[i + 1][j][1] + (long) total) % MOD);

                } else {

                    // Mirror: Right becomes Down
                    dp[i + 1][j][1] =
                        (int) ((dp[i + 1][j][1] + (long) right) % MOD);

                    // Mirror: Down becomes Right
                    dp[i][j + 1][0] =
                        (int) ((dp[i][j + 1][0] + (long) down) % MOD);
                }
            }
        }

        return (int) ((dp[m - 1][n - 1][0]
                     + (long) dp[m - 1][n - 1][1]) % MOD);
    }
}
