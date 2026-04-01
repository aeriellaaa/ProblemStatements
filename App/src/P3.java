import java.util.*;

public class P3 {

    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id     = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    // ── Merge Sort (ascending, stable) ────────────────────────────────────────

    public static void mergeSort(ArrayList<Trade> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(ArrayList<Trade> list, int left, int mid, int right) {
        ArrayList<Trade> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).volume <= list.get(j).volume) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid)  temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    // ── Quick Sort (descending, in-place) ─────────────────────────────────────

    public static void quickSort(ArrayList<Trade> list, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int lomutoPartition(ArrayList<Trade> list, int low, int high) {
        // Median-of-three pivot selection
        int mid = (low + high) / 2;
        if (list.get(low).volume < list.get(mid).volume)   swap(list, low, mid);
        if (list.get(low).volume < list.get(high).volume)  swap(list, low, high);
        if (list.get(mid).volume < list.get(high).volume)  swap(list, mid, high);
        swap(list, mid, high); // place pivot at high

        int pivot = list.get(high).volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume >= pivot) { // DESC: >= for descending
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(ArrayList<Trade> list, int a, int b) {
        Trade temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    // ── Merge two sorted lists ────────────────────────────────────────────────

    public static ArrayList<Trade> mergeSortedLists(ArrayList<Trade> morning, ArrayList<Trade> afternoon) {
        ArrayList<Trade> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < morning.size() && j < afternoon.size()) {
            if (morning.get(i).volume <= afternoon.get(j).volume) {
                merged.add(morning.get(i++));
            } else {
                merged.add(afternoon.get(j++));
            }
        }

        while (i < morning.size())   merged.add(morning.get(i++));
        while (j < afternoon.size()) merged.add(afternoon.get(j++));

        return merged;
    }

    // ── Compute total volume ──────────────────────────────────────────────────

    public static int totalVolume(ArrayList<Trade> list) {
        int total = 0;
        for (Trade t : list) total += t.volume;
        return total;
    }

    public static void main(String[] args) {

        System.out.println("=== Historical Trade Volume Analysis ===\n");

        // Input trades
        ArrayList<Trade> trades = new ArrayList<>();
        trades.add(new Trade("trade3", 500));
        trades.add(new Trade("trade1", 100));
        trades.add(new Trade("trade2", 300));

        System.out.println("Input: " + trades);
        System.out.println();

        // Merge Sort ascending
        ArrayList<Trade> mergeSorted = new ArrayList<>(trades);
        mergeSort(mergeSorted, 0, mergeSorted.size() - 1);
        System.out.println("MergeSort (asc): " + mergeSorted + " // Stable");

        // Quick Sort descending
        ArrayList<Trade> quickSorted = new ArrayList<>(trades);
        quickSort(quickSorted, 0, quickSorted.size() - 1);
        System.out.println("QuickSort (desc): " + quickSorted + " // Pivot: median");

        // Merge two sorted sessions
        System.out.println("\n-- Merging Morning + Afternoon Sessions --");

        ArrayList<Trade> morning = new ArrayList<>();
        morning.add(new Trade("m1", 100));
        morning.add(new Trade("m2", 300));

        ArrayList<Trade> afternoon = new ArrayList<>();
        afternoon.add(new Trade("a1", 200));
        afternoon.add(new Trade("a2", 400));

        System.out.println("Morning   : " + morning);
        System.out.println("Afternoon : " + afternoon);

        ArrayList<Trade> merged = mergeSortedLists(morning, afternoon);
        System.out.println("Merged    : " + merged);
        System.out.println("Merged morning+afternoon total: " + totalVolume(merged));

        // Total volume of original
        System.out.println("\nTotal Volume (all trades): " + totalVolume(trades));
    }
}