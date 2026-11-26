class Solution {
    public boolean canAliceWin(int n) {
        int i = 0;
        while( n >= (10 -i)){
            n = n- (10 -i);
            i++;
        }
        return i % 2 == 1;
        
    }
}