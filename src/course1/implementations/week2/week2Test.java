package course1.implementations.week2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

public class week2Test {
    BasicSortings sorting;

    @Before
    public void setUp() {
        sorting = new BasicSortings();
    }

    @Test
    public void StackTest() {
        StackOfStrings stack = new StackOfStrings();
        stack.push("Hh");
        stack.push("Bb");
        stack.push("Zz");
        stack.pop();
        stack.pop();
        stack.push("Kk");

        assertFalse(stack.isEmpty());
        assertEquals("Kk", stack.pop());
        assertEquals("Hh", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void QueueTest() {
        QueueOfStrings queue = new QueueOfStrings();
        queue.enqueue("Hh");
        queue.enqueue("Bb");
        queue.enqueue("Zz");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("Kk");

        assertFalse(queue.isEmpty());
        assertEquals("Zz", queue.dequeue());
        assertEquals("Kk", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testBubbleSort() {
        Double[] array = generateArray();

        assertFalse(isSorted(array));
        sorting.bubbleSort(array);
        assertTrue(isSorted(array));
    }

    @Test
    public void testSelectionSort() {
        Double[] array = generateArray();

        assertFalse(isSorted(array));
        sorting.selectionSort(array);
        assertTrue(isSorted(array));
    }

    @Test
    public void testInsertionSort() {
        Double[] array = generateArray();

        assertFalse(isSorted(array));
        sorting.insertionSort(array);
        assertTrue(isSorted(array));
    }

    @Test
    public void testShellSort() {
        Double[] array = generateArray();

        assertFalse(isSorted(array));
        sorting.shellSort(array);
        assertTrue(isSorted(array));
    }

    private Double[] generateArray() {
        final int N = 10;
        final Double[] a = new Double[10];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    private boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}