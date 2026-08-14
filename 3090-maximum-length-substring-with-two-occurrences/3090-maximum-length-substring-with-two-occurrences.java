import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int j = 0; // Left pointer
        int ans = 0;
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
            while (mp.get(ch) > 2) {
                char leftChar = s.charAt(j);
                mp.put(leftChar, mp.get(leftChar) - 1);
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        
        return ans;
    }
}
