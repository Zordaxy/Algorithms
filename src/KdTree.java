import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class KdTree {
    private static final boolean VERTICAL = true;
    private Node root = null;
    private Point2D nearest;
    private double distance;
    private Queue<Point2D> range;
    private int size = 0;

    private static class Node {

        private final Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        this.root = insert(this.root, p, KdTree.VERTICAL, null);
    }

    private Node insert(Node node, Point2D p, boolean orientation, Node parent) {
        if (node == null) {
            if (this.size == 0) {
                this.size++;
                return new Node(p, new RectHV(0, 0, 1, 1));
            }
            RectHV rect;
            int cmp;
            this.size++;
            if (orientation) {
                cmp = Point2D.Y_ORDER.compare(p, parent.p);
                if (cmp <= 0) {
                    rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                } else {
                    rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
                }
            } else {
                cmp = Point2D.X_ORDER.compare(p, parent.p);
                if (cmp <= 0) {
                    rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                } else {
                    rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                }
            }
            return new Node(p, rect);
        }

        Comparator<Point2D> orderComparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        int cmp = orderComparator.compare(p, node.p);

        if (cmp < 0) {
            node.lb = insert(node.lb, p, !orientation, node);
        } else if (cmp > 0) {
            node.rt = insert(node.rt, p, !orientation, node);
        } else {
            if (orientation ? !((Double) (p.y())).equals(node.p.y()) : !((Double) (p.x())).equals(node.p.x())) {
                node.lb = insert(node.lb, p, !orientation, node);
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return contains(root, p, KdTree.VERTICAL);
    }

    private boolean contains(Node node, Point2D p, boolean orientation) {
        if (node == null) {
            return false;
        }

        Comparator<Point2D> orderComparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        int cmp = orderComparator.compare(p, node.p);

        if (cmp < 0) {
            return contains(node.lb, p, !orientation);
        } else if (cmp > 0) {
            return contains(node.rt, p, !orientation);
        } else {
            orderComparator = !orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
            cmp = orderComparator.compare(p, node.p);
            return cmp == 0 || contains(node.lb, p, !orientation);
        }
    }

    public void draw() {
        draw(root, KdTree.VERTICAL);
    }

    private void draw(Node node, boolean orientation) {
        if (node == null) {
            return;
        }
        if (orientation) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }

        double x0 = orientation ? node.p.x() : node.rect.xmin();
        double y0 = orientation ? node.rect.ymin() : node.p.y();

        double x1 = orientation ? node.p.x() : node.rect.xmax();
        double y1 = orientation ? node.rect.ymax() : node.p.y();
        StdDraw.line(x0, y0, x1, y1);

        draw(node.lb, !orientation);
        draw(node.rt, !orientation);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (root == null) return null;
        this.nearest = root.p;
        this.distance = this.nearest.distanceSquaredTo(p);
        nearest(root, p, KdTree.VERTICAL);
        return this.nearest;
    }

    private void nearest(Node node, Point2D p, boolean orientation) {
        if (node == null) return;
        double comparedDistance = node.p.distanceSquaredTo(p);
        if (comparedDistance < this.distance) {
            this.distance = comparedDistance;
            this.nearest = node.p;
        }

        if (orientation ? node.p.x() > p.x() : node.p.y() > p.y()) {
            nearest(node.lb, p, !orientation);
            if (node.rt != null && node.rt.rect.distanceSquaredTo(p) < this.distance) {
                nearest(node.rt, p, !orientation);
            }
        } else {
            nearest(node.rt, p, !orientation);
            if (node.lb != null && node.lb.rect.distanceSquaredTo(p) < this.distance) {
                nearest(node.lb, p, !orientation);
            }
        }
        return;
    }

    public Iterable<Point2D> range(RectHV rect) {
        range = new Queue<>();
        if (rect == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (!this.isEmpty()) {
            range(root, rect);
        }
        return this.range;
    }

    private void range(Node node, RectHV rect) {
        if (rect.contains(node.p)) {
            range.enqueue(node.p);
        }
        if (node.lb != null && rect.intersects(node.lb.rect)) {
            range(node.lb, rect);
        }
        if (node.rt != null && rect.intersects(node.rt.rect)) {
            range(node.rt, rect);
        }
    }

    public static void main(String[] args) {
        KdTree KD = new KdTree();
        StdOut.println(KD.size());
        StdOut.println(KD.isEmpty());
        KD.insert(new Point2D(0.5, 0.8));
        KD.insert(new Point2D(0.1, 0.1));
        KD.insert(new Point2D(0.9, 0.9));
        KD.insert(new Point2D(0.7, 0.7));
        KD.insert(new Point2D(0.9, 0.1));
        StdOut.println(KD.size());
        StdOut.println(KD.isEmpty());
        for (int i = 0; i < 20; i++) {
            StdOut.print("=");
        }
        StdOut.println();
        StdOut.println(KD.contains(new Point2D(0.75, 0.8)));
        StdOut.println(KD.contains(new Point2D(0.9, 0.9)));
        KD.draw();
        StdOut.println(KD.nearest(new Point2D(0.01, 0.3)));
        StdOut.println(KD.range(new RectHV(0, 0, 0.5, 0.99)).toString());
    }
}
