class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = nums[n/2];
        int sum = 0;
        int total =0;
        for(int num : nums){
            if(num < mid){
                sum = (mid - num)+sum;
            }
            else{
                total = (num - mid)+total;
            }
        }
        return sum + total;
        
    }
}