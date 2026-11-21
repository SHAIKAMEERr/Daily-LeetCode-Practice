import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  
    private List<Integer>[] tree;
  
    private int[] ans;
    private int[] lastSeen;

    public int[] findSubtreeSizes(int[] parent, String s) {
  
        int n = parent.length;
        tree = new ArrayList[n];
  
        for (int i = 0; i < n; i++) {
  
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {

            tree[parent[i]].add(i);
        }

        ans = new int[n];
        lastSeen = new int[26];

        Arrays.fill(lastSeen, -1);

        dfs(0, -1, s);

        return ans;
    }

    private void dfs(int curr, int prevParent, String s) {
    
        int charIdx = s.charAt(curr) - 'a';
        
        int savedAncestor = lastSeen[charIdx];
        
        lastSeen[charIdx] = curr;
        
        ans[curr] = 1;

        for (int child : tree[curr]) {

            dfs(child, curr, s);
        }

        lastSeen[charIdx] = savedAncestor;

        int targetParent = (savedAncestor != -1) ? savedAncestor : prevParent;

        if (targetParent != -1) {

            ans[targetParent] += ans[curr];
        }
    }
}
