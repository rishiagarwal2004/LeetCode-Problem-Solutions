import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // 1. Verify if a palindrome is even possible
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";

        int halfLen = n / 2;
        char[] half = new char[halfLen];

        // 2. Try to greedily match the prefix of target's left half
        if (canBuild(0, half, count, target, n, oddChar)) {
            return buildFullString(half, oddChar, n);
        }

        return "";
    }

    private boolean canBuild(int idx, char[] half, int[] count, String target, int n, int oddChar) {
        int halfLen = half.length;
        if (idx == halfLen) {
            // Check if the exact prefix copy creates a strictly larger palindrome
            String full = buildFullString(half, oddChar, n);
            return full.compareTo(target) > 0;
        }

        char targetChar = target.charAt(idx);
        int targetIdx = targetChar - 'a';

        // Match the target character if available
        if (count[targetIdx] >= 2) {
            half[idx] = targetChar;
            count[targetIdx] -= 2;
            if (canBuild(idx + 1, half, count, target, n, oddChar)) {
                return true;
            }
            count[targetIdx] += 2; // Backtrack
        }

        // If matching failed, we MUST pick a character strictly larger than targetChar
        for (int c = targetIdx + 1; c < 26; c++) {
            if (count[c] >= 2) {
                half[idx] = (char) ('a' + c);
                count[c] -= 2;
                // Once we are strictly greater at this index, all remaining indices 
                // must pick the smallest available character to keep it lexicographically smallest.
                fillSmallest(idx + 1, half, count);
                return true;
            }
        }

        // If neither worked, backtrack up the stack to change a previous character to something larger
        return false;
    }

    // Helper to immediately fill the remaining spots with the smallest possible available characters
    private void fillSmallest(int startIdx, char[] half, int[] count) {
        int c = 0;
        for (int i = startIdx; i < half.length; i++) {
            while (count[c] < 2) {
                c++;
            }
            half[i] = (char) ('a' + c);
            count[c] -= 2;
        }
    }

    private String buildFullString(char[] half, int oddChar, int n) {
        StringBuilder sb = new StringBuilder();
        for (char c : half) sb.append(c);
        if (n % 2 != 0) {
            sb.append((char) ('a' + oddChar));
        }
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }
        return sb.toString();
    }
}
