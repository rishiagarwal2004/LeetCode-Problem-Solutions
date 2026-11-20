import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array so we can build consecutive sequences from smallest to largest
        Arrays.sort(nums);

        // dp.get(x) stores the maximum length of a consecutive sequence ending at value x
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (int num : nums) {
            // Option 1: Increase current element by 1 (becomes num + 1)
            // It can extend a consecutive sequence ending at num
            dp.put(num + 1, dp.getOrDefault(num, 0) + 1);

            // Option 2: Keep current element unchanged (stays num)
            // It can extend a consecutive sequence ending at num - 1
            dp.put(num, dp.getOrDefault(num - 1, 0) + 1);

            // Track the longest sequence seen so far
            maxLen = Math.max(maxLen, Math.max(dp.get(num), dp.get(num + 1)));
        }

        return maxLen;
    }
}
