class Solution {
    public int sumOfSquares(int[] nums) {
        int sum = 0;
        int n = nums.length;
        int i = 1;
        for(int num : nums){    
            if(n % i == 0){
                int sq = num * num;
                sum = sum + sq; 
            }
            i++;
        }
        return sum;
        
    }
}