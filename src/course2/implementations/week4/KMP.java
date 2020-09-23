package course2.implementations.week4;

import java.util.ArrayList;

/**
 * Knuth Morris Pratt (Pattern Searching)
 * 1. Generate lps - array with longest prefix which is also proper suffix 
 * 2. Compare 2 indices i and j in text and pattern respectively 
 * 3. In case of mismatch use lps to decrease j to next sub prefix/suffix
 */
public class KMP {
    public ArrayList<Integer> KMPSearch(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int N = txt.length();
        int M = pat.length();

        // longest proper prefix that is also a suffix
        int[] lps = computeLPSArray(pat);
        int i = 0;
        int j = 0;

        while (i < N) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                // Characters lps[0..lps[j-1]] will match anyway,
                if (j != 0) {
                    // Go to nested sub suffix/prefix. Don't increment i
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }

            if (j == M) {
                result.add(i - j);
                j = lps[j - 1];
            }
        }

        return result;
    }

    /**
     * Return array indicates index of longest prefix that is also a suffix.
     */
    private int[] computeLPSArray(String pat) {
        int M = pat.length();
        int[] lps = new int[M];
        lps[0] = 0;

        int len = 0;
        int i = 1;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // Go to nested sub suffix/prefix. Don't increment i
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
