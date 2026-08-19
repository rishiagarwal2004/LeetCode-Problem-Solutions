import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reserved = new HashMap<>();
        
        // Map row to a bitmask of occupied seats
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            reserved.put(row, reserved.getOrDefault(row, 0) | (1 << col));
        }
        
        // Rows with no reservations can automatically take 2 families
        int count = (n - reserved.size()) * 2;
        
        for (int mask : reserved.values()) {
            // Check availability using exact bitwise masks (1-indexed bits)
            // 0b0000111100 -> bits 2, 3, 4, 5
            // 0b1111000000 -> bits 6, 7, 8, 9
            // 0b0011110000 -> bits 4, 5, 6, 7
            boolean left = (mask & 0b0000111100) == 0;  
            boolean right = (mask & 0b1111000000) == 0; 
            boolean middle = (mask & 0b0011110000) == 0; 
            
            if (left && right) {
                count += 2;
            } else if (left || right || middle) {
                count += 1;
            }
        }
        
        return count;
    }
}
