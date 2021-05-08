# 算法分析

如何观察算法的性能特征。

## 观察

通过观察分析得到输入量和运行时间的关系

1. 计时，在 Java 中可以使用 StopWatch 进行计时
2. 输入量翻倍测试，得到输入量和对应的运行时间
3. 数据分析，绘制双对数坐标关系，确认是否符合幂定律 $T(N) = aN^b$
4. 预测和验证

幂定律：$T(N) = aN^b$

$b = lg(T(2N)/T(N))$，$a = T(N) / N^b$

## 数学模型

使用数学方法计算。

程序总运行时间 = 每个操作的运行时间 \* 操作次数

精确数学模型：使用高级的数学技巧精确计算开销

近似数学模型：忽略低阶项，使用数学方法计算开销最大的操作

## 增长阶数分类

算法相关的增长阶数函数：

$1、lgN、N、NlogN、N^2、N^3、2^N$

在 log-log 坐标图中表示输入量和效率关系

![Screen Shot 2021-05-07 at 10.38.00 AM](https://gitee.com/curlly-brackets/pic-bed/raw/master/Screen%20Shot%202021-05-07%20at%2010.38.00%20AM.png)

## 算法理论

针对不同输入从不同角度分析算法，算法运行时间会介于最好情况和最坏情况之间：

最好情况：下界

最坏情况：上界

平均情况：随机建模



性能界限表示：

- $O$ 算法性能的 上界
- $Ω$ 算法性能的 下界
- $Θ$ 表示增长阶数
- ~  表示近似模型



找到最优算法的方法 ：证明下界，如果上下界之间存在间隔，降低上界或者提高下界。



## 内存需求

单位回顾

Bit.  0 or 1

Byte. 8 bits

KB.  $2^{10}$ bytes

MB. $2^{20}$ bytes

GB. $2^{30}$ bytes



Java中数据所需内存空间

- boolean 1byte
- byte 1byte
- char 2bytes
- int 4bytes
- float 4bytes
- long 8bytes
- double 8bytes
- Object reference 8bytes
- Array: 24bytes + memory of each array entry
- Object: 16bytes + memory for each instance variable + 8 if inner class
- Padding: round up to multiple of 8 bytes

Example

``` java
public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}. 
     * Initially, each elements is in its own set.
     *
     * @param  n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
  ....
}
```

`WeightedQuickUnionUF` need (24 + 4n) * 2 + 4 ~ 8n