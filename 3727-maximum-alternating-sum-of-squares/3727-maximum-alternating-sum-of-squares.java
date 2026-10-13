class Solution {
    public long maxAlternatingSum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        int [] arr = new int [nums.length];
        int j = 0;
        while(left <= right){
            arr[j++] = nums[right--];
            if(left <= right){
                arr[j++] = nums[left++];
            }
        }
        long result = 0;
        for(int k = 0 ; k < arr.length ; k++){
            long sq = 1L * arr[k] * arr[k];
            if(k % 2 == 0){
               result += sq;
            } else{
                result -= sq;
            }
        }
        return result;
        
    }
}