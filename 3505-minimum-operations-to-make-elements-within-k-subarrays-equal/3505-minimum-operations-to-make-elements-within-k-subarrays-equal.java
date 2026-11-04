import java.util.*;

class Solution {
    class MedianTracker {
        private final TreeMap<Long, Integer> lower = new TreeMap<>();
        private final TreeMap<Long, Integer> upper = new TreeMap<>();
        private long lowerSum = 0;
        private long upperSum = 0;
        private int lowerSize = 0;
        private int upperSize = 0;

        public void add(long num) {
            if (lower.isEmpty() || num <= lower.lastKey()) {
                lower.put(num, lower.getOrDefault(num, 0) + 1);
                lowerSum += num;
                lowerSize++;
            } else {
                upper.put(num, upper.getOrDefault(num, 0) + 1);
                upperSum += num;
                upperSize++;
            }
            balance();
        }

        public void remove(long num) {
            if (lower.containsKey(num)) {
                lowerSum -= num;
                lowerSize--;
                if (lower.put(num, lower.get(num) - 1) == 1) {
                    lower.remove(num);
                }
            } else {
                upperSum -= num;
                upperSize--;
                if (upper.put(num, upper.get(num) - 1) == 1) {
                    upper.remove(num);
                }
            }
            balance();
        }

        private void balance() {
            while (lowerSize < upperSize) {
                Map.Entry<Long, Integer> first = upper.firstEntry();
                long val = first.getKey();
                upperSum -= val;
                upperSize--;
                if (upper.put(val, first.getValue() - 1) == 1) {
                    upper.remove(val);
                }
                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSum += val;
                lowerSize++;
            }
            while (lowerSize > upperSize + 1) {
                Map.Entry<Long, Integer> last = lower.lastEntry();
                long val = last.getKey();
                lowerSum -= val;
                lowerSize--;
                if (lower.put(val, last.getValue() - 1) == 1) {
                    lower.remove(val);
                }
                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSum += val;
                upperSize++;
            }
        }

        public long getMinOps() {
            long median = lower.lastKey();
            return (median * lowerSize - lowerSum) + (upperSum - median * upperSize);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1; // number of possible windows of size x
        long[] cost = new long[m];
        
        MedianTracker tracker = new MedianTracker();
        for (int i = 0; i < x; i++) {
            tracker.add(nums[i]);
        }
        cost[0] = tracker.getMinOps();
        
        for (int i = x; i < n; i++) {
            tracker.remove(nums[i - x]);
            tracker.add(nums[i]);
            cost[i - x + 1] = tracker.getMinOps();
        }

        // DP table: dp[i][j] = min ops using j subarrays from first i windows/positions
        long[][] dp = new long[m + 1][k + 1];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE / 2);
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            long minPrev = Long.MAX_VALUE / 2;
            for (int i = 1; i <= m; i++) {
                // dp[i][j] can come from not picking window i-1: dp[i-1][j]
                // or picking window ending around here with offset x
                minPrev = Math.min(minPrev, dp[Math.max(0, i - x)][j - 1] + cost[i - 1]);
                dp[i][j] = Math.min(dp[i - 1][j], minPrev);
            }
        }

        return dp[m][k];
    }
}
