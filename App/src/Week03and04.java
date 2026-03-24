import java.util.*;

public class RiskThresholdLookup {

    // 🔹 Linear Search (unsorted)
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Search -> Comparisons: " + comparisons);
        return -1;
    }

    // =========================================================
    // 🔹 Binary Search: Find insertion point (lower_bound)
    // =========================================================
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Binary (lower_bound) -> Comparisons: " + comparisons);
        return low;
    }

    // =========================================================
    // 🔹 Upper Bound (first element > target)
    // =========================================================
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // =========================================================
    // 🔹 Floor (largest ≤ target)
    // =========================================================
    public static Integer floor(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length) return arr[arr.length - 1];
        if (arr[idx] == target) return arr[idx];
        if (idx == 0) return null;

        return arr[idx - 1];
    }

    // =========================================================
    // 🔹 Ceiling (smallest ≥ target)
    // =========================================================
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length) return null;
        return arr[idx];
    }

    // 🔹 Utility print
    public static void printArray(String label, int[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // =========================================================
    // 🔹 MAIN DRIVER
    // =========================================================
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int[] sorted = {10, 25, 50, 100};

        int target = 30;

        System.out.println("Unsorted Risk Bands:");
        printArray("Input", unsorted);

        // 🔹 Linear Search
        int linearResult = linearSearch(unsorted, target);
        System.out.println("Linear result for " + target + ": " + linearResult);

        System.out.println("\nSorted Risk Bands:");
        printArray("Sorted", sorted);

        // 🔹 Binary Search - insertion point
        int insertPos = lowerBound(sorted, target);
        System.out.println("Insertion Position for " + target + ": " + insertPos);

        // 🔹 Floor & Ceiling
        Integer floorVal = floor(sorted, target);
        Integer ceilVal = ceiling(sorted, target);

        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
    }
}