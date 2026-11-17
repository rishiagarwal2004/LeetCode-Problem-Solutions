import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int INF = 1_000_000;

        // 1. Precompute proper divisors for every possible substring length
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d <= len / 2; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // 2. Precompute minimum changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minEdits = INF;

                for (int d : divisors[len]) {
                    int edits = 0;
                    // Check each modulo group as an independent palindrome
                    for (int rem = 0; rem < d; rem++) {
                        int left = i + rem;
                        int right = j - (d - 1 - rem);
                        while (left < right) {
                            if (chars[left] != chars[right]) {
                                edits++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minEdits = Math.min(minEdits, edits);
                }
                cost[i][j] = minEdits;
            }
        }

        // 3. DP to partition s into k semi-palindromes
        // dp[i][p] = min changes for prefix of length i split into p parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each partition needs at least 2 characters, so i >= 2 * p
            for (int i = 2 * p; i <= n; i++) {
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[m][p - 1] + cost[m][i - 1] < dp[i][p]) {
                        dp[i][p] = dp[m][p - 1] + cost[m][i - 1];
                    }
                }
            }
        }

        return dp[n][k];
    }
}

