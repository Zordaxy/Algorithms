package course2.implementations.week4;

/**
 * Ternary search tree
 */
public class TST<Value> {
    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node left, mid, right;
        private char c;
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(x.left, key, value, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, value, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, value, d + 1);
        } else {
            x.value = value;
        }

        return x;
    }

    public Value get(String key) {
        return get(root, key, 0);
    }

    private Value get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);

        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return (Value) x.value;
        }
    }
}
