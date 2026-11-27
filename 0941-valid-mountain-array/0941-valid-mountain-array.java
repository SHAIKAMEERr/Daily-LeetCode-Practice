class Solution {
   
    public boolean validMountainArray(int[] arr) {
   
        int n = arr.length;
   
        if (n < 3) {
            return false;
        }

        int left = 0;
        int right = n - 1;

        while (left + 1 < n && arr[left] < arr[left + 1]) {

            left++;
        }

        while (right - 1 >= 0 && arr[right - 1] > arr[right]) {

            right--;
        }

        return left > 0 && right < n - 1 && left == right;
    }
}