
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        // Check sorted order and find minimum and maximum characters
        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);

            if (ch < s.charAt(i - 1)) {
                sorted = false;
            }

            minChar = (char) Math.min(minChar, ch);
            maxChar = (char) Math.max(maxChar, ch);
        }

        // Case 1: Already sorted
        if (sorted) {
            return 0;
        }

        // Case 2: Length 2 and unsorted
        if (n == 2) {
            return -1;
        }

        // Case 3: One operation is sufficient
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Case 4: Minimum or maximum is in the middle
        for (int i = 1; i < n - 1; i++) {
            char ch = s.charAt(i);

            if (ch == minChar || ch == maxChar) {
                return 2;
            }
        }

        // Case 5: Neither endpoint has the correct extreme
        return 3;
    }
}
