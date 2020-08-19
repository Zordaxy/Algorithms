package course1.implementations.week4;

/**
 * 1. Largest key is a[1], which is root of binary tree 2. Use array indices to
 * move through tree: - Parent of node at k is at k/2 - Children of node at k
 * are at 2k and 2k+1
 * 
 * HeapSort: - create MaxPQ with all keys sinking all keys not from the bottom
 * level - repeatedly remove the maximum key
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (T[]) new Comparable[capacity + 1];
    }

    /**
     * Generate MaxPQ from array
     */
    public MaxPQ(T[] a) {
        pq = (T[]) new Comparable[a.length * 2];
        // Separate implementation can be "in place" by applying indices and taking into
        // the account pq[0]
        System.arraycopy(a, 0, pq, 1, a.length);
        N = a.length;

        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(T key) {
        pq[++N] = key;
        swim(N);
    }

    public T delMax() {
        T max = pq[1];
        swap(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    /**
     * HeapSort
     */
    public T[] sort() {
        T[] result = (T[]) new Comparable[N];
        while (N > 1) {
            swap(1, N--);
            sink(1);
        }
        System.arraycopy(pq, 1, result, 0, result.length);

        return result;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }

            if (!less(k, j)) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void swap(int a, int b) {
        T temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }
}