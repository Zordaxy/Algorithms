package course1.implementations.week6;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class week6Test {
    @Test
    public void HashSTTest() {
        final HashST<Integer, String> hashTable = new HashST<Integer, String>();
        hashTable.put(5, "Toyota");
        hashTable.put(3, "Mazda");
        hashTable.put(8, "BWM");

        assertEquals("Mazda", hashTable.get(3));
        assertEquals("Toyota", hashTable.get(5));
        assertEquals("BWM", hashTable.get(8));
    }
}