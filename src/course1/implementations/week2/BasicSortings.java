package course1.implementations.week2;

import course1.implementations.Test;
import edu.princeton.cs.algs4.StdRandom;

class SortClasses {
    public void bubbleSort(final Comparable[] array) {
        int n = array.length;
        boolean swapped = true;
        while (swapped) {
            int tempN = 0;
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (this.less(array[i], array[i - 1])) {
                    this.swap(array, i, i - 1);
                    tempN = i;
                    swapped = true;
                }
            }
            n = tempN;
        }
    }

    /**
     * Scan from left to right
     * In iteration i, find index min of smallest remaining entry
     * Swap a[i] and a[min]
     */
    public void selectionSort(final Comparable[] array) {
        final int n = array.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (this.less(array[j], array[min])) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    /**
     * Scan from left to right
     * Move the pointer to the right
     * Moving from the right to left, exchange array[i] with each larger entry to its left
     */
    public void insertionSort(final Comparable[] array) {
        final int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (this.less(array[j], array[j - 1])) {
                    this.swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Improved Insertion Sort 
     * Move entries more than one position at a time by h-sorting array
     */
    public void shellSort(final Comparable[] array) {
        final int n = array.length;

        // determine increment as 3x + 1 sequence
        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }

        // h-sorting with decreasing increment
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                // block similat to Insertion Sort with increment h
                for (int j = i; j >= h; j -= h) {
                    if (this.less(array[j], array[j - h])) {
                        this.swap(array, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
            h /= 3;
        }
    }

    private void swap(final Comparable[] array, final int a, final int b) {
        final Comparable item = array[a];
        array[a] = array[b];
        array[b] = item;
    }

    private boolean less(final Comparable a, final Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void shuffle(Comparable[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(i + 1);
            this.swap(array, i, r);
        }
    }

    public boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (this.less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}

public class BasicSortings {
    public static void main(final String[] args) {
        Test test = new Test();
        Double[] array;
        final SortClasses sorting = new SortClasses();

        array = Test.generateArray();
        test.assertEquals(sorting.isSorted(array), false);
        sorting.bubbleSort(array);
        test.assertEquals(sorting.isSorted(array), true);

        array = Test.generateArray();
        test.assertEquals(sorting.isSorted(array), false);
        sorting.selectionSort(array);
        test.assertEquals(sorting.isSorted(array), true);

        array = Test.generateArray();
        test.assertEquals(sorting.isSorted(array), false);
        sorting.insertionSort(array);
        test.assertEquals(sorting.isSorted(array), true);

        array = Test.generateArray();
        test.assertEquals(sorting.isSorted(array), false);
        sorting.shellSort(array);
        test.assertEquals(sorting.isSorted(array), true);

        test.printResult();
    }
}
