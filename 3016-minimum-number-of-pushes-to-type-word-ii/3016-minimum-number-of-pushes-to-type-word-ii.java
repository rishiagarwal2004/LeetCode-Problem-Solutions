import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {

        int[] frequency = new int[26];
        for (char ch : word.toCharArray()) {
            frequency[ch - 'a']++;
        }
        Arrays.sort(frequency);
        int totalPushes = 0;
        int count = 0;
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) break; 
            int pushesPerLetter = (count / 8) + 1; 
            
            totalPushes += frequency[i] * pushesPerLetter;
            count++; 
        }
        
        return totalPushes;
    }
}
