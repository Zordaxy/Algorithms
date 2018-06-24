package algs4.week2StacksAndQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class StackOfStrings {
    //    private Node first = null;
    private String[] array;
    private int N = 0;

    StackOfStrings() {
        array = new String[1];
    }

    void push(String item) {
//        Node oldFirst = first;
//        first = new Node();
//        first.value = item;
//        first.next = oldFirst;
        if (array.length == ++N) {
            String[] newArray = new String[N * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[N -1] = item;
    }

    String pop() {
//        String item = first.value;
//        first = first.next;
//        return item;
        String item = array[--N];
        array[N] = null;
        if (array.length == N / 4) {
            String[] newArray = new String[N / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        return item;
    }

    boolean isEmpty() {
//        return first == null;
        return N == 0;
    }

//    private class Node {
//        String value;
//        Node next;
//    }
}

class QueueOfStrings {
    private Node first = null;
    private Node last = null;

    QueueOfStrings() {

    }
    void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        if(isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }

    String dequeue() {
        String item = first.value;
        first = first.next;
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

        private class Node {
        String value;
        Node next;
    }
}

public class StacksAndQueues {
    public static void main(String[] args) {
        QueueOfStrings stack = new QueueOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.dequeue());
            else stack.enqueue(s);
            StdOut.print(stack.isEmpty());
        }
    }
}
