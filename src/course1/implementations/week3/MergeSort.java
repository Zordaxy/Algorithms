package course1.implementations.week3;

import edu.princeton.cs.algs4.Insertion;

/**
 * 1. Divide array into two halves in sort method if array has more than 1 element.
 * 2. Recursively sort each half.
 * 3. Merge two halves.
 */
public class MergeSort {
    private void merge(Comparable[] array, Comparable[] aux, int lo, int mid, int hi) {
        assert this.isSorted(array, lo, mid);
        assert this.isSorted(array, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int ind1 = lo;
        int ind2 = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (ind1 > mid) {
                array[k] = aux[ind2++];
            } else if (ind2 > hi) {
                array[k] = aux[ind1++];
            } else if (less(aux[ind2], aux[ind1])) {
                array[k] = aux[ind2++];
            } else {
                array[k] = aux[ind1++];
            }
        }

        assert this.isSorted(array, lo, hi);
    }

    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        final int CUTOFF = 7;
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi + 1);
            return;
        }

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (less(a[mid], a[mid + 1]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert this.isSorted(a, 0, a.length - 1);
    }
}
