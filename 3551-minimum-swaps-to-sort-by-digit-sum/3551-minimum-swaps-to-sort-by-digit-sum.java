import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Element class ka array banayein
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], i, getDigitSum(nums[i]));
        }
        
        // Custom Sort: Pahle digit sum ke basis par, fir value ke basis par
        Arrays.sort(elements, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.val, b.val);
        });
        
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || elements[i].originalIndex == i) {
                continue;
            }
            
            int cycleSize = 0;
            int j = i;
            
            while (!visited[j]) {
                visited[j] = true;
                j = elements[j].originalIndex;
                cycleSize++;
            }
            
            if (cycleSize > 0) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    
    // Fixed: 'static' keyword lagaya aur line 68 ki error ko hata diya
    static class Element {
        int val;
        int originalIndex;
        int digitSum;
        
        Element(int val, int originalIndex, int digitSum) {
            this.val = val;
            this.originalIndex = originalIndex;
            this.digitSum = digitSum;
        }
    }
}
