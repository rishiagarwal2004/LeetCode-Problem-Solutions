class Solution {
    private int m, n;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited land cell (value > 0)
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        // Mark the current cell as visited by setting it to 0
        long sum = grid[r][c];
        grid[r][c] = 0;

        // Explore 4-directionally connected neighbors
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                sum += dfs(grid, nr, nc);
            }
        }
        return sum;
    }
}
