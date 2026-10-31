import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Map to store the characters and their most recent index positions
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        // 'right' expands the sliding window scan line
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is already in the map, move the left pointer 
            // right past the previous occurrence (only if it falls inside the current window)
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update/insert the current character's index position
            map.put(currentChar, right);

            // Calculate the current unique window size and update the maximum
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
