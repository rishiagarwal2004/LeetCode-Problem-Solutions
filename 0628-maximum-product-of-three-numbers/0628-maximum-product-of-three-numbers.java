class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int op1= nums[n-1]*nums[n-2]*nums[n-3];
        int op2= nums[0] * nums[1] * nums[n - 1];
        return Math.max(op1, op2);

    }
}