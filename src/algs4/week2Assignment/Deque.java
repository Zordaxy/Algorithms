package algs4.week2Assignment;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;

    private int first;
    private int last;
    private int n = 0;

    public Deque() {
        array = (Item[]) new Object[4];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (first == 0) {
            increaseArray();
        }
        array[--first] = item;
        if (size() == 0) {
            last = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (last == array.length - 1) {
            increaseArray();
        }
        array[++last] = item;
        if (size() == 0) {
            first = last;
        }
        n++;
    }

    public Item removeFirst() {
        return removeElement(first);
    }

    public Item removeLast() {
        return removeElement(last);
    }

    private Item removeElement(int element) {
        if (n == 0) {
            throw new java.util.NoSuchElementException();
        }
        Item item = array[element];
        array[element] = null;
        n--;
        if (array.length <= n / 4) {
            decreaseArray();
        }
        last = first + n - 1;
        return item;
    }

    private void decreaseArray() {
        Item[] newArray = (Item[]) new Object[n / 2];
        int newFirst = newArray.length / 4;
        System.arraycopy(array, first, newArray, newFirst, n);
        first = newFirst;
        array = newArray;
    }

    private void increaseArray() {
        Item[] newArray = (Item[]) new Object[(array.length + array.length / 2)];
        int newFirst = newArray.length / 4;
        System.arraycopy(array, first, newArray, newFirst, n);
        first = newFirst;
        last = size() == 0 ? first : first + n - 1;
        array = newArray;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() {
            return i < last;
        }

        public Item next() {
            if (i >= last) {
                throw new java.util.NoSuchElementException();
            }
            return array[i++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

//    private void print() {
//        StdOut.print(Arrays.toString(array));
//    }

    public static void main(String[] args) {
//        Deque deque = new Deque();
//        while (!StdIn.isEmpty()) {
//            String s = StdIn.readString();
//            if (s.equals("f")) {
//                String element = StdIn.readString();
//                deque.addFirst(element);
//                StdOut.print();
//            } else if (s.equals("l")) {
//                String element = StdIn.readString();
//                deque.addLast(element);
//            }
//            if (s.equals("-f")) {
//                StdOut.print(deque.removeFirst());
//            } else if (s.equals("-l")) {
//                StdOut.print(deque.removeLast());
//            }
//            deque.print();
//        }
    }
}

