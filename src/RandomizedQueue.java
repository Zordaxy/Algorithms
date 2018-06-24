import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size;

    public RandomizedQueue() {
        size = 0;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        size++;
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int number = StdRandom.uniform(size);
        Item element;
        if (number == 0) {
            element = first.value;
            first = first.next;
        } else {
            Node parent = getNodeByNumber(number - 1);
            element = parent.next.value;
            parent.next = parent.next.next;
            if (parent.next == null) {
                last = parent;
            }
        }
        size--;
        return element;
    }

    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return getNodeByNumber(StdRandom.uniform(size)).value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    private class Node {
        Item value;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        int[] itemNumbers;
        int current;

        RandomIterator() {
            if (size == 0) {
                throw new java.util.NoSuchElementException();
            }

            itemNumbers = new int[size];
            for (int i = 0; i < size; i++) {
                itemNumbers[i] = i;
            }
            StdRandom.shuffle(itemNumbers);
            current = 0;
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (current == size) {
                throw new java.util.NoSuchElementException();
            }
            return getNodeByNumber(itemNumbers[current++]).value;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private Node getNodeByNumber(int number) {
        Node element = first;
        while (number != 0) {
            number--;
            element = element.next;
        }
        return element;
    }

    public static void main(String[] args) {
//        RandomizedQueue<String> que = new RandomizedQueue<>();
//        while (!StdIn.isEmpty()) {
//            String s = StdIn.readString();
//            if (s.equals("f")) {
//                String element = StdIn.readString();
//                que.enqueue(element);
//            } else if (s.equals("l")) {
//                que.dequeue();
//            }
//            StdOut.print(que.size());
//            for (String ss : que) {
//                StdOut.println(ss);
//            }
//        }
    }
}
