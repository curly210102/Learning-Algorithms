import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Shuffling
 */
public class Shuffling {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
            int j = StdRandom.uniform(i + 1);
            exch(nums, i, j);
        }

        System.out.println("------------------");
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}