import java.util.Iterator;

/**
 * LinkedQueue
 */
public class LinkedQueue<E> implements Iterable<E> {
    private class Node {
        E value;
        Node next;
    }

    private Node head = null;
    private Node tail = null;

    public void enqueue(E value) {
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }

    public E dequeue() {
        if (head == null) {
            return null;
        }

        E value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return new Iterator<E>() {
            private Node curr = head;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return curr != null;
            }

            @Override
            public E next() {
                E value = curr.value;
                curr = curr.next;
                // TODO Auto-generated method stub
                return value;
            }
        };
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("a");
        queue.enqueue("hello");
        queue.enqueue("world");
        queue.dequeue();
        for (String ch : queue) {
            System.out.println(ch);
        }
    }
}