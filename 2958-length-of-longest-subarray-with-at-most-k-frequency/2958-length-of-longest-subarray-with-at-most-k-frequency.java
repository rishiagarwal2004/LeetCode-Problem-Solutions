import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            int val = nums[right];
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            while (freqMap.get(val) > k) {
                int leftVal = nums[left];
                freqMap.put(leftVal, freqMap.get(leftVal) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}