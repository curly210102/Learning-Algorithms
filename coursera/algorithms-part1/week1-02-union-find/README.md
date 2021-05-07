# Union-Find

This course introduces the steps of designing a usable algorithm by the example of Union-Find.



## the steps of designing a usable algorithm

1. Model the problem. 
2. Find an algorithm to solve it. 
3. Fast enough? Fits in memory? ・
4. If not, figure out why. 
5. Find a way to address the problem. 
6. Iterate until satisfied

> Can we do better?





## Example: Dynamic connectivity

### The Problem

Is there a path between two points？

### Model the problem

Model object

- represent point object: map point object to index number

- represent connectivity: **connected components** Maximal set of objects that are mutually  connected.

Operations

- Find: check whether two points are in the same connected components
- Union: merge two connected components to one

### Quick-Find Algorithm

**DataStructure** 

Array records point id, the two points in the same connected components have the same id.

**Find**

query and compare two Array item

**Union**

Iterate over the Array update points' id

``` java
/**
 * QuickFindUF
 */
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        if (!connected(p, q)) {
            int pid = id[p];
            int qid = id[q];
            for (int i = 0; i < id.length; i++) {
                if (pid == id[i]) {
                    id[i] = qid;
                }
            }
        }
    }
}
```

**Efficiency**

- Initialize: N
- Find: N
- Union: N * M 

Statements execute N times when union. If the union is called M times, the efficiency is N * M, It's a bit slow. 

In the union function, The algorithm has to iterate all array items, it's unnecessary. 

How to update the algorithm query and update the needed item.



### Quick-Union Algorithm

Maintain connected component as a tree, thus, we only need update the root id of the tree,  merge two trees to one.

**DataStructure**

Array item records parent id.

**Find**

start from the point, find the root item along the tree, compare two root id.

**Union**

find root items of two point, change the root id of one to the other.

```java
public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);

        id[pRoot] = qRoot;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }
}
```

**Efficiency**

Worst: Single branch tree

- initialize: N
- Find: N
- Union: N*M

Best: Flat

- intialize: N
- Find: 1
- Union: 1

We can do some improvments by control the structure of tree

### Weighted Quick Union

Merge the small tree into the big tree. Maintain an extra array to record weighting.

```java
public class QuickUnionWeighted {
    private int[] id;
    private int[] sz;

    public QuickUnionWeighted(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);

        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }
}

```

**Efficiency**

- intialize: N
- Find: lgN
- Union: lgN

Can the tree be flatter?



### Path compression

When find, Move the passing node to the root node.

```java
private int root(int i)
{
   while (i != id[i])
   {
	   	id[i] = id[id[i]];
  	 	i = id[i];
   }
   return i;
}
```

Amazing fact. [Fredman-Saks] No linear-time algorithm exist





## Application: percolates

1. up to 4 calls to union()
2. virtual top site/bottom site