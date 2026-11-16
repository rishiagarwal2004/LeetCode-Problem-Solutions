class Solution {
    public String digitSum(String s, int k) {
        while(s.length() > k){
            String str = "";
            int sum = 0;
            for(int i =0; i<s.length();i++){
                sum += s.charAt(i)-'0';
                if((i+1)%k == 0 || i == s.length()-1){
                    str += sum;
                    sum = 0;
                }
            }
            s = str;
        }
        return s;
    }
}