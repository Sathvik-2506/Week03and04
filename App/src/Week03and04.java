import java.util.*;

public class TradeVolumeAnalysis {

    // 🔹 Trade Class
    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    // =========================================================
    // 🔹 MERGE SORT (ASCENDING, STABLE)
    // =========================================================
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // 🔹 Stable merge
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // =========================================================
    // 🔹 QUICK SORT (DESCENDING, IN-PLACE)
    // =========================================================
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Lomuto partition (DESC)
    private static int partition(Trade[] arr, int low, int high) {
        Trade pivot = arr[high]; // pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // DESCENDING: bigger values first
            if (arr[j].volume > pivot.volume) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // =========================================================
    // 🔹 MERGE TWO SORTED ARRAYS (ASCENDING)
    // =========================================================
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        int n = a.length, m = b.length;
        Trade[] result = new Trade[n + m];

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < n) result[k++] = a[i++];
        while (j < m) result[k++] = b[j++];

        return result;
    }

    // =========================================================
    // 🔹 TOTAL VOLUME
    // =========================================================
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    // =========================================================
    // 🔹 PRINT UTILITY
    // =========================================================
    public static void printArray(String label, Trade[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // =========================================================
    // 🔹 MAIN DRIVER
    // =========================================================
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        System.out.println("Original Trades:");
        printArray("Input", trades);

        // 🔹 Merge Sort (ASC)
        mergeSort(trades, 0, trades.length - 1);
        printArray("Merge Sorted (ASC)", trades);

        // 🔹 Quick Sort (DESC)
        quickSort(trades, 0, trades.length - 1);
        printArray("Quick Sorted (DESC)", trades);

        // 🔹 Merge two sorted lists (example)
        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 300)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 400)
        };

        Trade[] merged = mergeTwoSorted(morning, afternoon);
        printArray("Merged Sessions", merged);

        // 🔹 Total Volume
        int total = totalVolume(merged);
        System.out.println("Total Volume: " + total);
    }
}