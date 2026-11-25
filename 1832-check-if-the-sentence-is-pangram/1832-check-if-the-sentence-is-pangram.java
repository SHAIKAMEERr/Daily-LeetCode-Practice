class Solution {
  
    public boolean checkIfPangram(String sentence) {
  
        if (sentence.length() < 26) {
            return false;
        }
        
        boolean[] seen = new boolean[26];
        int uniqueCount = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
  
            int index = sentence.charAt(i) - 'a';
  
            if (!seen[index]) {
                seen[index] = true;
                uniqueCount++;
  
                // Exit early as soon as all 26 letters are found
                if (uniqueCount == 26) {
                    return true;
                }
            }
        }      
        return false;
    }
}