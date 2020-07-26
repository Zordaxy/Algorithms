package course1.implementations.week1;

class UnionFind {
    private int[] id;
    private int[] size;

    public UnionFind(int N) {
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
