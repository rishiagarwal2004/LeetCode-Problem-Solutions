import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        Set<Integer> presenceSet = new HashSet<>();

        // Track boundaries and store elements
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
            presenceSet.add(num);
        }

        List<Integer> result = new ArrayList<>();
        
        // Scan sequentially between the sequence range
        for (int i = minVal + 1; i < maxVal; i++) {
            if (!presenceSet.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }
}
