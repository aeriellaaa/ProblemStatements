class P6 {

    // -------- LINEAR SEARCH (unsorted) --------
    static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Found at index " + i + " (comparisons: " + comparisons + ")");
                return i;
            }
        }
        System.out.println("Not found (comparisons: " + comparisons + ")");
        return -1;
    }

    // -------- BINARY SEARCH INSERTION POINT (lower_bound) --------
    static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Insertion index (lower_bound): " + low + " (comparisons: " + comparisons + ")");
        return low;
    }

    // -------- FLOOR (largest <= target) --------
    static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Floor comparisons: " + comparisons);
                return arr[mid];
            } else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor comparisons: " + comparisons);
        return result;
    }

    // -------- CEILING (smallest >= target) --------
    static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Ceiling comparisons: " + comparisons);
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                result = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Ceiling comparisons: " + comparisons);
        return result;
    }

    public static void main(String[] args) {
        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // Linear search on unsorted
        System.out.println("Linear Search:");
        linearSearch(unsorted, target);

        // Sorted array
        int[] sorted = {10, 25, 50, 100};

        // Binary insertion point
        System.out.println("\nBinary Search (Insertion Point):");
        lowerBound(sorted, target);

        // Floor and Ceiling
        System.out.println("\nFloor and Ceiling:");
        Integer f = floor(sorted, target);
        Integer c = ceiling(sorted, target);

        System.out.println("Floor(" + target + "): " + f);
        System.out.println("Ceiling(" + target + "): " + c);
    }
}