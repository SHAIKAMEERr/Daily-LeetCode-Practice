class Solution {
   
    public int numOfSubarrays(int[] arr) {
      
        final int MOD = 1_000_000_007;
        
        int[] count = {1, 0};
        int result = 0;
        int prefixSum = 0;
        
        for (int num : arr) {
           
            prefixSum += num;
            
            int parity = prefixSum & 1;
            
            result = (result + count[parity ^ 1]) % MOD;
            
            count[parity]++;
        }
        
        return result;
    }
}
