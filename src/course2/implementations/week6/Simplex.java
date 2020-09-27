package course2.implementations.week6;

/**
 * Determines an optimal solution to the linear program
 * matrix, b is an m-length vector, and c is an n-length vector.
 * 
 * 13A + 23B <-- maximize
 * 5A + 15B ≤ 480 subject
 * 4A + 4B ≤ 160
 * 35A + 20B ≤ 1190
 * A , B ≥ 0
 * 
 * 13A + 23B − Z = 0
 * 5A + 15B + SC = 480 subject
 * 4A + 4B + SH = 160
 * 35A + 20B + SM = 1190
 * A , B , SC , SH , SM ≥ 0
 * 
 * 5 15 1 0 0 480
 * 4 4 0 1 0 160
 * 35 20 0 0 1 1190
 * 13 23 0 0 0 0
 *
 * expected result: x0 = 12, x1 = 28, opt = 800
 */
public class Simplex {
    private static final double EPSILON = 1.0E-10;
    private double[][] a;
    private int m; // number of constraints
    private int n; // number of original variables

    private int[] basis;

    public Simplex(double[][] A, double[] b, double[] c) {
        m = b.length;
        n = c.length;
        for (int i = 0; i < m; i++) {
            if (!(b[i] >= 0)) {
                throw new IllegalArgumentException("negative RHS value");
            }
        }

        a = new double[m + 1][n + m + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = A[i][j];
            }

        }

        for (int i = 0; i < m; i++) {
            a[i][n + i] = 1.0;
        }
        for (int j = 0; j < n; j++) {
            a[m][j] = c[j];
        }
        for (int i = 0; i < m; i++) {
            a[i][m + n] = b[i];
        }
        basis = new int[m];
        for (int i = 0; i < m; i++) {
            basis[i] = n + i;
        }
        solve();
    }

    private void solve() {
        while (true) {
            int q = bland();
            if (q == -1)
                break; // optimal

            int p = minRatioRule(q);
            if (p == -1) {
                throw new ArithmeticException("LP (linear program) is unbounded");
            }
            pivot(p, q);
            basis[p] = q;
        }
    }

    private int bland() {
        for (int j = 0; j < m + n; j++) {
            if (a[m][j] > 0)
                return j;
        }
        return -1; // optimal
    }

    private int minRatioRule(int q) {
        int p = -1;
        for (int i = 0; i < m; i++) {
            if (a[i][q] <= EPSILON)
                continue;
            else if (p == -1)
                p = i;
            else if ((a[i][m + n] / a[i][q]) < (a[p][m + n] / a[p][q]))
                p = i;
        }
        return p;
    }

    private void pivot(int p, int q) {

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m + n; j++) {
                if (i != p && j != q) {
                    a[i][j] -= a[p][j] * a[i][q] / a[p][q];
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            if (i != p)
                a[i][q] = 0.0;
        }
        for (int j = 0; j <= m + n; j++) {
            if (j != q)
                a[p][j] /= a[p][q];
        }
        a[p][q] = 1.0;
    }

    public double value() {
        return -a[m][m + n];
    }

    public double[] primal() {
        double[] x = new double[n];
        for (int i = 0; i < m; i++) {
            if (basis[i] < n) {
                x[basis[i]] = a[i][m + n];
            }
        }
        return x;
    }
}
