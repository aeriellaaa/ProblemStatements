import java.util.*;

public class P5 {

    // ── Linear Search ─────────────────────────────────────────────────────────

    public static int linearSearchFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear first " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int linearSearchLast(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                lastIndex = i;
            }
        }
        System.out.println("Linear last  " + target + ": index " + lastIndex + " (" + comparisons + " comparisons)");
        return lastIndex;
    }

    // ── Binary Search ─────────────────────────────────────────────────────────

    public static int binarySearch(String[] sortedLogs, String target) {
        int low = 0, high = sortedLogs.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = sortedLogs[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary " + target + ": index " + mid + " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Count all occurrences using binary search (find leftmost + rightmost)
    public static int countOccurrences(String[] sortedLogs, String target) {
        int first = findFirst(sortedLogs, target);
        if (first == -1) return 0;
        int last = findLast(sortedLogs, target);
        return last - first + 1;
    }

    private static int findFirst(String[] sortedLogs, String target) {
        int low = 0, high = sortedLogs.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedLogs[mid].equals(target)) {
                result = mid;
                high = mid - 1; // keep searching left
            } else if (sortedLogs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int findLast(String[] sortedLogs, String target) {
        int low = 0, high = sortedLogs.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedLogs[mid].equals(target)) {
                result = mid;
                low = mid + 1; // keep searching right
            } else if (sortedLogs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Account ID Lookup in Transaction Logs ===\n");

        // Unsorted logs for linear search
        String[] logs = {"accB", "accA", "accB", "accC"};
        System.out.println("Logs: " + Arrays.toString(logs));
        System.out.println();

        // Linear Search
        System.out.println("-- Linear Search --");
        linearSearchFirst(logs, "accB");
        linearSearchLast(logs, "accB");
        linearSearchFirst(logs, "accD"); // not found
        System.out.println();

        // Sort for binary search
        String[] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs);
        System.out.println("Sorted logs: " + Arrays.toString(sortedLogs));
        System.out.println();

        // Binary Search
        System.out.println("-- Binary Search --");
        int idx = binarySearch(sortedLogs, "accB");
        int count = countOccurrences(sortedLogs, "accB");
        System.out.println("Count of accB: " + count);
        System.out.println();

        binarySearch(sortedLogs, "accA");
        System.out.println("Count of accA: " + countOccurrences(sortedLogs, "accA"));
        System.out.println();

        binarySearch(sortedLogs, "accD"); // not found

        // Time complexity report
        System.out.println("\n-- Time Complexity Report --");
        System.out.println("Linear Search : O(n)     - worst case scans all " + logs.length + " entries");
        System.out.println("Binary Search : O(log n) - max " + (int)(Math.log(sortedLogs.length) / Math.log(2) + 1) + " comparisons for " + sortedLogs.length + " entries");
    }
}