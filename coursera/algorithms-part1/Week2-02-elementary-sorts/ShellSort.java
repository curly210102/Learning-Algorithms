public class ShellSort {
    public static void main(String[] args) {
        Integer[] a = { 10, 1, -2, 5, 8, 9 };
        ShellSort.sort(a);
        for (int b : a) {
            System.out.println(b);
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;

        // 计算最大增量
        while (h > N / 3)
            h = 3 * h + 1;

        // 增量序列
        while (h >= 1) {
            // 从第二段开始逐个处理
            for (int i = h; i < N; i++) {
                // 和前面的同组数据做插入排序
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
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
