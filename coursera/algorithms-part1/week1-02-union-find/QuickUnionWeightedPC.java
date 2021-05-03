/**
 * QuickUnionWeightedPC
 */
public class QuickUnionWeightedPC {
    private int[] id;
    private int[] sz;

    public QuickUnionWeightedPC(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        QuickUnionWeightedPC qu = new QuickUnionWeightedPC(10);
        qu.union(0, 1);
        qu.union(2, 4);
        qu.union(3, 5);
        qu.union(5, 7);
        qu.union(1, 3);
        qu.union(7, 2);
        System.out.println(qu.find(0, 3));
        System.out.println(qu.find(0, 4));
    }
}