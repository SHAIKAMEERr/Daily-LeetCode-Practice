class Solution {
   
    public int longestBalanced(String s) {
   
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
           
            int[] freq = new int[26];
           
            int distinctCount = 0;
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
           
                int idx = s.charAt(j) - 'a';
                
                if (freq[idx] == 0) {
           
                    distinctCount++;
                }
                
                freq[idx]++;
           
                maxFreq = Math.max(maxFreq, freq[idx]);
                
                int currentLen = j - i + 1;
           
                if (maxFreq * distinctCount == currentLen) {
           
                    maxLen = Math.max(maxLen, currentLen);
                }
            }
        }
        return maxLen;
    }
}