package course2.implementations.week3;

/**
 * Radix Sort or LSD sort (Least significant digit)
 * 
 * 1. Do following for each digit i where i varies from least significant digit
 * to the most significant digit: Sort input array using counting sort (or any
 * stable sort) according to the i’th digit
 */
public class RadixSort {
    private int exp;
    private int[] arr;

    private int getValue(int i) {
        return (arr[i] / exp) % 10;
    }

    private int getMax() {
        int mx = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    private void countSort() {
        int R = 10; // radix
        int[] count = new int[R + 1];
        int[] aux = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[getValue(i) + 1]++;
        }

        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        for (int i = 0; i < arr.length; i++) {
            aux[count[getValue(i)]++] = getValue(i);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = aux[i];
        }
    }

    public void sort(int arr[]) {
        this.arr = arr;

        int m = getMax();

        for (int i = 1; m / i > 0; i *= 10) {
            exp = i;
            countSort();
        }
    }
}
