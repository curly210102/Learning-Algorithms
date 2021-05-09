import java.util.Iterator;

public class ArrayQueue<E> implements Iterable<E> {
    private int head = 0;
    private int tail = 0;
    private E[] arr = (E[]) new Object[2];

    public void enqueue(E value) {
        int size = arr.length;
        if ((tail + 1) % size == head)
            resize(2 * size - 1);
        arr[tail++] = value;
    }

    public E dequeue() {
        if (tail == head)
            return null;
        E value = arr[head];
        arr[head] = null;
        head = (head + 1) % arr.length;
        return value;
    }

    private void resize(int size) {
        E[] copy = (E[]) new Object[size];

        int curr = head;
        int i = 0;
        while (curr != tail) {
            copy[i] = arr[curr];
            i++;
            curr = (head + i) % arr.length;
        }
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
