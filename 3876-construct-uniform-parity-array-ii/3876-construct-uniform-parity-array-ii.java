class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean allEven = true;
        for(int num : nums1){
            min = Math.min(min , num);

            if (num %2 ==1){
                allEven = false;
            }
        }
        return allEven || min % 2 != 0;
    }
}