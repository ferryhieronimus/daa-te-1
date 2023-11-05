import java.util.*;

/**
 * Creator: Michael T. Goodrich
 * taken from
 * https://cs.uwaterloo.ca/~imunro/cs840/ProjectPapers/SODA10_101_goodrichm.pdf
 * with some modifications
 */
public class ShellSort {
    public static final int C = 4; // number of region compare-exchange repetitions

    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void compareExchange(int[] a, int i, int j) {
        if ((i < j && a[i] > a[j]) || (i > j && a[i] < a[j])) {
            exchange(a, i, j);
        }
    }

    public static void permuteRandom(int[] a, Random rand) {
        for (int i = 0; i < a.length; i++) {
            // Use the Knuth random perm. algorithm
            exchange(a, i, rand.nextInt(a.length - i) + i);
        }
    }

    // compare-exchange two regions of length offset each
    public static void compareRegions(int[] a, int s, int t, int offset, Random rand) {
        int[] mate = new int[offset]; // index offset array
        for (int count = 0; count < C; count++) {
            // do C region compare-exchanges
            for (int i = 0; i < offset; i++) {
                mate[i] = i;
            }
            permuteRandom(mate, rand); // comment this out to get a deterministic Shellsort
            for (int i = 0; i < offset; i++) {
                compareExchange(a, s + i, t + mate[i]);
            }
        }
    }

    public static void randomizedShellSort(int[] a) {
        int n = a.length; // we assume that n is a power of 2
        Random rand = new Random(); // random number generator
        for (int offset = n / 2; offset > 0; offset /= 2) {
            // compare-exchange up
            for (int i = 0; i < n - offset; i += offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }

            // compare-exchange down
            for (int i = n - offset; i >= offset; i -= offset) {
                compareRegions(a, i - offset, i, offset, rand);
            }

            // compare 3 hops up
            for (int i = 0; i < n - 3 * offset; i += offset) {
                compareRegions(a, i, i + 3 * offset, offset, rand);
            }

            // compare 2 hops up
            for (int i = 0; i < n - 2 * offset; i += offset) {
                compareRegions(a, i, i + 2 * offset, offset, rand);
            }

            // compare odd-even regions
            for (int i = 0; i < n; i += 2 * offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }

            // compare even-odd regions
            for (int i = offset; i < n - offset; i += 2 * offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }
        }
    }
}
