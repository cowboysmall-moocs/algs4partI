
/**
 * jerry
 */
public class PointSET {

    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {

        points = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {

        return points.isEmpty();
    }

    // number of points in the set
    public int size() {

        return points.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {

        points.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {

        return points.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {

        for (Point2D point : points)
            StdDraw.point(point.x(), point.y());
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        SET<Point2D> range = new SET<>();
        for (Point2D point : points)
            if (rect.contains(point))
                range.add(point);
        return range;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {

        if (isEmpty())
            return null;

        double minimumDistance = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D point : points) {

            double currentDistance = point.distanceTo(p);
            if (currentDistance < minimumDistance) {

                minimumDistance = currentDistance;
                nearest = point;
            }
        }
        return nearest;
    }
}
