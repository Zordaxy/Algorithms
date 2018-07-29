import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class KdTree {
    private final static boolean VERTICAL = true;
    private Node root = null;
    private Point2D nearest;
    private Comparator<Point2D> comparator;

    private static class Node {

        private Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return size(x.lb) + 1 + size(x.rt);
        }
    }

    public void insert(Point2D p) {
        RectHV rect = this.root != null ? this.root.rect : new RectHV(0, 0, 1, 1);
        this.root = insert(this.root, p, this.VERTICAL, rect);
    }

    private Node insert(Node node, Point2D p, boolean orientation, RectHV rect) {
        if (node == null) {
            rect = rect != null ? rect : new RectHV(0, 0, 1, 1);
            return new Node(p, rect);
        }
        Comparator<Point2D> orderComparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        int cmp = orderComparator.compare(p, node.p);
        if (cmp < 0) {
            rect = orientation ? new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax()) : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
            node.lb = insert(node.lb, p, !orientation, rect);
        } else if (cmp > 0) {
            rect = orientation ? new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax()) : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
            node.rt = insert(node.rt, p, !orientation, rect);
        } else {
            node.p = p;
        }
        return node;
    }

    public boolean contains(Point2D p) {
        return contains(root, p, this.VERTICAL);
    }

    private boolean contains(Node root, Point2D p, boolean orientation) {
        if (root == null) {
            return false;
        }

        Comparator<Point2D> comparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        int cmp = comparator.compare(p, root.p);

        if (cmp < 0) {
            return contains(root.lb, p, !orientation);
        } else if (cmp > 0) {
            return contains(root.rt, p, !orientation);
        } else {
            return true;
        }
    }

    public void draw() {
        StdDraw.setPenRadius(0.005);
        draw(root, this.VERTICAL);
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
        if (root == null) return null;
        this.nearest = root.p;
        this.comparator = p.distanceToOrder();
        return nearest(root, p, this.VERTICAL);
    }

    private Point2D nearest(Node node, Point2D p, boolean orientation) {
        if (node == null) return nearest;
        checkForMin(node.p);

        if (orientation ? node.p.x() > p.x() : node.p.y() > p.y()) {
            checkForMin(nearest(node.lb, p, !orientation));
            if (node.rt != null && node.rt.rect.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
                checkForMin(nearest(node.rt, p, !orientation));
            }
        } else {
            checkForMin(nearest(node.rt, p, !orientation));
            if (node.lb != null && node.lb.rect.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
                checkForMin(nearest(node.lb, p, !orientation));
            }
        }

        return nearest;
    }

    private void checkForMin(Point2D a) {
        if (this.comparator.compare(a, this.nearest) <= 0) {
            this.nearest = a;
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
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
    }
}
