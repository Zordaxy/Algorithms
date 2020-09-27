package course2.implementations.week6;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Week6Test {
    @Test
    public void TrieTest() {
        double[] c = {  13.0,  23.0 };
        double[] b = { 480.0, 160.0, 1190.0 };
        double[][] A = {
            {  5.0, 15.0 },
            {  4.0,  4.0 },
            { 35.0, 20.0 },
        };
        Simplex lp = new Simplex(A, b, c);
        double[] x = lp.primal();

        assertEquals(800.0, lp.value(), 0.1);
        assertEquals(12, x[0], 0.1);
        assertEquals(28, x[1], 0.1);
    }
}
