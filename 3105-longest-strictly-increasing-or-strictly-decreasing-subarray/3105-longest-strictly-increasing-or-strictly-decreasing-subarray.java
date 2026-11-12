class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;
        for(int i = 0; i < n; i++){
            int inc = 1;
            int j = i+1;
            while(j < n && nums[j] > nums[j-1]){
                inc++;
                j++;
            }
            int dec = 1;
            j = i+1;
            while(j < n && nums[j] < nums[j-1]){
                dec++;
                j++;
            }
            int Max = Math.max(inc, dec);
             result = Math.max(result,Max); 

            
        }
        return result;
    }
}