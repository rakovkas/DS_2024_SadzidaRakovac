package week1;

import java.util.Iterator;

public class DoublyLinkedList<Data> implements Iterable<Data> {
    private DoubleNode<Data> head;
    private DoubleNode<Data> tail;
    private int size = 0;

    /* Add a new node to the front of the doubly linked list */
    public void addToFront(Data data) {
        DoubleNode<Data> node = new DoubleNode<>();
        node.data = data;

        if (head == null) {
            head = node;
            head.prev = null;
            head.next = null;
        } else {
            DoubleNode<Data> next = head;
            head.prev = node;
            head = node;
            head.next = next;
        }

        size++;
    }

    /* Remove a node from the front of the doubly linked list */
    public void removeFromFront() {
        if (head == null) {
            throw new IndexOutOfBoundsException("The linked list is empty.");
        }

        head = head.next;
        head.prev = null;
        size--;
    }

    /* Add a new node to the end of the doubly linked list */
    public void addToRear(Data data) {
        DoubleNode<Data> node = new DoubleNode<>();
        node.data = data;

        if (head == null) {
            head = node;
            head.next = null;
            head.prev = null;
        } else if (size == 1) {
            head.prev = null;
            node.prev = head;
            head.next = node;
            tail = node;
            tail.next = null;
        } else {
            DoubleNode<Data> current = head;

            while (current.next != null) {
                current = current.next;
            }

            node.prev = current;
            current.next = node;
            current.next.next = null;
            tail = node;
        }

        size++;
    }

    /* Remove a node at the end of the doubly linked list */
    public void removeFromRear() {
        if (head == null) {
            throw new IndexOutOfBoundsException("The linked list is empty.");
        } else if (size == 1) {
            head = null;
        } else {
            DoubleNode<Data> current = head;

            while (current.next.next != null) {
                current = current.next;
            }

            current.next = null;
        }

        size--;
    }

    /* Get a linked list node by index (0-indexed) */
    public Data get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid linked list node.");
        }

        DoubleNode<Data> current = head;
        int i = 0;

        while (i < index) {
            current = current.next;
            i++;
        }

        return current.data;
    }

    /* Add an element to a doubly linked list by index (0-index) */
    public void add(int index, Data data) {
        DoubleNode<Data> node = new DoubleNode<>();
        node.data = data;

        if (size == 0) {
            node.prev = null;
            node.next = null;
            head = node;
            tail = node;
            size++;
        } else if (size == 1) {
            tail = node;
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
        } else if (index == size) {
            node.next = null;
            node.prev = tail;
            tail = node;
            size++;
        } else if (index == size - 1) {
            node.prev = tail.prev;
            node.next = tail;
            tail.next = null;
        } else {
            DoubleNode<Data> current = head;
            int i = 0;

            while (i < index - 1) {
                current = current.next;
                i++;
            }

            node.next = current.next;
            node.prev = current;
            current.next.prev = node;
            current.next = node;

            size++;
        }
    }

    /* Delete an element from a doubly linked list by index (0-index) */
    public void remove(int index) {
        if (index < 0 || index >= size || head == null) {
            throw new IndexOutOfBoundsException("Invalid linked list node.");
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else if (index == 0) {
            head = head.next;
            size--;
        } else {
            DoubleNode<Data> current = head;
            int i = 0;

            while (i < index - 1) {
                current = current.next;
                i++;
            }

            DoubleNode<Data> nextNext = current.next.next;
            current.next = nextNext;
            nextNext.prev = current;
            size--;
        }
    }

    /* Return the current size of the doubly linked list */
    public int count() {
        return size;
    }

    /* Return an Iterator Object */
    @Override
    public Iterator<Data> iterator() {
        return new DoublyLinkedListIterator();
    }

    /* Define the Iterator class, and hasNext() and next() methods */
    private class DoublyLinkedListIterator implements Iterator<Data> {
        DoubleNode<Data> current = new DoubleNode<>();

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Data next() {
            Data data = current.data;
            current = current.next;
            return data;
        }
    }

    /* Get head node (for test purposes) */
    public DoubleNode<Data> getHead() {
        return head;
    }

    /* Get tail node (for test purposes) */
    public DoubleNode<Data> getTail() {
        return tail;
    }
}