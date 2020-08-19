package course1.implementations.week4;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.count= 1;
        }
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
            return new Node(key, value);
        }

        int comp = key.compareTo(x.key);

        if (comp < 0) {
            x.left = put(x.left, key, value);
        } else if (comp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
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
}
