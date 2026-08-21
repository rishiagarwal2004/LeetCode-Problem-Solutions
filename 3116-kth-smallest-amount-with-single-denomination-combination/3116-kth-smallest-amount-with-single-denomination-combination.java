class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long l = 1, r = (long) 25 * k; // Upper bound based on max coin * k
        long ans = r;
        
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (count(coins, mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    
    private long count(int[] coins, long mx) {
        long total = 0;
        int n = coins.length;
        // Iterate through all non-empty subsets using bitmask
        for (int i = 1; i < (1 << n); i++) {
            long lcmVal = 1;
            int bitCount = 0;
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    bitCount++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > mx) {
                        lcmVal = mx + 1; // Cap to avoid overflow and break early
                        break;
                    }
                }
            }
            
            if (bitCount % 2 == 1) {
                total += mx / lcmVal;
            } else {
                total -= mx / lcmVal;
            }
        }
        return total;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
