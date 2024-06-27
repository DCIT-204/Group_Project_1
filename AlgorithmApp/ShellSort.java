package AlgorithmApp;
// Shell Sort Algorithm class
public class ShellSort {
    // Shell Sort Algorithm
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
        // Shell sort has a time complexity of O(n^(3/2)) ie O(n^2)
        // Shell sort has a space complexity of O(1)
    }
}
