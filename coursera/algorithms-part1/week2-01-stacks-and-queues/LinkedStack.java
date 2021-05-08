import java.util.Iterator;

public class LinkedStack<E> implements Iterable<E> {
    private Node first = null;

    private class Node {
        E value;
        Node next;
    }

    public void push(E value) {
        Node node = new Node();
        node.value = value;
        node.next = first;
        first = node;
    }

    public E pop() {
        if (first != null) {
            E value = first.value;
            first = first.next;
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return new Iterator<E>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return current != null;
            }

            @Override
            public E next() {
                // TODO Auto-generated method stub
                E value = current.value;
                current = current.next;
                return value;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
                try {
                    throw new NoSuchMethodException("Not Supported'");
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("world");
        stack.push("a");
        String ch = stack.pop();
        System.out.println(ch);
        stack.push("hello");
        for (String s : stack) {
            System.out.println(s);
        }
    }
}
