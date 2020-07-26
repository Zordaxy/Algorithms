package course1.implementations.week1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class week1Test {
    @Test
    public void TestUnionFind() {
        UnionFind uf = new UnionFind(20);

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);

        assertFalse(uf.connected(0, 7));
        assertTrue(uf.connected(8, 9));

        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);

        assertTrue(uf.connected(0, 7));
    }
}