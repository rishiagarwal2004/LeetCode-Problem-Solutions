import java.util.*;

class Solution {
    private int n;
    private List<Integer>[] adj;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer;
    private int LOG;
    private char[] currentChars;

    // Segment Tree with Lazy Propagation for Range XOR Updates
    private class SegmentTree {
        int[] tree;
        int[] lazy;
        int size;

        SegmentTree(int n) {
            this.size = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }

        void push(int node) {
            if (lazy[node] != 0) {
                tree[2 * node] ^= lazy[node];
                lazy[2 * node] ^= lazy[node];
                tree[2 * node + 1] ^= lazy[node];
                lazy[2 * node + 1] ^= lazy[node];
                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int l, int r, int val) {
            if (r < start || end < l) return;
            if (l <= start && end <= r) {
                tree[node] ^= val;
                lazy[node] ^= val;
                return;
            }
            push(node);
            int mid = (start + end) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);
        }

        int query(int node, int start, int end, int idx) {
            if (start == end) return tree[node];
            push(node);
            int mid = (start + end) / 2;
            if (idx <= mid) {
                return query(2 * node, start, mid, idx);
            } else {
                return query(2 * node + 1, mid + 1, end, idx);
            }
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.currentChars = s.toCharArray();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // Calculate maximum binary lifting limit
        LOG = 0;
        while ((1 << LOG) <= n) LOG++;
        
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        timer = 0;

        // Step 1: Precalculate LCA tables and Euler Tour entry/exit times
        dfsLCA(0, 0, 0);

        // Step 2: Build the initial root-to-node prefix masks
        int[] initialMasks = new int[n];
        dfsMasks(0, 0, 0, initialMasks);

        // Step 3: Populate the Segment Tree using the flattened Euler Tour positions
        SegmentTree segTree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            segTree.update(1, 0, n - 1, tin[i], tin[i], initialMasks[i]);
        }

        List<Boolean> result = new ArrayList<>();

        // Step 4: Process Queries
        for (String q : queries) {
            String[] tokens = q.split(" ");
            if (tokens[0].equals("update")) {
                int u = Integer.parseInt(tokens[1]);
                char c = tokens[2].charAt(0);
                
                int oldCharMask = 1 << (currentChars[u] - 'a');
                int newCharMask = 1 << (c - 'a');
                int xorDiff = oldCharMask ^ newCharMask;
                
                currentChars[u] = c;
                // A single node update flips the XOR prefix mask of its entire subtree range
                segTree.update(1, 0, n - 1, tin[u], tout[u], xorDiff);
            } else {
                int u = Integer.parseInt(tokens[1]);
                int v = Integer.parseInt(tokens[2]);
                
                int lcaNode = getLCA(u, v);
                int maskU = segTree.query(1, 0, n - 1, tin[u]);
                int maskV = segTree.query(1, 0, n - 1, tin[v]);
                
                // XOR out everything above LCA; re-add the LCA character mask itself
                int pathMask = maskU ^ maskV ^ (1 << (currentChars[lcaNode] - 'a'));
                
                // Check if at most one bit is set to 1 (can form a palindrome)
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }

    private void dfsLCA(int u, int p, int d) {
        tin[u] = timer++;
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        for (int v : adj[u]) {
            if (v != p) {
                dfsLCA(v, u, d + 1);
            }
        }
        tout[u] = timer - 1;
    }

    private void dfsMasks(int u, int p, int currentMask, int[] initialMasks) {
        currentMask ^= (1 << (currentChars[u] - 'a'));
        initialMasks[u] = currentMask;
        for (int v : adj[u]) {
            if (v != p) {
                dfsMasks(v, u, currentMask, initialMasks);
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
}
