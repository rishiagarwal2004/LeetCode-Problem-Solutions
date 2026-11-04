class Solution {
    public int[] shuffle(int[] nums, int n) {
        int l = nums.length;
        int p1 = 0;
        int p2 = n;
        int[] arr = new int [l];
        for(int i =0;i<l;i++){
            if(i % 2 == 0){
                arr[i] = nums[p1];
                p1++;
            }else{
                arr[i] = nums[p2];
                p2++;
            }
        }
        return arr;
        
    }
}