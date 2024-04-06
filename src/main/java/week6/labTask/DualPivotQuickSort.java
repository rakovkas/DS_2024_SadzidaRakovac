package week6.labTask;

import java.util.Random;

public class DualPivotQuickSort {

    /* Quick sort algorithm (public invocation) */
    public static <Data extends Comparable<Data>> void sort(Data[] elements) {
        shuffle(elements);
        sort(elements, 0, elements.length - 1);    }

    /* Knuth shuffle (performance guarantee) */
    private static <Data extends Comparable<Data>> void shuffle(Data[] elements) {
        Random rand = new Random();
        for (int i = 0; i < elements.length; i++) {
            int r = i + rand.nextInt(elements.length - i);
            swap(elements, i, r);
        }
    }

    /* Recursive quick sort logic */
    private static <Data extends Comparable<Data>> void sort(Data[] elements, int low, int high) {
        if (high <= low) {
            return;
        }
        int [] pivots = partition(elements, low, high);
        int leftPivot = pivots[0];
        int rightPivot = pivots[1];
        sort(elements, low, leftPivot); //left boundary
        sort(elements, leftPivot + 1, rightPivot); //middle
        sort(elements, rightPivot + 1, high); //right boundary
    }

    /* Partition an array using the dual-pivot approach and return the pivot indices */
    public static <Data extends Comparable<Data>> int[] partition(Data[] elements, int low, int high) {
        if (elements[low].compareTo(elements[high]) > 0){
            swap(elements, low, high);
        }
        //low - left pivot
        //high - right pivot

        int i = low + 1; //left pivot partition
        int j = high - 1; // right pivot partition
        int k = i;

        while (k <= j) {
            if (elements[k].compareTo(elements[low]) < 0) {
                swap(elements, k++, i++);

            } else if (elements[k].compareTo(elements[high]) > 0) {
                swap(elements, k, j--);

            } else {
                k++;
            }
        }

        swap(elements, --i, low);
        swap(elements, ++j, high);

        return new int[]{i, j}; //return indices of pivots
    }

    private static <Data extends Comparable<Data>> void swap(Data[] elements, int i, int j) {
        Data tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }
}
