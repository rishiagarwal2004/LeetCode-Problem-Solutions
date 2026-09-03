class Solution {
    public boolean uniformArray(int[] nums1) {
        int count = 0;
        for(int num : nums1){
            if(num % 2 ==0){
              count =count +1;
            }
        }
        if (count == nums1.length){
            return true;
        }
        else{
            Arrays.sort(nums1);
            if(nums1[0] % 2 ==0){
                return false;
            }
        }
        return true;
        
    }
}