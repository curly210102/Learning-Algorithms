# 栈和队列

## 栈

两种方案：链表实现和数组实现

### 链表实现

操作链表头节点

优点：保证操作在最坏情况下也是常数级消耗，

缺点：需要额外的空间和时间处理节点链接。

``` java
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

```



### 数组实现

对数组尾部操作

优点：通过调整索引指针实现操作，总体时间消耗更少

缺点：数组需要手动扩容，放满后双倍扩容，利用率少于 1 / 4 时折半缩减（避免抖动），时间消耗可分摊到正常查询中

``` java
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

```



## 队列

### 链表实现

``` java
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
```



### 数组实现

使用循环数组来实现，需要准备一个空位给尾指针，用来区分队满和队空的情况

``` java
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
```



## 实现方式选择

需要保证每个操作高效完成的情况使用链表，保证整体时间消耗较低的情况使用数组。





## 栈的应用

算术表达式运算，Dijkstra双栈算法