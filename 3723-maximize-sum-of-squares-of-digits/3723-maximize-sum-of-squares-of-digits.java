class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }        
        StringBuilder ans = new StringBuilder();
        for (int d = 9; d >= 1; d--) {
            while (sum >= d) {
                ans.append(d);
                sum -= d;
            }
        }
        while (ans.length() < num) {
            ans.append('0');
        }
        
        return ans.toString();
    }
}
