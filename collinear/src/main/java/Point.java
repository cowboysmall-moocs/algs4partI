/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {

            return Double.compare(slopeTo(o1), slopeTo(o2));
        }
    };       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {

        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {

        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {

        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {

        int deltaY = that.y - this.y;
        int deltaX = that.x - this.x;

        if (deltaY == 0 && deltaX == 0)
            return Double.NEGATIVE_INFINITY;

        else if (deltaY == 0)
            return +0;

        else if (deltaX == 0)
            return Double.POSITIVE_INFINITY;

        else
            return ((double) deltaY) / ((double) deltaX);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {

        int diffY = this.y - that.y;
        if (diffY == 0)
            return this.x - that.x;
        else
            return diffY;
    }

    // return string representation of this point
    public String toString() {

        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
