package week4.labTask;

import week4.AbstractSort;

public class BinaryInsertionSort extends AbstractSort {

    public static <Data extends Comparable<Data>> void sort(LinkedList<Data> ll) {
        //insertion sort
        for (int i = 0; i < ll.count(); i++) {
            //insert instead swap findInsertionPoint()
            int point  = findInsertionPoint(ll, i-1, ll.get(i));
            ll.add(point, ll.get(i));
            ll.remove(i+1);
        }

    }

    public static <Data extends Comparable<Data>> int findInsertionPoint(LinkedList<Data> ll, int high, Data key) {
        // binary search which return the index where KEY element needs to be inserted
        //high - end of the sorted part if array
        int low = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ll.get(mid).compareTo(key) > 0) {
                high = mid - 1;
            } else if (ll.get(mid).compareTo(key) < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high+1;
    }
}
