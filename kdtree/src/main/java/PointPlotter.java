

public class PointPlotter {

    public static void main(String[] args) {

        StdDraw.show(0);
        KdTree kdtree = new KdTree();

        // read in the input
        String filename = args[0];
        In in = new In(filename);

        while (!in.isEmpty()) {

            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        StdDraw.clear();
        kdtree.draw();

        // display to screen all at once
        StdDraw.show(0);
    }
}
