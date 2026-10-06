class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                open++;
            }else if(ch == ')'){
                if(open > 0){
                    open--;
                }else{
                    close++;
                }

            }else{
                close++;
            }

        }
        return open + close;
        
    }
}