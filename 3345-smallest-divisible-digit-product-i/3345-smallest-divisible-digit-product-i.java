class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int m=1;
            int temp=n;
            while(temp>0){
                m *=temp%10;
                temp /=10;
            }
            if(m%t==0){
                return n;
            }
            n++;
        }
    }
}