package course1.implementations.week4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

public class week4Test {
    @Test
    public void BSTTest() {
        final BST<Character, Integer> symbolTable = new BST<Character, Integer>();
        Character[] values = { 'Q', 'H', 'K', 'R', 'L', 'D', 'M', 'A', 'P', 'N' };
        int index = 0;
        for (Character ch : values) {
            symbolTable.put(ch, ++index);
        }

        assertEquals(10, symbolTable.size());
        assertTrue(symbolTable.isBST());

        int val = symbolTable.get('H');
        assertEquals(2, val);

        Queue<Character> keys = (Queue) symbolTable.keys();
        int size = keys.size();
        assertEquals(10, size);
    }

    @Test
    public void MaxPQTest() {
        Character[] unsorted = { 'H', 'B', 'Z', 'D', 'K', 'A', 'S' };
        Character[] expected = { 'Z', 'S', 'K', 'H', 'D', 'B', 'A' };
        MaxPQ<Character> maxPQ = new MaxPQ<Character>(16);

        // MaxPQ test
        for (Character c : unsorted) {
            maxPQ.insert(c);
        }

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], maxPQ.delMax());
        }
    }

    @Test
    public void HeapSortTest() {
        Character[] unsorted = { 'H', 'B', 'Z', 'D', 'K', 'A', 'S' };
        Character[] expected = { 'Z', 'S', 'K', 'H', 'D', 'B', 'A' };
        MaxPQ<Character> maxPQ = new MaxPQ<Character>(16);

        maxPQ = new MaxPQ<Character>(unsorted);
        Comparable[] result = maxPQ.sort();

        assertEquals(expected.length, result.length);

        for (int i = 0; i < unsorted.length; i++) {
            assertEquals(expected[expected.length - 1 - i], result[i]);
        }
    }
}