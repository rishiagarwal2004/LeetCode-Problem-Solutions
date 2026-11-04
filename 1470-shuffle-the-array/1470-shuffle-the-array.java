class Solution {
    public int[] shuffle(int[] nums, int n) {
        int l = nums.length;
        int[] firstHalf = Arrays.copyOfRange(nums, 0, n);
        int[] secondHalf = Arrays.copyOfRange(nums, n, l);
        int[] arr = new int [l];
        for(int i =0;i<l;i++){
            if(i % 2 == 0){
                arr[i] = firstHalf[i / 2];
            }else{
                arr[i] = secondHalf[i / 2];
            }
        }
        return arr;
        
    }
}