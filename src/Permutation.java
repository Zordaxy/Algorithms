import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> que = new RandomizedQueue<>();
        int count = 0;

        int n = Integer.parseInt(args[0]);
        if (n == 0) {
            return;
        }

        String line;
        while (!StdIn.isEmpty()) {
            line = StdIn.readString();
            if (n > count) {
                count++;
                que.enqueue(line);
            } else {
                que.dequeue();
                que.enqueue(line);
            }
        }

        for (String element : que) {
            StdOut.println(element);
        }
    }
}
