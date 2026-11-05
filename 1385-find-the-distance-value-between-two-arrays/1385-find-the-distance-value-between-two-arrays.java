import java.util.Arrays;

public class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable fast binary lookup
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int val : arr1) {
            // Check if any element in arr2 falls in the range [val - d, val + d]
            if (isValid(arr2, val - d, val + d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean isValid(int[] arr, int lower, int upper) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Found an element within the prohibited distance window
            if (arr[mid] >= lower && arr[mid] <= upper) {
                return false; 
            } else if (arr[mid] < lower) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // No elements from arr2 fall within the invalid window
        return true;
    }
}
