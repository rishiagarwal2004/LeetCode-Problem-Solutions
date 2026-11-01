class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = 0;
        for(int num : nums){
            sum += num;
            if(min > num){
                min = num;
            }
        }
        return sum - (nums.length * min);
    }
}