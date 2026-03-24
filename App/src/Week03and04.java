import java.util.*;

public class AccountLookupSystem {

    // 🔹 Linear Search (First Occurrence)
    public static int linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First -> Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Linear Search (Last Occurrence)
    public static int linearSearchLast(String[] arr, String target) {
        int comparisons = 0;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }

        System.out.println("Linear Last -> Comparisons: " + comparisons);
        return index;
    }

    // =========================================================
    // 🔹 BINARY SEARCH (ANY ONE MATCH)
    // =========================================================
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search -> Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search -> Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Find First Occurrence (Binary)
    public static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Find Last Occurrence (Binary)
    public static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Count occurrences using binary search
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // 🔹 Utility print
    public static void printArray(String label, String[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // =========================================================
    // 🔹 MAIN DRIVER
    // =========================================================
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        printArray("Input", logs);

        // 🔹 Linear Search
        int firstLinear = linearSearchFirst(logs, "accB");
        int lastLinear = linearSearchLast(logs, "accB");

        System.out.println("Linear First accB: index " + firstLinear);
        System.out.println("Linear Last accB: index " + lastLinear);

        // 🔹 Sort logs for Binary Search
        Arrays.sort(logs);
        printArray("Sorted Logs", logs);

        // 🔹 Binary Search
        int indexBinary = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary accB: index " + indexBinary);
        System.out.println("Count of accB: " + count);
    }
}