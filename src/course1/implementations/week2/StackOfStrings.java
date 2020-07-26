package course1.implementations.week2;

public class StackOfStrings {
    // private Node first = null;
    private String[] array;
    private int N = 0;

    StackOfStrings() {
        array = new String[1];
    }

    void push(String item) {
        // Node oldFirst = first;
        // first = new Node();
        // first.value = item;
        // first.next = oldFirst;
        if (array.length == ++N) {
            String[] newArray = new String[N * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[N - 1] = item;
    }

    String pop() {
        // String item = first.value;
        // first = first.next;
        // return item;
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
        // return first == null;
        return N == 0;
    }

    // private class Node {
    // String value;
    // Node next;
    // }
}