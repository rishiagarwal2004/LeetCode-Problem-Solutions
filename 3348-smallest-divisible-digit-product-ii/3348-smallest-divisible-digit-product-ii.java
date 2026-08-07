import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Factorize t into prime factors 2, 3, 5, 7
        int[] target = new int[8]; // Indices 2, 3, 5, 7 will store counts
        long tempT = t;
        int[] primes = {2, 3, 5, 7};
        
        for (int p : primes) {
            while (tempT % p == 0) {
                target[p]++;
                tempT /= p;
            }
        }
        
        // If t has prime factors other than 2, 3, 5, 7, impossible
        if (tempT > 1) {
            return "-1";
        }
        
        int n = num.length();
        int firstZero = num.indexOf('0');
        int validPrefixLen = (firstZero == -1) ? n : firstZero;
        
        // Step 2: Check if num itself is valid (no zeros and product divisible by t)
        if (firstZero == -1) {
            int[] prefixFactors = new int[8];
            for (int i = 0; i < n; i++) {
                addFactors(prefixFactors, num.charAt(i) - '0', 1);
            }
            if (satisfies(prefixFactors, target)) {
                return num;
            }
        }
        
        // Calculate factor count of the valid prefix
        int[] currFactors = new int[8];
        for (int i = 0; i < validPrefixLen; i++) {
            addFactors(currFactors, num.charAt(i) - '0', 1);
        }
        
        // Step 3: Backtrack from right to left to find smallest replacement
        for (int i = n - 1; i >= 0; i--) {
            if (i < validPrefixLen) {
                addFactors(currFactors, num.charAt(i) - '0', -1);
            }
            
            if (i > validPrefixLen) {
                continue;
            }
            
            int startDigit = (i < validPrefixLen) ? (num.charAt(i) - '0' + 1) : 1;
            int spaceLeft = n - 1 - i;
            
            for (int d = startDigit; d <= 9; d++) {
                int[] req = new int[8];
                int[] dFactors = getFactors(d);
                
                for (int p : primes) {
                    req[p] = Math.max(0, target[p] - currFactors[p] - dFactors[p]);
                }
                
                List<Integer> neededDigits = minDigitsNeeded(req);
                if (neededDigits.size() <= spaceLeft) {
                    // Construct answer
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append(d);
                    
                    int ones = spaceLeft - neededDigits.size();
                    while (ones-- > 0) {
                        sb.append('1');
                    }
                    for (int digit : neededDigits) {
                        sb.append(digit);
                    }
                    return sb.toString();
                }
            }
        }
        
        // Step 4: If no valid number of length n exists, expand length to n + 1 (or required min length)
        List<Integer> neededDigits = minDigitsNeeded(target);
        int totalLen = Math.max(n + 1, neededDigits.size());
        
        StringBuilder sb = new StringBuilder();
        int ones = totalLen - neededDigits.size();
        while (ones-- > 0) {
            sb.append('1');
        }
        for (int digit : neededDigits) {
            sb.append(digit);
        }
        
        return sb.toString();
    }
    
    private void addFactors(int[] factors, int d, int sign) {
        if (d <= 1) return;
        int[] f = getFactors(d);
        factors[2] += sign * f[2];
        factors[3] += sign * f[3];
        factors[5] += sign * f[5];
        factors[7] += sign * f[7];
    }
    
    private int[] getFactors(int d) {
        int[] f = new int[8];
        int temp = d;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (temp % p == 0) {
                f[p]++;
                temp /= p;
            }
        }
        return f;
    }
    
    private boolean satisfies(int[] curr, int[] target) {
        return curr[2] >= target[2] && curr[3] >= target[3] && 
               curr[5] >= target[5] && curr[7] >= target[7];
    }
    
    private List<Integer> minDigitsNeeded(int[] req) {
        int c2 = req[2], c3 = req[3], c5 = req[5], c7 = req[7];
        
        int n9 = c3 / 2;
        int rem3 = c3 % 2;
        
        int n8 = c2 / 3;
        int rem2 = c2 % 3;
        
        int n6 = 0;
        if (rem2 > 0 && rem3 > 0) {
            n6 = 1;
            rem2--;
            rem3 = 0;
        }
        
        int n4 = rem2 / 2;
        rem2 %= 2;
        
        int n2 = rem2;
        int n3 = rem3;
        int n5 = c5;
        int n7 = c7;
        
        List<Integer> digits = new ArrayList<>();
        addMany(digits, 2, n2);
        addMany(digits, 3, n3);
        addMany(digits, 4, n4);
        addMany(digits, 5, n5);
        addMany(digits, 6, n6);
        addMany(digits, 7, n7);
        addMany(digits, 8, n8);
        addMany(digits, 9, n9);
        
        return digits;
    }
    
    private void addMany(List<Integer> list, int val, int count) {
        for (int i = 0; i < count; i++) {
            list.add(val);
        }
    }
}