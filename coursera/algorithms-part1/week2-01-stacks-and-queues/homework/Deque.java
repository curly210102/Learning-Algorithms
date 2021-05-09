import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next = null;
        Node prev = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // IllegalArgumentException
        if (item == null) {
            throw new IllegalArgumentException("argument can not be null");
        }
        Node node = new Node();
        node.item = item;
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // IllegalArgumentException
        if (item == null) {
            throw new IllegalArgumentException("argument can not be null");
        }
        Node node = new Node();
        node.item = item;
        node.prev = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = tail;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("deque is empty");
        }
        Item item = head.item;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("deque is empty");
        }

        Item item = tail.item;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr = head;

        @Override
        public Item next() {
            // NoSuchElementException
            if (curr == null) {
                throw new NoSuchElementException("iterator end");
            }

            Item item = curr.item;
            curr = curr.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public void remove() {
            // UnsupportedOperationException
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> queue = new Deque<>();
        queue.addFirst("B");
        queue.addFirst("A");
        queue.addLast("C");
        System.out.println(queue.size());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeLast());
        System.out.println(queue.size());

        System.out.println("---------------------");
        queue.addLast("B");
        queue.addLast("C");
        queue.addFirst("A");
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println(queue.removeLast());
        System.out.println(queue.removeLast());
        System.out.println(queue.removeFirst());
        System.out.println(queue.size());
    }

}