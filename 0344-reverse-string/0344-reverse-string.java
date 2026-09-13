class Solution {
    public void reverseString(char[] s) {
        char [] rs =  new char[s.length];
        int j=0;
        for(int i = s.length-1; i>=0;i--){
            rs[j] = s[i];
            j++;
        }
        for(int i = 0; i< s.length;i++){
            s[i] = rs[i];
        }

        
    }
}