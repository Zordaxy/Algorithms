package algs4.week5Assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private final SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        double scale = 0;
        for (Point2D point : this.set) {
            if (point.x() > scale) {
                scale = point.x();
            }
            if (point.y() > scale) {
                scale = point.y();
            }
        }

        StdDraw.setXscale(0, scale);
        StdDraw.setYscale(0, scale);

        for (Point2D point : this.set) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Queue<Point2D> range = new Queue<>();
        for (Point2D point : this.set) {
            if (rect.contains(point)) {
                range.enqueue(point);
            }
        }
        return range;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
        double distance = Double.POSITIVE_INFINITY;
        Point2D nearest = null;
        for (Point2D point : this.set) {
            double pointDistance = point.distanceSquaredTo(p);
            if (nearest == null || pointDistance < distance) {
                nearest = point;
                distance = pointDistance;
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET set = new PointSET();
        for (int i = 0; i < 20; i++) {
            double x = StdRandom.uniform(100);
            double y = StdRandom.uniform(100);
            set.insert(new Point2D(x, y));
        }
        set.draw();
        assert set.size() == 20 : "Wrong size: " + set.size();
    }
}
