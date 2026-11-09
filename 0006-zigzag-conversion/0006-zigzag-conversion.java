class Solution {
    public String convert(String s, int numRows) {
        // Edge case: if only one row, return the original string
        if (numRows == 1) {
            return s;
        }
      
        // Create an array of StringBuilders, one for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int row = 0; row < numRows; row++) {
            rows[row] = new StringBuilder();
        }
      
        // Initialize variables for zigzag traversal
        int currentRow = 0;  // Current row index
        int direction = -1;  // Direction of movement: -1 for up, 1 for down
      
        // Process each character in the input string
        for (char character : s.toCharArray()) {
            // Append current character to the current row
            rows[currentRow].append(character);
          
            // Change direction when reaching the top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                direction = -direction;
            }
          
            // Move to the next row based on current direction
            currentRow += direction;
        }
      
        // Concatenate all rows to form the final result
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
      
        return result.toString();
    }
}
