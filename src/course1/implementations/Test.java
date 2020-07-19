package course1.implementations;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * assertEquals - to add assertion
 * printResult - use at the end to print passed/failed numbers
 */
public class Test {
    private int pass;
    private int fail;

    public Test() {
        pass = 0;
        fail = 0;
    }

    public void assertEquals(boolean statement, boolean expected) {
        if (statement == expected) {
            pass++;
        } else {
            fail++;
            StdOut.print("Fails on " + (pass + fail) + " statement");
        }
    }

    public void printResult() {
        StdOut.println("Testing complete. " + (fail == 0 ? "PASSED" : "FAILED"));
        StdOut.println("Passed: " + pass + " failed " + fail);
    }

    public static Double[] generateArray() {
        final int N = 10;
        final Double[] a = new Double[10];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }
}