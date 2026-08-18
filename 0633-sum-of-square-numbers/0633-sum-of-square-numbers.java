class Solution {
    public boolean judgeSquareSum(int c) {
        if(c==0 || c==1){
            return true;
        }
        long left=0;
        long right=(int)Math.sqrt(c);
        while(left<=right){
            long sum=left*left + right*right;
            if(sum==c){
                return true;
            }
            else if(sum <c){
                left++;
            }
            else{
                right--;
            }
        }
        return false;
        
    }
}