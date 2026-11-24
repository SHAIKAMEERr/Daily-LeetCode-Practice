class Solution {
   
    public int countDigitOne(int n) {
   
        int count = 0;

        for (long k = 1; k <= n; k *= 10) {

            long higher = n / (k * 10);
            long curr = (n / k) % 10;
            long lower = n % k;

            if (curr == 0) {
                count += higher * k;
            } 
            else if (curr == 1) {
                count += higher * k + lower + 1;
            } 
            else {
                count += (higher + 1) * k;
            }
        }
        return count;
    }
}