package algs4.week2Sorting;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class SortClasses {
    public void bubbleSort(Comparable[] array) {
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

    public void selectionSort(Comparable[] array) {
        int n = array.length;
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

    public void insertionSort(Comparable[] array) {
        int n = array.length;
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

    public void shellSort(Comparable[] array) {
        int n = array.length;

        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && this.less(array[j], array[j - h]); j -= h) {
                    this.swap(array, j, j - 1);
                }
            }
            h /= 3;
        }
    }


    private void swap(Comparable[] array, int a, int b) {
        Comparable item = array[a];
        array[a] = array[b];
        array[b] = item;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}

public class OwnSorting {
    public static void main(String[] args) {
        SortClasses sorting = new SortClasses();
        int N = 10;
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        sorting.shellSort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
