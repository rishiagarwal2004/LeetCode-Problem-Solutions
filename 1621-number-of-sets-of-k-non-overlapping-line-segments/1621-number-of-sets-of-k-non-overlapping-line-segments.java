class Solution {
  static final long MOD = 1_000_000_007L;

  public int numberOfSets(int n, int k) {
    int N = n + k - 1;
    int R = 2 * k;

    R = Math.min(R, N - R);

    long[] inv = new long[R + 1];

    if(R >= 1) inv[1] = 1;

    for(int i = 2; i <= R; i++)
      inv[i] = MOD - (MOD / i) * inv[(int)(MOD % i)] % MOD;

    long result = 1;

    for(int i = 1; i <= R; i++) {
      result = result * (N - R + i) % MOD;
      result = result * inv[i] % MOD;
    }

    return (int)result;
  }
}