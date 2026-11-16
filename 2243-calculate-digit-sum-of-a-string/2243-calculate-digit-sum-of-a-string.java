class Solution {
   
    public String digitSum(String s, int k) {
   
        while (s.length() > k) {
   
            StringBuilder nextRound = new StringBuilder();
            
            for (int i = 0; i < s.length(); i += k) {
   
                int sum = 0;
   
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
   
                    sum += s.charAt(j) - '0';
                }
   
                nextRound.append(sum);
            }
            
            s = nextRound.toString();
        }
        
        return s;
    }
}
