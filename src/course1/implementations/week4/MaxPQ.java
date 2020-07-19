package course1.implementations.week4;

import course1.implementations.Test;

/**
 * 1. Largest key is a[1], which is root of binary tree
 * 2. Use array indices to move through tree:
 * - Parent of node at k is at k/2
 * - Children of node at k are at 2k and 2k+1
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (T[]) new Comparable[capacity + 1];
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

    public static void main(final String[] args) {
        final Test test = new Test();
        final MaxPQ<Character> maxPQ = new MaxPQ<Character>(16);

        maxPQ.insert('H');
        maxPQ.insert('B');
        maxPQ.insert('Z');
        maxPQ.insert('D');
        maxPQ.insert('K');
        maxPQ.insert('A');
        maxPQ.insert('S');

        test.assertEquals(maxPQ.delMax() == 'Z', true);
        test.assertEquals(maxPQ.delMax() == 'S', true);
        test.assertEquals(maxPQ.delMax() == 'K', true);
        test.assertEquals(maxPQ.delMax() == 'H', true);
        test.assertEquals(maxPQ.delMax() == 'D', true);
        test.assertEquals(maxPQ.delMax() == 'B', true);
        test.assertEquals(maxPQ.delMax() == 'A', true);

        test.printResult();
    }
}