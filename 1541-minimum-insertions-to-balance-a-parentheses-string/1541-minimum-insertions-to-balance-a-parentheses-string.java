class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int openBrackets = 0;
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            if (s.charAt(i) == '(') {
                openBrackets++;
                i++;
            } else {
                // We encounter a ')'
                // Check if the next character is also a ')' to form a pair
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2; // Consume both ')'
                } else {
                    ans++;  // Insert a missing ')' to make it a pair
                    i++;    // Consume the single ')'
                }
                
                // Match the pair with an opening '('
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    ans++;  // Insert a missing opening '('
                }
            }
        }
        
        // Each remaining unmatched '(' needs 2 closing ')'
        ans += openBrackets * 2;
        return ans;
    }
}
