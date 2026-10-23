class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[0] - arr[1];
        for(int i = 0 ; i < arr.length-1;i++){
            int diff_2 = arr[i] - arr[i+1];
            if(diff != diff_2){
                return false;
            }
        }
        return true;
        
    }
}