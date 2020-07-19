package course1.implementations.week3;

import edu.princeton.cs.algs4.StdRandom;
import course1.implementations.Test;

/**
 * 1. Perform partitioning
 * 2. Recursively sort 2 arrays divided by partition element
 * 
 * Partitioning:
 * 1. Go from edges with i and j. Repeat until i and j pointers cross (and partition element a[lo]):
 * - scan i from left to right so long as (a[i] < a[lo])
 * - scan j from right to left so long as (a[lo] < a[j])
 * - swap a[i] and a[j]
 * 2. When pointers cross:
 * - exchange a[lo] with a[j]
 * 
 * Additional:
 * 1. Switch to Insertion sort when a.length == 10
 * 2. Stop on duplicates
 * 3. QuickSelect - partitoning than using only one array to continue search.
 */
public class QuickSort {
    private int partition(final Comparable[] a, final int lo, final int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }

            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            this.swap(a, i, j);
        }

        this.swap(a, lo, j);
        return j;
    }

    private void sort(final Comparable[] a, final int lo, final int hi) {
        if (hi <= lo) {
            return;
        }
        final int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public void sort(final Comparable[] a) {
        StdRandom.shuffle(a);
        this.sort(a, 0, a.length - 1);
    }

    private boolean less(final Comparable a, final Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void swap(final Comparable[] array, final int a, final int b) {
        final Comparable item = array[a];
        array[a] = array[b];
        array[b] = item;
    }

    public boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (this.less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        final Test test = new Test();
        Double[] array;
        final QuickSort sorting = new QuickSort();

        array = Test.generateArray();
        test.assertEquals(sorting.isSorted(array), false);
        sorting.sort(array);
        test.assertEquals(sorting.isSorted(array), true);

        test.printResult();
    }
}
