import java.util.List;

public class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits = new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; ++i) {
                bits[i] += (n >> i) & 1;
            }
        }
        
        long mod = 1_000_000_007;
        long sum = 0;
        
        for (int i = 0; i < k; ++i) {
            long num = 0;
            for (int j = 0; j < 32; ++j) {
                if (bits[j] > i) {
                    num |= (1L << j);
                }
            }
            sum = (sum + (num * num) % mod) % mod;
        }
        
        return (int) sum;
    }
}
