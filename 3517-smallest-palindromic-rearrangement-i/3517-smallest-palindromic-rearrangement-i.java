class Solution {
    public String smallestPalindrome(String s) {
        int n= s.length();
        int mid= n/2;
        char[] chars=s.toCharArray();
        Arrays.sort(chars,0,mid);
        for(int i=0;i<mid;i++){
            chars[n-i-1]=chars[i];
        }
        String result=new String(chars);
        return result;
        
    }
}