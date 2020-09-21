package course2.implementations.week3;

/**
 * 1. Count numbers of appearence for eact char
 * 2. Count starting indices for them
 * 3. Populate auxiliary array
 * 4. Copy auxiliary to main array
 */
public class CountingSort {
    public void sort(char[] arr) {
        int R = 256; // radix
        int[] count = new int[R + 1];
        char[] aux = new char[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] + 1]++;
        }

        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            aux[count[ch]++] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = aux[i];
        }
    }
}
