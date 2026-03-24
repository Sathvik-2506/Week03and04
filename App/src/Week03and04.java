import java.util.*;
import java.time.LocalTime;

public class TransactionAuditSystem {

    // 🔹 Transaction Class
    static class Transaction {
        String id;
        double fee;
        LocalTime timestamp;

        public Transaction(String id, double fee, String time) {
            this.id = id;
            this.fee = fee;
            this.timestamp = LocalTime.parse(time);
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // 🔹 Bubble Sort (for ≤100 records) - by fee
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination
            if (!swapped) break;
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (for 100–1000 records) - by fee + timestamp
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare by fee, then timestamp
            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.isAfter(key.timestamp)))) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort completed.");
    }

    // 🔹 Outlier Detection (> $50)
    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        return outliers;
    }

    // 🔹 Utility to print list
    public static void printList(String label, List<Transaction> list) {
        System.out.println(label + ": " + list);
    }

    // 🔹 Main Driver
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        printList("Input", transactions);

        int size = transactions.size();

        // 🔹 Choose sorting strategy
        if (size <= 100) {
            bubbleSort(transactions);
            printList("Bubble Sorted (by fee)", transactions);

        } else if (size <= 1000) {
            insertionSort(transactions);
            printList("Insertion Sorted (fee + timestamp)", transactions);

        } else {
            System.out.println("Large dataset detected → Use advanced sort (not implemented)");
        }

        // 🔹 Outlier Detection
        List<Transaction> outliers = findOutliers(transactions);
        printList("High-fee Outliers (>50)", outliers);
    }
}