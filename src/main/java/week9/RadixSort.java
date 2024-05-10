package week9;

public class RadixSort {
    /* Radix sort algorithm (public invocation) */
    public static void sort(int[] elements) {
        int max = getMax(elements);                         // 1

        for (int exp = 1; max / exp > 0; exp *= 10) {       // 2
            sort(elements, exp);                            // 3
        }
    }

    /* Digit-wise radix sort logic */
    private static void sort(int[] elements, int exp) {
        int[] aux = new int[elements.length];                   // 1
        int[] frequency = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};       // 2

        for (int i = 0; i < elements.length; i++) {             // 3
            int digit = (elements[i] / exp) % 10;               // 3
            frequency[digit]++;                                 // 3
        }

        for (int i = 1; i < 10; i++) {                          // 4
            frequency[i] += frequency[i - 1];                   // 4
        }

        for (int i = elements.length - 1; i >= 0; i--) {        // 5
            int digit = (elements[i] / exp) % 10;               // 5
            aux[frequency[digit] - 1] = elements[i];            // 5
            frequency[digit]--;                                 // 5
        }

        for (int i= 0; i < elements.length; i++) {              // 6
            elements[i] = aux[i];                               // 6
        }
    }

    /* Find the maximum element in the array */
    private static int getMax(int[] elements) {
        int max = elements[0];                          // 1
        for (int i = 1; i < elements.length; i++) {     // 2
            if (elements[i] > max) {                    // 3
                max = elements[i];                      // 3
            }
        }
        return max;                                     // 4
    }
}
