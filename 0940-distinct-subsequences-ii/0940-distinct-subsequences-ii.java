class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            long diff = (total - dp[c] + 1 + mod) % mod;
            dp[c] = (dp[c] + diff) % mod;
            total = (total + diff) % mod;
        }

        return (int) total;
    }
}