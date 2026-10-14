class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum exceeds the maximum possible sum (9 * num), return ""
        if (num * 9 < sum) {
            return "";
        }
        
        StringBuilder answer = new StringBuilder();
        
        // Greedily place the largest possible digits from 9 down to 1
        for (int d = 9; d >= 1; d--) {
            while (sum >= d) {
                answer.append(d);
                sum -= d;
            }
        }
        
        // Pad with '0' until the length reaches 'num'
        while (answer.length() < num) {
            answer.append('0');
        }
        
        return answer.toString();
    }
}
