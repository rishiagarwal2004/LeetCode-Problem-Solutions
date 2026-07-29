import java.util.*;

class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String mid = "";
        int[] half = new int[26];
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }

        if (countWays(half) < k) return "";

        StringBuilder left = new StringBuilder();

        while (halfLen > 0) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    halfLen--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long res = 1;
        int rem = total;

        for (int x : cnt) {
            if (x == 0) continue;
            res *= comb(rem, x);
            if (res >= LIMIT) return LIMIT;
            rem -= x;
        }

        return res;
    }

    private long comb(int n, int r) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans >= LIMIT) return LIMIT;
        }

        return ans;
    }
}