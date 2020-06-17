package course1.implementations.week1;

import course1.implementations.Test;

class _UnionFind {
    private int[] id;
    private int[] size;

    public _UnionFind(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        for (int i = 0; i < N; i++) {
            size[i] = 1;
        }
    }

    public boolean connected(int a, int b) {
        return root(a) == root(b);
    }

    public void union(int a, int b) {
        int i = root(a);
        int j = root(b);
        if (i != j) {
            if (size[i] < size[j]) {
                id[i] = j;
                size[j] += size[i];
            } else {
                id[j] = i;
                size[i] += size[j];
            }
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}

public class UnionFind {
    public static void main(String[] args) {
        _UnionFind uf = new _UnionFind(20);
        Test test = new Test();

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        
        test.assertEqual(uf.connected(0, 7), false);
        test.assertEqual(uf.connected(8, 9), true);

        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);

        test.assertEqual(uf.connected(0, 7), true);

        test.printResult();
    }
}
