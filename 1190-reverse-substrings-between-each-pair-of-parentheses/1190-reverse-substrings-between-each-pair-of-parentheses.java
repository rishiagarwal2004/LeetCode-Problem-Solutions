class Solution {
    public String reverseParentheses(String s) {
        char[] str = s.toCharArray();
        while (true) {
            int open = -1;
            int close = -1;
            for (int i = 0; i < str.length; i++) {
                if (str[i] == '(') {
                    open = i;
                } 
                else if (str[i] == ')') {
                    close = i;
                    break;
                }
            }
            if (open == -1 || close == -1) {
                break;
            }
            int left = open + 1;
            int right = close - 1;
            while (left < right) {
                char temp = str[left];
                str[left] = str[right];
                str[right] = temp;

                left++;
                right--;
            }
            StringBuilder temp = new StringBuilder();

            for (int i = 0; i < str.length; i++) {
                if (i != open && i != close) {
                    temp.append(str[i]);
                }
            }

            str = temp.toString().toCharArray();
        }

        return new String(str);
    }
}