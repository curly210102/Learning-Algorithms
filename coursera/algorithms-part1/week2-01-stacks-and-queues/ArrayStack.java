import java.util.Iterator;

public class ArrayStack<E> implements Iterable<E> {
    private E[] s = (E[]) new Object[1];
    private int N = 0;

    private void resize(int size) {
        E[] copy = (E[]) new Object[size];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public E pop() {
        if (N == 0) {
            return null;
        }

        E value = s[--N];
        s[N] = null;

        int size = s.length;
        if (4 * N == size) {
            resize(size / 2);
        }
        return value;
    }

    public void push(E value) {
        int size = s.length;
        if (N == size) {
            resize(2 * size);
        }
        s[N++] = value;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return new Iterator<E>() {
            private int current = N;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return current > 0;
            }

            @Override
            public E next() {
                // TODO Auto-generated method stub
                return s[--current];
            }
        };
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("world");
        stack.push("hello");

        for (String str : stack) {
            System.out.println(str);
        }
    }
}
