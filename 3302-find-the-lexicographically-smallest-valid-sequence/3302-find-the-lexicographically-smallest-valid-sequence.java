class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // last[j] stores the maximum index i in word1 such that 
        // the suffix of word2 from index j can be formed by a subsequence in word1[i...]
        int[] last = new int[m];
        java.util.Arrays.fill(last, -1);
        
        int i = n - 1;
        int j = m - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }
        
        int[] ans = new int[m];
        boolean canSkip = true; // Tracks if we can still make 1 character replacement
        j = 0; // Pointer for word2
        
        for (i = 0; i < n; i++) {
            if (j == m) break;
            
            // Case 1: Characters match perfectly
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } 
            // Case 2: Characters mismatch, but we can utilize our 1 skip/modification
            else if (canSkip && (j == m - 1 || i < last[j + 1])) {
                canSkip = false;
                ans[j] = i;
                j++;
            }
        }

        return j == m ? ans : new int[0];
    }
}
