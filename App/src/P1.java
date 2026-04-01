import java.util.*;

public class P1 {

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id        = id;
            this.fee       = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":$" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort by fee ascending (for batches <= 100)
    public static void bubbleSort(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            passes++;
            if (!swapped) break; // early termination
        }

        System.out.println("BubbleSort (fees): " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort by fee + timestamp (for batches 100-1000)
    public static void insertionSort(ArrayList<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Sort by fee first, then timestamp if fees are equal
            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("InsertionSort (fee+timestamp): " + list);
    }

    // Flag high-fee outliers > $50
    public static void flagOutliers(ArrayList<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }

        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers (> $50): none");
        } else {
            System.out.println("High-fee outliers (> $50): " + outliers);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Transaction Fee Sorting for Audit Compliance ===\n");

        // Sample transactions
        ArrayList<Transaction> batch1 = new ArrayList<>();
        batch1.add(new Transaction("id1", 10.5, "10:00"));
        batch1.add(new Transaction("id2", 25.0, "09:30"));
        batch1.add(new Transaction("id3", 5.0,  "10:15"));

        System.out.println("Input Transactions: " + batch1);
        System.out.println();

        // Bubble Sort (small batch <= 100)
        ArrayList<Transaction> bubbleBatch = new ArrayList<>(batch1);
        System.out.println("-- Bubble Sort (Small Batch <= 100) --");
        bubbleSort(bubbleBatch);
        System.out.println();

        // Insertion Sort (medium batch 100-1000)
        ArrayList<Transaction> insertionBatch = new ArrayList<>(batch1);
        System.out.println("-- Insertion Sort (Medium Batch 100-1000) --");
        insertionSort(insertionBatch);
        System.out.println();

        // Flag outliers
        System.out.println("-- Outlier Detection --");
        flagOutliers(batch1);

        // Test with outliers
        System.out.println("\n-- Outlier Detection (with high fees) --");
        ArrayList<Transaction> outlierBatch = new ArrayList<>();
        outlierBatch.add(new Transaction("id4", 75.0, "11:00"));
        outlierBatch.add(new Transaction("id5", 12.0, "11:30"));
        outlierBatch.add(new Transaction("id6", 99.9, "12:00"));
        flagOutliers(outlierBatch);
    }
}