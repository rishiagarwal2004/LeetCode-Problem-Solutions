class Solution {
    public boolean isHappy(int n) {
        while(true){
            int temp;
            int sum =0;
            int count = 0;
            int original =n;
            while(n > 0){
                temp = n%10;
                sum = sum + (temp * temp);
                n /= 10;
                count++;
            }
        if(count == 1){    
            if (original == 1 || original == 7){
                return true;

            }else{
                return false;
            }
        }
        n = sum;
    }
        
    }
}