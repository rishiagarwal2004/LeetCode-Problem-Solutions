class Solution {
    public int reverse(int x) {
        int y=0;
        int j=x;
        while(j!=0){
            int temp=j%10;
            j= j/10;
            if (y > Integer.MAX_VALUE / 10 ||
               (y == Integer.MAX_VALUE / 10 && temp>7))
                return 0;
            if (y < Integer.MIN_VALUE / 10 ||
               (y == Integer.MIN_VALUE / 10 && temp < -8))
                return 0;
            y= y*10 + temp;
        }
        return y;
    }
}