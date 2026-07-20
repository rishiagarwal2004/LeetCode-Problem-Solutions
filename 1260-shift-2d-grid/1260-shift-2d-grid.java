class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int size = m * n;

        int[] arr = new int[size];
        int ind = 0;

        // Flatten the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[ind++] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ind = 0;

        // Build shifted grid
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {

                int idx = ((ind - k) % size + size) % size;
                list.add(arr[idx]);
                ind++;
            }
            ans.add(list);
        }

        return ans;
    }
}