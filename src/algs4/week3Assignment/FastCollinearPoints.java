package algs4.week3Assignment;// import edu.princeton.cs.algs4.StdOut;
// import java.io.BufferedReader;
// import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class FastCollinearPoints {
    private final ArrayList<LineSegment> results = new ArrayList<>();

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        int n;
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        n = points.length;
        Arrays.sort(points);
        Point[] aux = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new java.lang.IllegalArgumentException();
            }
            if (i < n - 1 && points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
            aux[i] = points[i];
        }

        for (int i = 0; i < n; i++) {
            Comparator<Point> comparator = points[i].slopeOrder();
            Point base = points[i];
            Arrays.sort(aux, comparator);

            int index = 0;
            while (index <= n - 3) {
                int firstIndex = index;
                int lastIndex = index + 1;
                while (lastIndex < n && ((Double)base.slopeTo(aux[firstIndex])).compareTo(base.slopeTo(aux[lastIndex])) == 0) {
                    lastIndex++;
                }
                int size = lastIndex - firstIndex;
                if (size < 3) {
                    index = lastIndex;
                    continue;
                }

                Point[] group = new Point[size + 1];
                for (int k = 0; k < size; k++) {
                    group[k] = aux[firstIndex];
                    firstIndex++;
                }
                group[size] = points[i];
                Arrays.sort(group);

                if (group[0] == points[i]) {
                    LineSegment matchedSegment = new LineSegment(group[0], group[size]);
                    this.results.add(matchedSegment);
                }
                index = ++lastIndex;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return results.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] resultsArray = new LineSegment[this.results.size()];
        return this.results.toArray(resultsArray);
    }

    // java -cp C:\localProjects\algs4\lib\algs4.jar;. Client input8.txt
//    public static void main(String[] args) {
//        final String FILENAME = "C:\\localProjects\\algs4\\src\\input50.txt";
//        FileReader fr = null;
//        BufferedReader br = null;
//        Point[] initial = null;
//        try {
//            fr = new FileReader(FILENAME);
//            br = new BufferedReader(fr);
//            String line;
//            Boolean firstLine = true;
//            int counter = 0;
//            while ((line = br.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    initial = new Point[Integer.parseInt(line.trim())];
//                } else {
//                    String[] pair = line.trim().split("\\s+");
//                    initial[counter] = new Point(Integer.parseInt(pair[0].trim()), Integer.parseInt(pair[1].trim()));
//                    counter++;
//                }
//            }
//        } catch (Exception e) {
//        } finally {
//            try {
//                if (br != null)
//                    br.close();
//                if (fr != null)
//                    fr.close();
//            } catch (Exception e) {
//            }
//        }
//        StdOut.println(Arrays.toString(initial));
//        FastCollinearPoints collinear = new FastCollinearPoints(initial);
//        StdOut.println(collinear.numberOfSegments());
//        StdOut.println(Arrays.toString(collinear.segments()));
//    }
}
