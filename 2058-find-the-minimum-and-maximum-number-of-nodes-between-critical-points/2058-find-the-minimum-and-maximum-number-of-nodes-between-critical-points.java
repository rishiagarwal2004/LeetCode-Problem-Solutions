/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // If the list has fewer than 3 nodes, critical points cannot exist
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; // 0-indexed position for curr

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isMinima = curr.val < prev.val && curr.val < next.val;

            if (isMaxima || isMinima) {
                if (firstCriticalIndex == -1) {
                    // This is the first critical point we've encountered
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update minDistance with the distance from the immediate previous critical point
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                    // Update maxDistance with the distance from the absolute first critical point
                    maxDistance = currentIndex - firstCriticalIndex;
                }
                // Move the previous critical point pointer to the current one
                prevCriticalIndex = currentIndex;
            }

            // Move to the next triplet
            prev = curr;
            curr = next;
            currentIndex++;
        }

        // If fewer than two critical points were found, return [-1, -1]
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}
