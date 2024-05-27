package homework2;

public class ProcessQueue {
    public Process[] pq = new Process[2];
    public int length = 0;

    /* Add a new process into the priority queue */
    public void addProcess(Process process) {
        if (pq.length == length + 1) {
            resize(2 * pq.length);
        }
        pq[++length] = process;
        swim(length);
    }

    /* Return and remove the next Process that should be run */
    public Process runNextProcess() {
        if (length == 0) {
            return null;
        }
        Process min = pq[1];
        swap(1, length--);
        sink(1);
        pq[length + 1] = null; // Avoid loitering
        return min;
    }

    /* Return the next Process that should be run (but do not delete it) */
    public Process peekNextProcess() {
        if (length == 0) {
            return null;
        }
        return pq[1];
    }

    public void resize(int capacity) {
        Process[] copy = new Process[capacity];
        for (int i = 1; i <= length; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= length) {
            int j = 2 * k;
            if (j < length && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private boolean greater(int a, int b) {
        return pq[a].compareTo(pq[b]) > 0;
    }

    private void swap(int a, int b) {
        Process temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }
}
