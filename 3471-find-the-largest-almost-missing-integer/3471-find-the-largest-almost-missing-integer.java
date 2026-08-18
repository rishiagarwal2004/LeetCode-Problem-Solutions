class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k == 1
        if (k == 1) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int ans = -1;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    ans = Math.max(ans, entry.getKey());
                }
            }

            return ans;
        }

        // Case 2: k == n
        if (k == n) {
            int ans = nums[0];

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        // Case 3: 1 < k < n
        int first = nums[0];
        int last = nums[n - 1];

        int firstCount = 0;
        int lastCount = 0;

        for (int num : nums) {
            if (num == first) {
                firstCount++;
            }

            if (num == last) {
                lastCount++;
            }
        }

        if (firstCount == 1 && lastCount == 1) {
            return Math.max(first, last);
        }

        if (firstCount == 1) {
            return first;
        }

        if (lastCount == 1) {
            return last;
        }

        return -1;
    }
}