class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int total = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] != 1){
                total = Math.max(count, total);
                count = 0;
            }
            else{               
                count++;
                total = Math.max(count, total);
            }
        }
        return total;
    }
}