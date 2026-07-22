import java.util.*;

public class Solution {

    interface IntOp {
        int apply(int a, int b);
    }

    static class SparseTable {
        int[][] table;
        IntOp op;

        public SparseTable(int[] arr, IntOp op) {
            this.op = op;
            int n = arr.length;
            if (n == 0) {
                table = new int[0][0];
                return;
            }
            int k = 32 - Integer.numberOfLeadingZeros(n);
            table = new int[n][k];
            for (int i = 0; i < n; i++) {
                table[i][0] = arr[i];
            }
            for (int j = 1; j < k; j++) {
                for (int i = 0; i <= n - (1 << j); i++) {
                    table[i][j] = op.apply(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int query(int l, int r) {
            int j = 32 - Integer.numberOfLeadingZeros(r - l + 1) - 1;
            return op.apply(table[l][j], table[r - (1 << j) + 1][j]);
        }
    }

    static class Block {
        char type;
        int start;
        int end;
        int length;

        Block(char type, int start, int end, int length) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }

    static class Internal1Info {
        int start;
        int end;
        int ai;
        int left0Start;
        int right0End;
        int gainAConst;

        Internal1Info(int start, int end, int ai, int left0Start, int right0End, int gainAConst) {
            this.start = start;
            this.end = end;
            this.ai = ai;
            this.left0Start = left0Start;
            this.right0End = right0End;
            this.gainAConst = gainAConst;
        }
    }
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        List<Block> blocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            blocks.add(new Block(s.charAt(i), i, j - 1, j - i));
            i = j;
        }

        int m = blocks.size();
        int[] posToBlockIdx = new int[n];
        int[] zeroBlockFullLengths = new int[m];

        for (int idx = 0; idx < m; idx++) {
            Block b = blocks.get(idx);
            for (int k = b.start; k <= b.end; k++) {
                posToBlockIdx[k] = idx;
            }
            zeroBlockFullLengths[idx] = (b.type == '0') ? b.length : 0;
        }

        SparseTable stZero = new SparseTable(zeroBlockFullLengths, Math::max);

        List<Internal1Info> internal1List = new ArrayList<>();
        for (int idx = 1; idx < m - 1; idx++) {
            Block b = blocks.get(idx);
            if (b.type == '1') {
                Block left0 = blocks.get(idx - 1);
                Block right0 = blocks.get(idx + 1);
                int gainAConst = left0.length + right0.length;
                internal1List.add(new Internal1Info(b.start, b.end, b.length, left0.start, right0.end, gainAConst));
            }
        }

        int kInternal = internal1List.size();
        int[] gainAArr = new int[kInternal];
        int[] aiArr = new int[kInternal];
        int[] internalStarts = new int[kInternal];
        int[] internalEnds = new int[kInternal];

        for (int idx = 0; idx < kInternal; idx++) {
            Internal1Info info = internal1List.get(idx);
            gainAArr[idx] = info.gainAConst;
            aiArr[idx] = info.ai;
            internalStarts[idx] = info.start;
            internalEnds[idx] = info.end;
        }

        SparseTable stGainA = new SparseTable(gainAArr, Math::max);
        SparseTable stAi = new SparseTable(aiArr, Math::min);

        int base1s = 0;
        for (int c = 0; c < n; c++) {
            if (s.charAt(c) == '1') base1s++;
        }

        List<Integer> answer = new ArrayList<>(queries.length);

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int bl = posToBlockIdx[l];
            int br = posToBlockIdx[r];

            int M0 = 0;
            if (bl == br) {
                if (s.charAt(l) == '0') {
                    M0 = r - l + 1;
                }
            } else {
                if (s.charAt(l) == '0') {
                    M0 = Math.max(M0, blocks.get(bl).end - l + 1);
                }
                if (s.charAt(r) == '0') {
                    M0 = Math.max(M0, r - blocks.get(br).start + 1);
                }
                if (bl + 1 <= br - 1) {
                    M0 = Math.max(M0, stZero.query(bl + 1, br - 1));
                }
            }

            int u = lowerBound(internalStarts, l + 1);
            int v = upperBound(internalEnds, r - 1) - 1;

            int maxGain = 0;
            if (u <= v) {
                int minAi = stAi.query(u, v);
                maxGain = Math.max(maxGain, M0 - minAi);

                if (u + 1 <= v - 1) {
                    maxGain = Math.max(maxGain, stGainA.query(u + 1, v - 1));
                }

                Internal1Info infoU = internal1List.get(u);
                int bLeftU = infoU.start - Math.max(l, infoU.left0Start);
                int bRightU = Math.min(r, infoU.right0End) - infoU.end;
                maxGain = Math.max(maxGain, bLeftU + bRightU);

                if (u < v) {
                    Internal1Info infoV = internal1List.get(v);
                    int bLeftV = infoV.start - Math.max(l, infoV.left0Start);
                    int bRightV = Math.min(r, infoV.right0End) - infoV.end;
                    maxGain = Math.max(maxGain, bLeftV + bRightV);
                }
            }

            answer.add(base1s + maxGain);
        }

        return answer;
    }

    private static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
