class Solution {
    public boolean checkDivisibility(int n) {
        int m=n;
        int temp;
        int sum=0;
        int product=1;
        while(m>0){
         temp =m%10;
         sum +=temp;
         product *=temp;
         m/=10;
         
        }
        if (n%(sum+product)==0){
            return true;
        }
        return false;
    }
}