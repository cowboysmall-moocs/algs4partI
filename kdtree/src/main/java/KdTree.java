/**
 * jerry
 */
public class KdTree {

    private KdTreeNode root;

    private int N;

    // construct an empty set of points
    public KdTree() {

        root = null;
        N = 0;
    }

    // is the set empty?
    public boolean isEmpty() {

        return root == null;
    }

    // number of points in the set
    public int size() {

        return N;
    }


    //_________________________________________________________________________

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D point) {

        if (isEmpty()) {

            root = new KdTreeNode(point);
            N++;

        } else {

            root = insert(root, point);
        }
    }

    private KdTreeNode insert(KdTreeNode node, Point2D point) {

        if (node.point.equals(point))
            return node;

        if (pointLessThan(node, point)) {

            if (node.leftBottom == null) {

                node.leftBottom =
                        new KdTreeNode(
                                point,
                                getLeftRectangle(node),
                                !node.vertical
                        );
                N++;

            } else {

                node.leftBottom = insert(node.leftBottom, point);
            }

        } else {

            if (node.rightTop == null) {

                node.rightTop =
                        new KdTreeNode(
                                point,
                                getRightRectangle(node),
                                !node.vertical
                        );
                N++;

            } else {

                node.rightTop = insert(node.rightTop, point);
            }
        }

        return node;
    }

    private RectHV getLeftRectangle(KdTreeNode node) {

        if (node.vertical)
            return new RectHV(
                    node.rectangle.xmin(),
                    node.rectangle.ymin(),
                    node.point.x(),
                    node.rectangle.ymax()
            );
        else
            return new RectHV(
                    node.rectangle.xmin(),
                    node.rectangle.ymin(),
                    node.rectangle.xmax(),
                    node.point.y()
            );
    }

    private RectHV getRightRectangle(KdTreeNode node) {

        if (node.vertical)
            return new RectHV(
                    node.point.x(),
                    node.rectangle.ymin(),
                    node.rectangle.xmax(),
                    node.rectangle.ymax()
            );
        else
            return new RectHV(
                    node.rectangle.xmin(),
                    node.point.y(),
                    node.rectangle.xmax(),
                    node.rectangle.ymax()
            );
    }


    //_________________________________________________________________________

    // does the set contain the point p?
    public boolean contains(Point2D p) {

        if (isEmpty())
            return false;

        return contains(root, p);
    }

    private boolean contains(KdTreeNode node, Point2D point) {

        if (node == null)
            return false;

        if (node.point.equals(point))
            return true;

        if (pointLessThan(node, point))
            return contains(node.leftBottom, point);
        else
            return contains(node.rightTop, point);
    }


    //_________________________________________________________________________

    // draw all of the points to standard draw
    public void draw() {

        StdDraw.setScale(0, 1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();

        root.rectangle.draw();

        draw(root);
    }

    private void draw(KdTreeNode node) {

        if (node == null)
            return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        if (node.vertical)
            drawVertical(node);
        else
            drawHorizontal(node);

        draw(node.leftBottom);
        draw(node.rightTop);
    }

    private void drawHorizontal(KdTreeNode node) {

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius();
        StdDraw.line(
                node.rectangle.xmin(),
                node.point.y(),
                node.rectangle.xmax(),
                node.point.y()
        );
    }

    private void drawVertical(KdTreeNode node) {

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(
                node.point.x(),
                node.rectangle.ymin(),
                node.point.x(),
                node.rectangle.ymax()
        );
    }


    //_________________________________________________________________________

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        SET<Point2D> range = new SET<>();
        if (!isEmpty())
            range(root, rect, range);
        return range;
    }

    private void range(KdTreeNode node, RectHV rect, SET<Point2D> range) {

        if (rect.contains(node.point))
            range.add(node.point);

        if (node.leftBottom != null
                && rect.intersects(node.leftBottom.rectangle))
            range(node.leftBottom, rect, range);

        if (node.rightTop != null
                && rect.intersects(node.rightTop.rectangle))
            range(node.rightTop, rect, range);
    }


    //_________________________________________________________________________

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D point) {

        if (isEmpty())
            return null;

        return nearest(root, root.point, point);
    }

    private Point2D nearest(KdTreeNode node, Point2D current, Point2D point) {

        if (node == null)
            return current;

        double distanceToNode = node.rectangle.distanceSquaredTo(point);
        double distanceToCurrent = current.distanceSquaredTo(point);

        if (distanceToNode > distanceToCurrent)
            return current;

        if (node.point.distanceSquaredTo(point) < distanceToCurrent)
            return nearestChild(node, node.point, point);
        else
            return nearestChild(node, current, point);
    }

    private Point2D nearestChild(
            KdTreeNode node, Point2D current, Point2D point) {

        if (pointLessThan(node, point))
            return nearest(
                    node.rightTop,
                    nearest(node.leftBottom, current, point),
                    point
            );
        else
            return nearest(
                    node.leftBottom,
                    nearest(node.rightTop, current, point),
                    point
            );
    }


    //_________________________________________________________________________

    private boolean pointLessThan(KdTreeNode node, Point2D point) {

        return node.vertical && point.x() < node.point.x()
                || !node.vertical && point.y() < node.point.y();
    }


    //_________________________________________________________________________

    private static class KdTreeNode {

        private Point2D point;
        private RectHV rectangle;

        private KdTreeNode leftBottom;
        private KdTreeNode rightTop;

        private boolean vertical;

        private KdTreeNode(Point2D point, RectHV rectangle, boolean vertical) {

            this.point = point;
            this.rectangle = rectangle;
            this.vertical = vertical;
        }

        private KdTreeNode(Point2D point) {

            this.point = point;
            this.rectangle = new RectHV(0, 0, 1, 1);
            this.vertical = true;
        }
    }
}
