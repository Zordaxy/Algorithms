package course1.implementations;

import edu.princeton.cs.algs4.StdOut;

public class Test {
    private int pass;
    private int fail;

    public Test() {
        pass = 0;
        fail = 0;
    }

    public void assertEqual(boolean statement, boolean expected) {
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
}