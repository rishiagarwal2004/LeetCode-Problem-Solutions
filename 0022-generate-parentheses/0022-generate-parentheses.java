class Solution {
    private List<String> result = new ArrayList<>();
    private int pairCount;
    public List<String> generateParenthesis(int n) {
        this.pairCount = n;
        backtrack(0, 0, "");
        return result;
    }
    private void backtrack(int openCount, int closeCount, String currentString) {
        if (openCount > pairCount || closeCount > pairCount || openCount < closeCount) {
            return;
        }
        if (openCount == pairCount && closeCount == pairCount) {
            result.add(currentString);
            return;
        }
        backtrack(openCount + 1, closeCount, currentString + "(");
        backtrack(openCount, closeCount + 1, currentString + ")");
    }
}
