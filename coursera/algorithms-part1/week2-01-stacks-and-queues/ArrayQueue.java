import java.util.Iterator;

public class ArrayQueue<E> implements Iterable<E> {
    private int head = 0;
    private int tail = -1;
    private E[] arr = (E[]) new Object[1];

    public void enqueue(E value) {
        int size = arr.length;
        if (tail != -1 && (tail + 1) % size == head)
            resize(2 * size);
        arr[++tail] = value;
    }

    public E dequeue() {
        if (tail + 1 == head)
            return null;
        E value = arr[head];
        arr[head++] = null;
        return value;
    }

    private void resize(int size) {
        E[] copy = (E[]) new Object[size];

        int i = 0;
        int p = head;
        while (p != tail) {
            copy[i++] = arr[p++];
            p %= arr.length;
        }
        copy[i] = arr[p];
        arr = copy;
        head = 0;
        tail = i;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return new Iterator<E>() {
            private int curr = head;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return curr != tail;
            }

            @Override
            public E next() {
                E value = arr[curr];
                curr = (curr + 1) % arr.length;
                // TODO Auto-generated method stub
                return value;
            }
        };
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>();
        queue.enqueue("a");
        queue.enqueue("hello");
        queue.enqueue("world");
        queue.dequeue();
        for (String ch : queue) {
            System.out.println(ch);
        }
    }
}
