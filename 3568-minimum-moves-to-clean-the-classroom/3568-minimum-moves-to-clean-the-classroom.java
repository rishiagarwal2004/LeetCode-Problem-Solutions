import java.util.*;

class Solution {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];

        // Initialize id with -1
        for (int i = 0; i < m; i++) {
            Arrays.fill(id[i], -1);
        }

        int x = -1, y = -1;
        int p = 0;

        // Find S and assign IDs to L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'S') {
                    x = i;
                    y = j;
                }
                else if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = p++;
                }
            }
        }

        // No litter
        if (p == 0) {
            return 0;
        }

        int out = (1 << p) - 1;

        /*
         * save[x][y][energy][mask]
         *
         * Java does not have C++ vector like this,
         * so we use a 4D array.
         */
        int[][][][] save = new int[m][n][energy + 1][out + 1];

        // Initialize with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int e = 0; e <= energy; e++) {
                    Arrays.fill(save[i][j][e], -1);
                }
            }
        }

        save[x][y][energy][out] = 0;

        /*
         * State:
         * x = row
         * y = column
         * now = current energy
         * s = remaining litter mask
         */
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y, energy, out});

        while (!q.isEmpty()) {

            int[] curr = q.poll();

            x = curr[0];
            y = curr[1];
            int now = curr[2];
            int s = curr[3];

            // All litter collected
            if (s == 0) {
                return save[x][y][now][s];
            }

            int step = save[x][y][now][s] + 1;

            // Moving costs 1 energy
            now--;

            if (now < 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {

                int xx = x + dx[i];
                int yy = y + dy[i];

                // Outside classroom or wall
                if (xx < 0 || xx >= m ||
                    yy < 0 || yy >= n ||
                    classroom[xx].charAt(yy) == 'X') {

                    continue;
                }

                // Recharge if current cell is R
                int e;

                if (classroom[xx].charAt(yy) == 'R') {
                    e = energy;
                } else {
                    e = now;
                }

                // If current cell is L, remove it from mask
                int mask = 0;

                if (classroom[xx].charAt(yy) == 'L') {
                    mask = 1 << id[xx][yy];
                }

                int state;

                if ((s & mask) != 0) {
                    state = s ^ mask;
                } else {
                    state = s;
                }

                // If this state has not been visited
                if (save[xx][yy][e][state] < 0) {

                    save[xx][yy][e][state] = step;

                    q.offer(new int[]{
                        xx, yy, e, state
                    });
                }
            }
        }

        return -1;
    }
}