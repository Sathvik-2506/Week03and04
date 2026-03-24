import java.util.*;

public class ClientRiskRanking {

    // 🔹 Client Class
    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore + "(Bal:" + accountBalance + ")";
        }
    }

    // 🔹 Bubble Sort (Ascending by riskScore + swap visualization)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // 🔍 Visualize each swap
                    System.out.println("Swap: " + Arrays.toString(arr));
                }
            }

            if (!swapped) break; // Early exit
        }

        System.out.println("Total swaps (Bubble): " + swaps);
    }

    // 🔹 Insertion Sort (Descending by riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Sort by:
            // 1. riskScore DESC
            // 2. accountBalance DESC (tie-breaker)
            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort completed.");
    }

    // 🔹 Top N highest risk clients
    public static void printTopN(Client[] arr, int n) {
        System.out.println("Top " + n + " highest risk clients:");

        for (int i = 0; i < Math.min(n, arr.length); i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    // 🔹 Utility print
    public static void printArray(String label, Client[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // 🔹 Main
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 8000),
                new Client("clientB", 50, 6000)
        };

        System.out.println("Original Data:");
        printArray("Input", clients);

        // 🔹 Bubble Sort (Ascending)
        bubbleSort(clients);
        printArray("Bubble Sorted (ASC)", clients);

        // 🔹 Insertion Sort (Descending)
        insertionSort(clients);
        printArray("Insertion Sorted (DESC)", clients);

        // 🔹 Top 10 highest risk
        printTopN(clients, 10);
    }
}