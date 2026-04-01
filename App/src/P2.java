import java.util.*;

public class P2 {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name           = name;
            this.riskScore      = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore;
        }
    }

    // Bubble Sort ascending by riskScore
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j]      = arr[j + 1];
                    arr[j + 1]  = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        System.out.println("Bubble Sort (asc): " + Arrays.toString(arr));
        System.out.println("Swaps: " + swaps);
    }

    // Insertion Sort descending by riskScore, then by accountBalance descending
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (arr[j].riskScore < key.riskScore ||
                    (arr[j].riskScore == key.riskScore && arr[j].accountBalance < key.accountBalance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (desc): " + Arrays.toString(arr));
    }

    // Print top N highest risk clients
    public static void topRiskClients(Client[] arr, int topN) {
        // Array must be sorted descending before calling this
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")");
            if (i < Math.min(topN, arr.length) - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=== Client Risk Score Ranking ===\n");

        Client[] clients = {
                new Client("clientC", 80, 15000.0),
                new Client("clientA", 20, 50000.0),
                new Client("clientB", 50, 30000.0),
                new Client("clientD", 95, 8000.0),
                new Client("clientE", 65, 22000.0)
        };

        System.out.println("Input: " + Arrays.toString(clients));
        System.out.println();

        // Bubble Sort ascending
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        System.out.println("-- Bubble Sort (Ascending by Risk Score) --");
        bubbleSort(bubbleArr);
        System.out.println();

        // Insertion Sort descending
        Client[] insertionArr = Arrays.copyOf(clients, clients.length);
        System.out.println("-- Insertion Sort (Descending by Risk Score + Balance) --");
        insertionSort(insertionArr);
        System.out.println();

        // Top 3 risk clients
        System.out.println("-- Top Risk Clients --");
        topRiskClients(insertionArr, 3);
    }
}
