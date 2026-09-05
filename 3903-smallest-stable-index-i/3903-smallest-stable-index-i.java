class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] = nums[i...n-1] ka minimum
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Prefix ka maximum maintain karenge
        int prefixMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);

            int diff = prefixMax - suffixMin[i];

            if (diff <= k) {
                return i;
            }
        }

        return -1;
    }
}