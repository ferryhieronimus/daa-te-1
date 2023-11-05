/**
 * Taken from
 * https://www.geeksforgeeks.org/heap-sort/
 * and
 * https://www.programiz.com/dsa/heap-sort
 * with some modifications
 */
public class MaxHeapSort {

    public static void sort(int arr[]) {
        int n = arr.length;

        // build a max heap first
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int arr[], int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && arr[leftChild] > arr[largest])
            largest = leftChild;

        if (rightChild < n && arr[rightChild] > arr[largest])
            largest = rightChild;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
