package course1.implementations.week6;

/**
 * Separate chaining hash table Hash table with linked list chains in each
 * bucket
 * 
 * Has precedence over BST when: 1. Order doesn't matter 2. Hash function is
 * simple and reliable
 */
public class HashST<Key, Value> {
    private int M;
    private Node[] st;

    public HashST() {
        this.M = 97;
        st = new Node[M];
    }

    public HashST(Integer m) {
        this.M = m;
        st = new Node[M];
    }

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * Horner's method: Combine each sugnificant field using "31x + y" rule
     */
    private int alternativeHash() {
        Object property1 = new Object();
        Object property2 = new Object();
        int hash = 17; // nonzero constant
        hash = 31 * hash + property1.hashCode();
        hash = 31 * hash + property2.hashCode();

        return hash;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return (Value) x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);

        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
    }
}