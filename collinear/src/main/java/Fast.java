/**
 * jerry
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fast {

    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        In in = new In(args[0]);

        int N = in.readInt();
        if (N < 4)
            return;

        Point[] points = new Point[N];
        Point[] sorted = new Point[N];

        for (int i = 0; i < N; ++i) {

            points[i] = new Point(in.readInt(), in.readInt());
            sorted[i] = points[i];
            points[i].draw();
        }

        Arrays.sort(points);

        List<Point[]> lines = new ArrayList<Point[]>();
        for (Point point : points) {

            Arrays.sort(sorted, point.SLOPE_ORDER);
            findLines(point, sorted, lines);
        }
        writeLines(lines);
        StdDraw.show(0);
    }


    //_________________________________________________________________________

    private static void findLines(
            Point point, Point[] sorted, List<Point[]> lines) {

        int start = 1;
        int length = 1;

        double previousSlope = point.slopeTo(sorted[1]);
        for (int i = 2; i < sorted.length; ++i) {

            double currentSlope = point.slopeTo(sorted[i]);
            if (currentSlope == previousSlope) {

                length++;

            } else {

                if (length >= 3)
                    handleLine(point, sorted, start, length, lines);

                length = 1;
                previousSlope = currentSlope;
                start = i;
            }
        }

        if (length >= 3)
            handleLine(point, sorted, start, length, lines);
    }

    private static void handleLine(
            Point point,
            Point[] sorted,
            int start,
            int length,
            List<Point[]> lines) {

        Point[] line = new Point[length + 1];
        for (int i = 0; i < length; ++i)
            line[i] = sorted[start + i];
        line[length] = point;

        Arrays.sort(line);

        if (point == line[0]) {

            line[0].drawTo(line[line.length - 1]);
            lines.add(line);
        }
    }

    private static void writeLines(List<Point[]> lines) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Point[] line : lines)
            for (int i = 0; i < line.length; i++)
                if (i < line.length - 1)
                    stringBuilder.append(line[i]).append(" -> ");
                else
                    stringBuilder.append(line[i]).append("\n");
        StdOut.print(stringBuilder.toString());
    }
}
