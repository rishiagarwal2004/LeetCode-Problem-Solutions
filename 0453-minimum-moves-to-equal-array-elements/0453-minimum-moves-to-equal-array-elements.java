class Solution {
    public int minMoves(int[] nums) {
         int n = nums.length;
        int min = nums[0];
        int sum = 0;
        for(int num : nums){
            sum += num;
            if(min > num){
                min = num;
            }
        }
        return sum - (n * min);
    }
}