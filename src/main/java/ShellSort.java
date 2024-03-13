public class ShellSort {
    public static int[] sort(int[] arr) {
        int n = arr.length;

        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                for (int j = i - gap; j >= 0 && arr[j + gap] < arr[j]; j -= gap) {
                    int tmp = arr[j];
                    arr[j] = arr[j + gap];
                    arr[j + gap] = tmp;
                }
            }
        }

        return arr;
    }
}
