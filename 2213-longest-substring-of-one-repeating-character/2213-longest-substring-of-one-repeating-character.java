public class Solution {
    
    // Segment tree node structure
    private static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        int len;

        Node(int maxLen, int prefLen, int suffLen, int len) {
            this.maxLen = maxLen;
            this.prefLen = prefLen;
            this.suffLen = suffLen;
            this.len = len;
        }

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.len = 1;
        }
    }

    private Node[] tree;
    private char[] chars;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        // Build the segment tree initially
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            // Update character array and segment tree
            if (chars[idx] != c) {
                chars[idx] = c;
                update(1, 0, n - 1, idx);
            }

            // Root node holds the answer for the entire string range [0, n - 1]
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild], chars[mid], chars[mid + 1]);
    }

    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            // Leaf node update is essentially a re-initialization
            tree[node] = new Node(chars[idx]);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx);
        } else {
            update(rightChild, mid + 1, end, idx);
        }

        tree[node] = merge(tree[leftChild], tree[rightChild], chars[mid], chars[mid + 1]);
    }

    private Node merge(Node left, Node right, char midLeftChar, char midRightChar) {
        int totalLen = left.len + right.len;
        int prefLen = left.prefLen;
        int suffLen = right.suffLen;

        // If whole left part is identical and matches right's prefix
        if (left.prefLen == left.len && midLeftChar == midRightChar) {
            prefLen = left.len + right.prefLen;
        }

        // If whole right part is identical and matches left's suffix
        if (right.suffLen == right.len && midLeftChar == midRightChar) {
            suffLen = right.len + left.suffLen;
        }

        // Calculate maximum length inside this merged segment
        int maxLen = Math.max(left.maxLen, right.maxLen);
        if (midLeftChar == midRightChar) {
            maxLen = Math.max(maxLen, left.suffLen + right.prefLen);
        }

        return new Node(maxLen, prefLen, suffLen, totalLen);
    }
}