package course1.implementations.week5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

public class week5Test {
    @Test
    public void RBTreeTest() {
        final RBTree<Character, Integer> symbolTable = new RBTree<Character, Integer>();
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
}