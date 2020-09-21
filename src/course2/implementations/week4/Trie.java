package course2.implementations.week4;

public class Trie<Value> {
    private static final int R = 256;
    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.value = value;
        } else {
            char c = key.charAt(d);
            x.next[c] = put(x.next[c], key, value, d + 1);
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
        if (key.length() == d) {
            return (Value) x.value;
        }
        return get(x.next[key.charAt(d)], key, d + 1);
    }
}
