class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for(int i =0 ; i< s.length() ; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                if(!stack.isEmpty()){
                    result.append(ch);
                }
                stack.push(ch);
            }else{
                if(stack.size() > 1){
                    result.append(ch);
                }
                stack.pop();
            }
        }
        return result.toString();
        
    }
}