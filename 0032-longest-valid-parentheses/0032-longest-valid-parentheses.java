class Solution {
    public int longestValidParentheses(String s) {
        if(s.length() == 0){
            return 0;
        }
        Stack <Integer> str = new Stack<>();
        str.push(-1);
        int count =0;

        for(int i = 0 ; i < s.length() ; i++){

            if(s.charAt(i) == '('){
                str.push(i);
            }
            else {
                str.pop();
                if (str.isEmpty()) {
                     str.push(i); 
                }
                else{
                    count = Math.max(count, i - str.peek());
                }
            }
        }
        return count;
        
    }
}