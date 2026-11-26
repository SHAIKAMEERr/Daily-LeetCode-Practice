class Solution {
   
    public boolean canAliceWin(int n) {
   
        int stonesToRemove = 10;
        int turns = 0;

        while (n >= stonesToRemove) {
   
            n -= stonesToRemove;
            stonesToRemove--;
            turns++;
        }
        return turns % 2 == 1;
    }
}
