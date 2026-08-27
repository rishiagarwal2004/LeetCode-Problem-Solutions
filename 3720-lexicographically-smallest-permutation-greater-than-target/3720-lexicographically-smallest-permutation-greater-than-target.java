import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) {
            totalCount[c - 'a']++;
        }

        // Try to match the longest possible prefix with target
        // Then branch off at index 'i' with a strictly greater character
        for (int i = n - 1; i >= -1; i--) {
            int[] currentCount = totalCount.clone();
            boolean validPrefix = true;
            StringBuilder sb = new StringBuilder();

            // 1. Verify if the prefix target[0...i] can be formed
            for (int j = 0; j <= i; j++) {
                int c = target.charAt(j) - 'a';
                if (currentCount[c] > 0) {
                    currentCount[c]--;
                    sb.append(target.charAt(j));
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // If we matched the entire target string, we cannot make it strictly greater
            if (i == n - 1) continue;

            // 2. Look for the next character at index i + 1 that is strictly greater than target[i + 1]
            int nextTargetChar = target.charAt(i + 1) - 'a';
            boolean foundGreater = false;

            for (int c = nextTargetChar + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    currentCount[c]--;
                    sb.append((char) ('a' + c));
                    foundGreater = true;
                    break;
                }
            }

            // 3. If found, fill the remaining slots with the smallest available characters
            if (foundGreater) {
                for (int c = 0; c < 26; c++) {
                    while (currentCount[c] > 0) {
                        sb.append((char) ('a' + c));
                        currentCount[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}
