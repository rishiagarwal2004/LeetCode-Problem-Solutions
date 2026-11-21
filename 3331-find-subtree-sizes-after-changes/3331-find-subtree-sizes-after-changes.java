import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Build adjacency list for the original tree
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // Start DFS from root node 0
        dfs(0, -1);
        return ans;
    }

    private void dfs(int u, int origParent) {
        int charIdx = s.charAt(u) - 'a';
        
        // Save the previous closest ancestor with the same character for backtracking
        int prevAncestor = lastSeen[charIdx];
        
        // Determine the new parent: closest matching ancestor if it exists, else original parent
        int targetParent = (prevAncestor != -1) ? prevAncestor : origParent;
        
        // Update lastSeen to current node u before visiting children
        lastSeen[charIdx] = u;
        
        // Initialize current node's subtree size
        ans[u] = 1;
        
        for (int v : adj[u]) {
            dfs(v, u);
        }
        
        // Add current node's finalized subtree size to its target parent
        if (targetParent != -1) {
            ans[targetParent] += ans[u];
        }
        
        // Restore state (backtrack) before returning to parent
        lastSeen[charIdx] = prevAncestor;
    }
}
