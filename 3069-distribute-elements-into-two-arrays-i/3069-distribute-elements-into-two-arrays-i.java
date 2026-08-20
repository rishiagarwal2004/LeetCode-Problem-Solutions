import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] resultArray(int[] nums) {
        // Initialize two lists to store elements dynamically
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Operation 1: Append first element to arr1
        arr1.add(nums[0]);
        // Operation 2: Append second element to arr2
        arr2.add(nums[1]);
        
        // Process remaining elements from index 2 onwards
        for (int i = 2; i < nums.length; i++) {
            // Compare the last elements of both lists
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Prepare the result array with the same length as nums
        int[] result = new int[nums.length];
        int index = 0;
        
        // Concatenate arr1 elements into the result
        for (int num : arr1) {
            result[index++] = num;
        }
        // Concatenate arr2 elements into the result
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}
