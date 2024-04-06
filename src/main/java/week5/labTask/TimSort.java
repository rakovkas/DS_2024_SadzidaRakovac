package week5.labTask;

import java.util.Arrays;

public class TimSort {
    public static <Data extends Comparable<Data>> void sort(Data[] elements, int threshold) {
        // HINT: It is not possible to directly create a new generic array in Java,
        // so you can use this workaround - create a Comparable array and cast it
        // Data[] aux = (Data[]) new Comparable[elements.length];
        Data[] aux = (Data[]) new Comparable[elements.length];
        int low = 0;
        int high = elements.length;
        int runLength = calculateRunLength(elements.length, threshold);

        for(int start = 0; start < elements.length; start += runLength){
            int end = Math.min(elements.length - 1, start + runLength - 1);
            insertionSort(elements, start, end);
        }

        int mergeSize = runLength;
        while (mergeSize < elements.length){
            for(int left = 0; left < elements.length; left += mergeSize*2){
                int mid = left + mergeSize - 1;
                int right = Math.min(elements.length - 1, left + (2*mergeSize)-1);
                if(mid < right){
                    merge(elements,aux,left,mid,right);
                }
            }
            mergeSize *= 2;
        }
    }

    public static <Data extends Comparable<Data>> void insertionSort(Data[] elements, int low, int high) {
        //sort elements with insertion sort
            for (int i = low + 1; i <= high; i++) {
                Data current = elements[i];
                int j = i - 1;
                while (j >= low && current.compareTo(elements[j]) < 0) {
                    elements[j + 1] = elements[j];
                    j--;
                }
                elements[j + 1] = current;
            }
    }

    public static <Data extends Comparable<Data>> void merge(Data[] elements, Data[] aux, int low, int mid, int high) {
        // check wih galloping
        for(int k = low; k <= high; k++) {
            aux[k] = elements[k];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                elements[k] = aux[j++];
            } else if (j > high) {
                elements[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                elements[k] = aux[j++];
            } else {
                elements[k] = aux[i++];
            }
        }
    }

    public static int calculateRunLength(int initialLength, int threshold) {
        // calculate run length
        int remainder = 0;
        while (initialLength > threshold){
            if (initialLength % 2 == 1){
                remainder = 1;
            }
            initialLength = (int) Math.floor(initialLength / 2);
        }
        return initialLength + remainder;
    }
}
