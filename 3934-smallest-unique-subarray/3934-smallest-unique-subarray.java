import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 998_244_353L;
    private long[] h1, h2, p1, p2;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        long base = 100_003L + new Random().nextInt(1_000_000);

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * base + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * base + nums[i]) % MOD2;
            p1[i + 1] = (p1[i] * base) % MOD1;
            p2[i + 1] = (p2[i] * base) % MOD2;
        }
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueWindow(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    private boolean hasUniqueWindow(int len) {
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        for (int i = 0; i <= n - len; i++) {
            int j = i + len;
            long hash1 = (h1[j] - (h1[i] * p1[len]) % MOD1 + MOD1) % MOD1;
            long hash2 = (h2[j] - (h2[i] * p2[len]) % MOD2 + MOD2) % MOD2;
            long combinedKey = (hash1 << 32) | hash2;
            freq.put(combinedKey, freq.getOrDefault(combinedKey, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
