class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = nums[0];
        for(int num : nums){
            if(max < num){
                max = num;
            }
        }
        for(int num : nums){
            sum = sum + (max - num);
        }
        return sum;
        
    }
}