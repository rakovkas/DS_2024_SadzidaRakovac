package homework3;

import java.util.ArrayList;

public class RedBlackTree<Entry> {
    private Node<Entry> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public ArrayList<Entry> get(String searchableName) {
        Node<Entry> x = root;
        int redEdges = 0;
        int blackEdges = 0;

        while (x != null) {
            int cmp = searchableName.compareTo(x.key);
            if (cmp < 0) {
                if (x.color == RED) redEdges++;
                else blackEdges++;
                x = x.left;
            } else if (cmp > 0) {
                if (x.color == RED) redEdges++;
                else blackEdges++;
                x = x.right;
            } else {
                System.out.println("\033[31m"+"Red edges " + "\033[0m" + "on the path: " + redEdges);
                System.out.println("Black edges on the path: " + blackEdges);
                return x.values;
            }
        }
        return null;
    }

    public void put(String searchableName, Entry entry) {
        root = put(root, searchableName, entry);
        root.color = BLACK;
    }

    private Node<Entry> put(Node<Entry> h, String searchableName, Entry entry) {
        if (h == null)
            return new Node<>(searchableName, entry);

        int cmp = searchableName.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, searchableName, entry);
        else if (cmp > 0)
            h.right = put(h.right, searchableName, entry);
        else
            h.values.add(entry);
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    public int[] countRedAndBlackEdges() {
        int[] counts = new int[2];
        countEdges(root, counts);
        return counts;
    }
    private void countEdges(Node<Entry> x, int[] counts) {
        if (x == null)
            return;
        if (x.color == RED)
            counts[1]++;
        else
            counts[0]++;
        countEdges(x.left, counts);
        countEdges(x.right, counts);
    }
    private boolean isRed(Node<Entry> x) {
        if (x == null)
            return false;
        return x.color == RED;
    }
    private Node<Entry> rotateLeft(Node<Entry> h) {
        Node<Entry> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node<Entry> rotateRight(Node<Entry> h) {
        Node<Entry> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node<Entry> h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
}