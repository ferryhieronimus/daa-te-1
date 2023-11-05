import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 6, 2, 1, 5, 7, 8 };
        // other arrays to test
        // int[] arr = readArrayFromFile("datasets/sorted_small.txt", 512);
        // int[] arr = readArrayFromFile("datasets/sorted_small.txt", 512);
        // int[] arr = readArrayFromFile("datasets/randomized_small.txt", 512);
        // int[] arr = readArrayFromFile("datasets/reversed_small.txt", 512);
        // int[] arr = readArrayFromFile("datasets/sorted_medium.txt", 8192);
        // int[] arr = readArrayFromFile("datasets/randomized_medium.txt", 8192);
        // int[] arr = readArrayFromFile("datasets/reversed_medium.txt", 8192);
        // int[] arr = readArrayFromFile("datasets/sorted_large.txt", 65536);
        // int[] arr = readArrayFromFile("datasets/randomized_large.txt", 65536);
        // int[] arr = readArrayFromFile("datasets/reversed_large.txt", 65536);

        long startTime = System.nanoTime();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Choose one of the following sorting algorithms to test
        ShellSort.randomizedShellSort(arr);
        // MaxHeapSort.sort(arr);

        long endTime = System.nanoTime();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time (milliseconds): " + elapsedTime / 1000000.0);

        long actualMemUsed = afterUsedMem - beforeUsedMem;
        System.out.println("Memory used: " + actualMemUsed);

        try {
            isSorted(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void isSorted(int[] arr) throws Exception {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                throw new Exception("Array is not sorted");
            }
        }
    }

    public static int[] readArrayFromFile(String fileName, int size) {
        int[] integerArray = new int[size];
        int index = 0;

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()) {
                int myInteger = scanner.nextInt();
                integerArray[index] = myInteger;
                index++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return integerArray;
    }

    public static long[] profileSortingAlgorithm(Runnable sortingAlgorithm) {
        long startTime = System.nanoTime();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        sortingAlgorithm.run();

        long endTime = System.nanoTime();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long elapsedTime = endTime - startTime;
        return new long[] { elapsedTime, afterUsedMem - beforeUsedMem };
    }
}
