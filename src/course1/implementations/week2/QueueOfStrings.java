package course1.implementations.week2;

public class QueueOfStrings {
    private Node first = null;
    private Node last = null;

    QueueOfStrings() {
    }

    void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        if (isEmpty()) {
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