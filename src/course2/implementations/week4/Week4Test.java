package course2.implementations.week4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Week4Test {
    @Test
    public void CountingSortTest() {
        CountingSort sorter = new CountingSort();
        char[] arr = new char[] { 'd', 'a', 'c', 'f', 'f', 'b', 'd', 'b', 'f', 'b', 'e', 'a' };

        sorter.sort(arr);

        assertEquals(12, arr.length);
        assertTrue(isSorted(arr));
    }

    @Test
    public void RadixSortTest() {
        RadixSort sorter = new RadixSort();
        int[] arr = new int[] { 789, 67, 3456, 2, 9879, 89, 678980, 78, 0 };

        sorter.sort(arr);

        assertEquals(9, arr.length);
        assertTrue(isSorted(arr));
    }

    private boolean isSorted(char[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
