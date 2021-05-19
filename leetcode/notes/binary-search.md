---
title: 数组：二分法
---

# 介绍

一个长度为 N 的数组。常规的顺序查找，需要耗费 $O(N)$ 个时间复杂度。

而二分查找则是一种优化的查找思想：将区间分成两部分，缩小可能范围，只取可能的一部分继续查找。不断折半直到查找到目标，时间复杂度降低至 $O(logN)$。

## 特征
- 二分查找适用于有序数组，需要连续的内存空间
- 对于小范围的搜索，二分查找相对顺序查找的优势不大。只有数据量比较大的时候，二分查找的优势才会比较明显。
- 如果数据之间的比较操作非常耗时，二分查找就比顺序遍历更有优势。

## 解析

### 基本使用

二分法的实现有两个注意点：
1. 中间位置计算：为了防止溢出，使用向左移位免去小数点处理，`left + ((right - left) >> 1)`
2. 区间划分：左闭右闭、左闭右开，保证整个过程中保持划分规则一致即可

写法一. 左闭右闭

``` javascript
var search = function (nums, target) {
  let left = 0;
  let right = nums.length - 1;

  while (left <= right) {
    const mid = left + ((right - left) >> 1);
    if (nums[mid] === target) {
      return mid;
    } else if (nums[mid] > target) {
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }
  return -1;
};
```

写法二. 左闭右开

``` javascript
var search = function (nums, target) {
  let left = 0;
  let right = nums.length;

  while (left < right) {
    const mid = left + ((right - left) >> 1);
    if (nums[mid] === target) {
      return mid;
    } else if (nums[mid] > target) {
      right = mid;
    } else {
      left = mid + 1;
    }
  }
  return -1;
};
```

> 层级一：基本使用，只需要抓住二分查找的特征：**针对有序序列的查找优化**，模版化套用即可

- [704. 二分查找](https://leetcode-cn.com/problems/binary-search/)
- [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position)
- [69. x 的平方根](../69.x-的平方根.md)
- [367.有效的完全平方数](../367.有效的完全平方数.md)

### 重复元素

> 层级二：二分查找的变体，查找一端元素

- 查找第一个等于给定值的元素
- 查找最后一个等于给定值的元素
- 查找第一个大于等于给定值的元素
- 查找最后一个小于等于给定值的元素

**本质上都是缩小限制范围，将左右指针往目标对象靠。**

比如 “查找第一个等于给定值的元素”，中间值和目标值比较情况：
1. 中间值小于给定值，往右边找，`l = mid + 1`
2. 中间值大于给定值，往左边找，`r = mid - 1`
3. 中间值等于给定值，继续往左边找，`r = mid - 1`

在判等之后，右指针还得继续向左移动，这里需要考虑移动的边界：
1. 数组边界：移动到数组最左端停止
2. 同值边界：左边值不等于给定值中断

``` javascript
function bsearch (nums, target) {
    let left = 0;
    let right = nums.length - 1;
    while (left <= right) {
        const mid = left + ((right - left) >> 1);
        if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
          if (nums[mid] === target && nums[mid - 1] !== target) return mid;
          else right = mid - 1;
        }
    }

    return -1;
}
```

[34. 在排序数组中查找元素的第一个和最后一个位置](../34.在排序数组中查找元素的第一个和最后一个位置.md)


### 灵活应用

> 层级三：寻找场景特征，应用二分法思想

1. 确定寻找的目标
2. 确定比较的对象
3. 确定细节
   - 起始条件：`r = nums.length - 1` 还是 `r = nums.length`，确定是左闭右闭区间还是左闭右开区间
   - 终止条件：`while(l < r)` 还是 `while(l <= r)`，循环结束的条件`l == r` 或者 `l > r`
   - 区间上下界更新方法：中值和区间的关系，中值是否需要包含在区间中
   - 返回值选择：根据结束状态确定返回值


[33. 搜索旋转排序数组](../33.搜索旋转排序数组.md)
[81. 搜索旋转排序数组 II](../81.搜索旋转排序数组-ii.md)
[153.寻找旋转排序数组中的最小值](../153.寻找旋转排序数组中的最小值.md)
[154.寻找旋转排序数组中的最小值-ii](../154.寻找旋转排序数组中的最小值-ii.md)
[540.有序数组中的单一元素](../540.有序数组中的单一元素.md)
[4.寻找两个正序数组的中位数](../4.寻找两个正序数组的中位数.md)
[29.两数相除](../29.两数相除.md)
[1482. 制作 m 束花所需的最少天数](../1482.制作-m-束花所需的最少天数.md)
[1011. 在 D 天内送达包裹的能力](../1011.在-d-天内送达包裹的能力.md)