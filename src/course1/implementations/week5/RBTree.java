package course1.implementations.week5;

import edu.princeton.cs.algs4.Queue;

public class RBTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;
        private boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.count= 1;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return x == null ? false : x.color;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = size(h.left) + size(h.right) + 1;

        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = size(h.left) + size(h.right) + 1;

        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.count;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }

        int comp = key.compareTo(x.key);

        if (comp < 0) {
            x.left = put(x.left, key, value);
        } else if (comp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp < 0) {
                x = x.left;
            } else if (comp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    public void delete(Key key) {

    }

    public Node floor(Key key) {
        Node x = root;
        if (x == null) {
            return null;
        }
        return floor(x, key);
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);

        if (comp == 0) {
            return x;
        }

        if (comp < 0) {
            return floor(x.left, key);
        }

        Node temp = floor(x.right, key);
        return temp == null ? x : temp;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return rank(key, x.left);
        } else if (comp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }
    
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null)
            return true;
        if (min != null && x.key.compareTo(min) <= 0)
            return false;
        if (max != null && x.key.compareTo(max) >= 0)
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
}
