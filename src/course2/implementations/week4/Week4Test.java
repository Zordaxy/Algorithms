package course2.implementations.week4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Week4Test {
    @Test
    public void TrieTest() {
        Trie<Integer> trie = new Trie<Integer>();
        trie.put("by", 4);
        trie.put("sea", 6);
        trie.put("sells", 1);
        trie.put("shells", 3);
        trie.put("she", 0);
        trie.put("shore", 7);
        trie.put("the", 5);

        assertEquals((Integer) 0, trie.get("she"));
        assertEquals((Integer) 3, trie.get("shells"));
        assertEquals((Integer) 1, trie.get("sells"));
        assertEquals((Integer) 5, trie.get("the"));
    }

    @Test
    public void TSTTest() {
        TST<Integer> trie = new TST<Integer>();
        trie.put("by", 4);
        trie.put("sea", 6);
        trie.put("sells", 1);
        trie.put("shells", 3);
        trie.put("she", 0);
        trie.put("shore", 7);
        trie.put("the", 5);

        assertEquals((Integer) 0, trie.get("she"));
        assertEquals((Integer) 3, trie.get("shells"));
        assertEquals((Integer) 1, trie.get("sells"));
        assertEquals((Integer) 5, trie.get("the"));
    }
}
