import java.util.Arrays;
import java.util.Comparator;

/**
 * jerry
 * 6/29/14
 */
public class Brute {

    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        In in = new In(args[0]);

        int N = in.readInt();

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {

            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        Arrays.sort(points);

        for (int i = 0; i < N; i++) {

            points[i].draw();

            for (int j = i + 1; j < N; j++) {

                double slope1 = points[i].slopeTo(points[j]);

                for (int k = j + 1; k < N; k++) {

                    double slope2 = points[i].slopeTo(points[k]);

                    if (Double.compare(slope1, slope2) == 0) {

                        for (int l = k + 1; l < N; l++) {

                            double slope3 = points[i].slopeTo(points[l]);

                            if (Double.compare(slope2, slope3) == 0) {

                                Point[] line = new Point[]{

                                        points[i],
                                        points[j],
                                        points[k],
                                        points[l]
                                };
                                Arrays.sort(line, new Comparator<Point>() {

                                    @Override
                                    public int compare(Point o1, Point o2) {

                                        return o1.compareTo(o2);
                                    }
                                });
                                line[0].drawTo(line[3]);
                                StdOut.printf(
                                        "%s -> %s -> %s -> %s\n",
                                        line[0],
                                        line[1],
                                        line[2],
                                        line[3]
                                );
                            }
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);
    }
}
