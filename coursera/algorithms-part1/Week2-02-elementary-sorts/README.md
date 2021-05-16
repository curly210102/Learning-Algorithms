# 基础排序

## 全序关系

在 Java 中，通过 `Comparable` 接口处理比较逻辑，`Comparable` 接口中需实现 `compareTo` 方法，`compareTo()` 方法实现的是全序关系（total order）。total order 有三个性质：

1. 非对称性：如果v小于等于w，而且w小于等于v，那么这种情况成立的唯一可能是 

   v和w相等。

2. 传递性：如果 v 小于等于w，w 小于等于 x，那么 v 必须小于等于 x。

3. 完全性：要么v小于等于w，要么w小于等于v，要么两者相等



## 选择排序（Selection Sort）

在剩余的非排序项中找到最小项移动到非排序项的首部。

``` java
public static void sort(Comparable[] a) {
  int N = a.length;
  for (int i = 0; i < N; i++) {
    int min = i;
    for (int j = i + 1; j < N; j++) {
      if (less(a[j], a[min])) {
        min = j;
      }
    }
    exch(a, i, min);
  }
}
```

$N^2/2$次比较，$N$次交换

> How many compares does selection sort make when the input array is *already sorted*?
> quadratic.



## 插入排序（Insertion Sort）

从左到右处理，指针左边是有序序列，指针右边是未排序序列。处理未排序序列的第一项，将它和左边每个更大元素交换位置。

``` java
public static void sort(Comparable[] a) {
  int N = a.length;
  for (int i = 0; i < N; i++) {
    for (int j = i - 1; i >= 0; j--) {
      if (less(a[i], a[j])) {
        exch(a, i, j);
      } else {
        break;
      }
    }
  }
}
```

平均需要$N^2/4$次比较，$N^2/4$次交换

插入排序的运行时间和数据初始状态有关：

- 初始有序：$N - 1$次比较，0次交换
- 初始逆序：$N^2/2$次比较，$N^2/2$次交换

插入排序有意思的地方在于对于部分有序的数组，它的运行时间是线性的 ，交换的次数与逆序对的个数相等。



## 希尔排序（Shell Sort）

插入排序之所以效率低下是因为每个元素每次只向前移动一个位置

希尔排序的出发点是对插入排序的优化，制造一种场景创造部分有序的序列。

> 这个算法的思想很简单，但是能获得巨大的性能提升。它相当快，所以在实际中非常有用 
>
> 除了巨大的数组会变得慢。对于中等大小的数组，它甚至可以胜过经典的复杂方法。代码量也不大

``` java
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
```

对于 $3x+1$ 的增量序列最坏情况下比较的次数是 $O(N^3/2)$



## 基础排序的应用

### 洗牌（Shuffling）

``` java
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
```

