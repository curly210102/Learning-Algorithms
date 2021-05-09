import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr = (Item[]) new Object[1];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can not be null");
        }

        int length = arr.length;
        if (size == length) {
            resize(2 * length);
        }
        arr[size++] = item;
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];

        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        int index = StdRandom.uniform(size);
        Item item = arr[index];
        swap(index, --size);

        int length = arr.length;
        if (size * 4 == length) {
            resize(length / 2);
        }

        return item;
    }

    private void swap(int n1, int n2) {
        Item item = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = StdRandom.uniform(size);
        return arr[index];
    }

    private class QueueIterator implements Iterator<Item> {
        private int i = 0;
        private final int[] orders = StdRandom.permutation(size);

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr[orders[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    };

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        System.out.println(queue.sample());
        System.out.println(queue.sample());

        for (String s : queue) {
            System.out.print(s);
        }
        System.out.println();
        System.out.println("--------------");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        for (String s : queue) {
            System.out.print(s);
        }

        System.out.println();
        System.out.println("--------------");

        int n = 5;
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            intQueue.enqueue(i);
        for (int a : intQueue) {
            for (int b : intQueue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }

}