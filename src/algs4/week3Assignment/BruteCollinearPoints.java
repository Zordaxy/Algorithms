package algs4.week3Assignment;// import edu.princeton.cs.algs4.StdOut;
// import java.io.BufferedReader;
// import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> results = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points == null) {
                throw new java.lang.IllegalArgumentException();
            }
            if (i < n - 1 && points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int t = k + 1; t < n; t++) {
                        if (((Double)points[i].slopeTo(points[j])).compareTo(points[i].slopeTo(points[k])) == 0 && ((Double)points[i].slopeTo(points[j])).compareTo(points[i].slopeTo(points[t])) == 0) {

                            Point[] group = new Point[4];
                            group[0] = points[i];
                            group[1] = points[j];
                            group[2] = points[k];
                            group[3] = points[t];
                            Arrays.sort(group);

                            if (group[0] == points[i]) {
                                LineSegment matchedSegment = new LineSegment(group[0], group[3]);
                                this.results.add(matchedSegment);
                            }
                        }
                    }
                }
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
//        BruteCollinearPoints collinear = new BruteCollinearPoints(initial);
//        StdOut.println(collinear.numberOfSegments());
//        StdOut.println(Arrays.toString(collinear.segments()));
//    }
}
