class Solution {
    public int findGCD(int[] nums) {
        int a=nums[0];
        int b=nums[nums.length-1];
        for(int num:nums){
            if(num<a){
                a=num;
            }
            if(num>b){
                b=num;
            }
        }
        return gcd(a,b);
    }
        public static int gcd(int a, int b) {
         if(b==0) 
           return a;
         return  gcd(b,a%b); 

    }
}