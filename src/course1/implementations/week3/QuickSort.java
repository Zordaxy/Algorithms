package course1.implementations.week3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    public void sort(Comparable[] a) {

    }

    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        QuickSort sorting = new QuickSort();
        int N = 20;
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        sorting.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
