class Solution {
   
    public int distinctSubseqII(String s) {
   
        final int MOD = 1_000_000_007;
   
        long[] endsWith = new long[26];
        long totalSubseqs = 0;

        for (char c : s.toCharArray()) {
   
            int idx = c - 'a';

            long newSubseqsEndingWithC = (totalSubseqs + 1) % MOD;
            
            totalSubseqs = (totalSubseqs + newSubseqsEndingWithC - endsWith[idx] + MOD) % MOD;
            
            endsWith[idx] = newSubseqsEndingWithC;
        }
        return (int) totalSubseqs;
    }
}
