package course1.implementations.week3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class week3Test {
    @Test
    public void MergeSortTest() {
        Double[] array;
        final MergeSort sorting = new MergeSort();

        array = generateArray();
        assertFalse(isSorted(array));

        sorting.sort(array);
        assertTrue(isSorted(array));
    }

    @Test
    public void QuickSortTest() {
        Double[] array;
        final QuickSort sorting = new QuickSort();

        array = generateArray();
        assertFalse(isSorted(array));

        sorting.sort(array);
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