public class InsertionSort {
    public static void main(String[] args) {
        Integer[] a = { 10, 1, -2, 5, 8, 9 };
        InsertionSort.sort(a);
        for (int b : a) {
            System.out.println(b);
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
