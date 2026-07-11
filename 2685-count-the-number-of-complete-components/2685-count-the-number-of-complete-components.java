import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Graph banao
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {

                int[] res = dfs(i);

                int nodes = res[0];
                int degreeSum = res[1];

                // Undirected graph me edge do baar count hoti hai
                int actualEdges = degreeSum / 2;

                int expectedEdges = nodes * (nodes - 1) / 2;

                if (actualEdges == expectedEdges)
                    ans++;
            }
        }

        return ans;
    }

    private int[] dfs(int node) {

        visited[node] = true;

        int nodes = 1;
        int degreeSum = graph[node].size();

        for (int nei : graph[node]) {
            if (!visited[nei]) {
                int[] temp = dfs(nei);
                nodes += temp[0];
                degreeSum += temp[1];
            }
        }

        return new int[]{nodes, degreeSum};
    }
}