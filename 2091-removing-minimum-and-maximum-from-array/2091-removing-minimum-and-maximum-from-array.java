import java.util.List;

class Solution {
    public int minimumDeletions(int[] nums) {
        int mini = 0, maxi = 0;
        final int n = nums.length;
        
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[mini]) {
                mini = i;
            } else if (nums[i] > nums[maxi]) {
                maxi = i;
            }
        }
        
        if (mini > maxi) {
            int temp = mini;
            mini = maxi;
            maxi = temp;
        }
        
        return Math.min(Math.min(maxi + 1, n - mini), mini + 1 + n - maxi);
    }
}
