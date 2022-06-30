# Leetcode刷题笔记

*谢斯航*

------



# 一、 刷题心法



## 1.1 如何刻意练习

① **读题**。分析看清楚题目到底要你做什么？

② **多解**。一个题目尽可能地想到几种解法，从中选择一个比较好地解法。

③ **代码实现**。

④ **运行测试用例**。构造一些边界数据来检测程序是否运行正常。



## 1.2 五遍解题法

* 刷题第 $1$ 遍。花 5~10 分钟读题+思考。想不出来就背诵和默写别人的解。有时候可能要多对比几种解法才能掌握到最优解。
* 刷题第 $2$ 遍。马上闭上所有答案自己写。提交 Leetcode 运行，运行完看看自己答案和别人的答案的差距，并且思考自己的答案和别人的答案差距在什么地方。着手进行优化，争取让自己的解法要领先 $80$% 的人。
* 刷题第 $3$ 遍。$24$ 小时后，再重复做一遍。
* 刷题第 $4$ 遍。一周后，再重复做一遍。
* 刷题第 $5$ 遍。面试前一个月专门性进行恢复性训练。



# 二、 数组



## 2.1 二分查找



### 0704. 二分查找

原题链接：[704. 二分查找 - 力扣（LeetCode）](https://leetcode.cn/problems/binary-search/)

**1.二分查找思路**

![image-20220604095457253](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220604095457253.png)

* 二分查找的边界条件要想不乱，就要遵循循环不变量原则，即坚持左闭右闭或左闭右开的区间原则。才能有条不紊地处理边界条件。本例我遵循左闭右闭的原则。
* 先拿到数组的长度，然后计算出中间的索引值 `mid` ；
* 传入的 `target` 与数组中间的数 `nums[mid]` 对比：
  * 如果 `target < nums[mid]` ：说明要找的数在数组左边，继续在左半部分的数组计算中间索引值，一直循环对比下去。
  * 其他情况 (`target >= nums[mid]`) ：说明要找的数在数组右边，继续在右半部分的数组计算中间索引值，一直循环对比下去。

```java
//二分查找
//遵循左闭右闭的循环不变量原则
public static int search(int target, int[] nums) {
    //如果目标直接超出数组范围，直接返回-1
    if (target < nums[0] || target > nums[nums.length - 1]) {
        return -1;
    }
    int left = 0;//声明左指针
    int right = nums.length - 1;//右指针
    int mid;//中间指针
    while (left <= right) {//因为左闭右闭原则，因此左、右指针相等是有意义的
        mid = left + ((right - left) >> 1);//更新中间指针，防溢出操作
        if (target == nums[mid]) {//找到目标，直接返回中间索引值
            return mid;
        }
        if (target < nums[mid]) {//滑动窗口收缩至左半边查找
            right = mid - 1;//更新右指针，收缩滑动窗口
        } else {//滑动窗口收缩至右半边查找
            left = mid + 1;//更新左指针，收缩滑动窗口
        }
    }
    return -1;//找不到返回-1
}
```





## 2.2 快慢指针(双指针)



### 0027. 移除数组指定元素

原题链接：[27. 移除元素 - 力扣（LeetCode）](https://leetcode.cn/problems/remove-element/)

**1.暴力解法思路**

* 双层循环。第一层遍历查找 `val` ；第二层覆盖数组。

```java
//暴力解法
public static int removeElement1(int[] nums, int val) {
    //更新覆盖后新数组的长度
    int size = nums.length;
    //外层循环查找val
    for (int i = 0; i < size; i++) {
        if (nums[i] == val) {
            //内层循环覆盖
            for (int j = i; j < size - 1; j++) {
                nums[j] = nums[j + 1];
            }
            //数组全部元素往前覆盖一步，指针i也要减一才能对应上原来的遍历顺序
            i--;//这一步很容易出错，也是关键的一步
            size--;
        }
    }
    return size;
}
```



**2.快慢指针思路(双指针)**

* 双指针的思路如下所示。在一个循环内使用两个指针完成两个循环的工作。
* 快指针和慢指针的初始位置都是 0 ；




![27.移除元素-双指针法](https://camo.githubusercontent.com/a701d49e9f64bd71f08a19276679f49d971d765e07402b3982f9b0ab63969f45/68747470733a2f2f747661312e73696e61696d672e636e2f6c617267652f30303865476d5a456c7931676e7472647336723539673330647530396d6e70642e676966)

```java
//快慢指针(双指针)
public static int removeElement(int[] nums, int val) {
    //定义快慢指针
    int fastIndex = 0;
    int slowIndex;
    for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
        if (nums[fastIndex] != val) {
            nums[slowIndex] = nums[fastIndex];
            slowIndex++;
        }
    }
    return slowIndex;
}
```

【代码解析】

* 第 4 行：声明并初始化快指针 `fastIndex` ；
* 第 5 行：声明但不初始化慢指针 `slowIndex` ，因为在for循环中再声明即可；
* 第 6 行：以快指针 `fastIndex` 为循环条件，快指针在每次循环都会后移一位；
* 第 7~9 行：要等到快指针 `fastIndex` 对应的元素值不等于 `val` 时，直接把当前快指针 `fastIndex` 所指数值赋给慢指针 `slowIndex` 所指的位置，以完成删除覆盖，同时慢指针 `slowIndex` 才可以后移一位。这样的写法十分优雅简洁，可以有效解决目标数据连续出现的复杂场景。
* 当循环结束时，返回慢指针 `slowIndex` ，即为删除后新数组的长度。



### 0026. 删除有序数组重复项

原题链接：[26. 删除有序数组中的重复项 - 力扣（LeetCode）](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

**1.快慢指针思路(双指针)**

* 快指针和慢指针的初始位置都是 0 ；
* 快指针作为循环条件，快指针每次循环都会加一，当快指针到达数组边界停止循；
* 如果快指针所指数据不等于慢指针，慢指针加一，再把快指针当前所指数据赋给慢指针所指位置。
* 返回慢指针 + 1；即为新数组的长度。

【初始版本】

![image-20220603093032839](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603093032839.png)

```java
//初始版本
public static int removeDuplicates1(int[] nums) {
    //初始化快慢指针
    int fastIndex = 0;
    int slowIndex;
    //一次循环
    for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
        if (nums[fastIndex] != nums[slowIndex]) {
            nums[++slowIndex] = nums[fastIndex];
        }
    }
    return slowIndex + 1;
}
```



【优化版本】考虑一开始就没有重复数据的情况，避免出现冗余的把快指针所指的数据赋给慢指针的操作。

![image-20220603092811972](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603092811972.png)

```java
//快慢指针
public static int removeDuplicates(int[] nums) {
    //初始化快慢指针
    int fastIndex = 0;
    int slowIndex;
    //一次循环
    for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
        if (nums[fastIndex] != nums[slowIndex]) {
            if (++slowIndex == fastIndex) {
                continue;
            }
            nums[slowIndex] = nums[fastIndex];
        }
    }
    return slowIndex + 1;
}
```



### 0977. 有序数组的平方

原题链接：[977. 有序数组的平方 - 力扣（LeetCode）](https://leetcode.cn/problems/squares-of-a-sorted-array/)

**1.暴力解法思路**

* 先平方，再排序。

```java
//暴力解法
//先平方，再排序
public static int[] sortedSquares(int[] nums) {
    //先平方
    for (int i = 0; i < nums.length; i++) {
        nums[i] *= nums[i];
    }
    //再排序
    Arrays.sort(nums);
    return nums;
}
```



**2.双指针思路**

* 因为数组已经是排好序的，平方后，数组的元素大小排序发生改变是由负数平方后变大而导致的。因此平方过后的最大值要么是第一个元素，要么是最后一个元素，绝不可能是中间的元素。
* 按这个思路，把左指针声明在数组 `A ` 起始位置；右指针声明在数组末尾；
* 创建一个与传入数组一样的数组作为结果集 `result` ，结果集的指针 `k` 指向末尾；
* 比较数组 `A ` 两端元素平方后的大小。大的那一个数据放入结果集中 `k` 的位置，放入后，大的数据的指针前移 (或后移) ；结果集指针 `k` 前移一位。
* 数组 `A ` 中只有放入了结果集 `result` 的元素之后，对应的指针才能移动。否则要一直保持不变，比较大小。

![image-20220603103734171](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603103734171.png)

![image-20220603103129687](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603103129687.png)

```java
//快慢指针思路(双指针)
public static int[] sortedSquares(int[] nums) {
    int len = nums.length;
    //声明并初始化双指针
    int left = 0;
    int right = len - 1;
    //创建结果集和结果集指针
    int[] result = new int[len];
    int k = right;
    //一次循环比较
    while (left <= right) {
        if (nums[left] * nums[left] < nums[right] * nums[right]) {
            //大的数存入结果集，结果集指针前移一位
            result[k--] = nums[right] * nums[right--];//大的数对应的指针移动一位
        } else {
            result[k--] = nums[left] * nums[left++];
        }
    }
    return result;
}
```



## 2.3 滑动窗口



### 0209. 长度最小的子数组

原题链接：[209. 长度最小的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-size-subarray-sum/)

**1.我对滑动窗口的自己思路**

* 声明一个窗口大小 `windowSize` ，初始 `windowSize = 0` ，但实际窗口子数组长度为1，此时两个指针重合； 
* 采用两个指针，窗口左边 `left` 和窗口右边 `right` ，分别作为窗口的两个界线，两个指针之间组成的就是原数组的一个子数组。两者的关系是：`right = left + windowSize` ；
* 由于题目要找最小数组，因此，窗口大小 `windowSize` 随着循环递增；
* 刚开始时窗口大小 `windowSize  = 0` ，窗口滑动遍历数组，如果窗口内的子数组的和满足 `>= target` 的条件，则输出该子数组。
* 如果遍历完一次后没有满足  `>= target` 的子数组，则窗口大小 `windowSize` 加一，再次滑动遍历数组，寻找满足 `>= target` 的子数组。

|                          刚开始循环                          |                     找到满足条件的子数组                     |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![image-20220603145832826](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603145832826.png) | ![image-20220603142325003](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603142325003.png) |

```java
//我对滑动窗口的理解思路
//LeetCode超时了
public static int minSubArrayLen(int target, int[] nums) {
    //窗口大小
    int winSize;
    //声明窗口指针
    int left, right;

    //外循环：以窗口大小为循环条件，当窗口大小为数组长度时终止循环
    for (winSize = 0; winSize < nums.length; winSize++) {
        //内循环：以左指针为循环条件，遍历以寻找窗口子数组和>=target的子数组
        for (left = 0; (right = left + winSize) < nums.length; left++) {
            //当窗口大小只有1时，不需要执行窗口子数组累加操作
            if (winSize == 0) {
                if (nums[left] >= target) {
                    return winSize + 1;
                }
            } else {//窗口大小大于等于2时需要执行累加操作
                int sum = 0;//窗口子数组的和
                //窗口子数组累加求和
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                //判断窗口子数组的和是否大于等于target
                if (sum >= target) {
                    return winSize + 1;
                }
            }
        }
    }
    return 0;
}
```

我自己写的暴力滑动窗口的时间复杂度显然是 $O(n^3)$ ，在提交到 LeetCode 后超时不通过。正确的滑动窗口的时间复杂度是  $O(n)$ ，即一次循环就能得出正确答案。因此我需要好好修改一下。



**2.正确的滑动窗口思路**

![209.长度最小的子数组](https://camo.githubusercontent.com/dd84aee84237ebb78cf7ffde58803dc03350a4071d0981b8add65d9c59199ac4/68747470733a2f2f636f64652d7468696e6b696e672e63646e2e626365626f732e636f6d2f676966732f3230392e2545392539352542462545352542412541362545362539432538302545352542302538462545372539412538342545352541442539302545362539352542302545372542422538342e676966)

```java
//正确的滑动窗口思路
public static int minSubArrayLen(int target, int[] nums) {
    int result = Integer.MAX_VALUE;//保存结果的变量，初始化为Integer的最大表示范围
    int sum = 0;//窗口数值之和
    int left = 0;//窗口起始位置，即左指针
    int subLength = 0;//窗口大小，即窗口数组长度
    //以窗口末尾位置(即右指针)为循环变量
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];//窗口数值求和
        //下面这段是算法的精髓
        //每次更新窗口起始位置(即左指针)，并不断比较子数组是否符合条件
        while (sum >= target) {
            subLength = right - left + 1;//计算窗口长度
            result = subLength > result ? result : subLength;//更新结果，确保不超过表示范围
            sum -= nums[left++];//滑动窗口的精髓，动态地调整窗口起始位置，并减去移出窗口的数值
        }
    }
    //result如果没被赋值则返回0
    return result == Integer.MAX_VALUE ? 0 : result;
}
```

* 声明并初始化结果 `result = Integer.MAX_VALUE` 。以免返回结果超出整数包装类 Integer 的表示范围 $2^{31}-1$；
* 声明并初始化窗口数值之和 `sum= 0` ；
* 声明并初始化滑动窗口的长度 `subLength = 0` ；
* 声明并初始化窗口起始位置 `left = 0` ；
* 进入循环，以窗口末尾位置 `right` 为循环条件。当 `right` 达到数组末尾时，循环结束。每次循环 `right` 加一；
* 左指针 `left` 先不动，右指针 `right` 不断右移一位，同时不断计算窗口数值之和 `sum` 。直到窗口数值之和 `sum` ≥ `target` 为止。
* 计算此时窗口长度 `sunLength` ，与 `result = Integer.MAX_VALUE` 对比确保窗口长度 `sunLength` 在 Integer 的表示范围之内，并把 `subLength` 赋给 `result` ；
* 此时最能体现滑动窗口精髓的一步来了。`sum -= nums[left++]` ：不断把左指针 `left` 后移，同时减去 `sum` 中相应的大小。以达到在保证窗口数值之和 `sum` ≥ `target` 的条件下，尽可能地缩小窗口长度 `sunLength` 的目的。不断循环直到窗口数值之和 `sum` < `target` 为止。





【总结】

* 通过上面的分析和解题，很明显，其实滑动窗口也是双指针的一种。
* 只是前面的双指针，仅仅关注两个指针所指的内容；而滑动窗口的双指针则更关注两个指针之间所有的内容 (例如，本题的两个指针之间所有元素的和) 。
* 以后如果碰到求连续子数组类型的题目，可以考虑采用滑动窗口的方法。



### 0904. 水果成篮

原题链接：[904. 水果成篮 - 力扣（LeetCode）](https://leetcode.cn/problems/fruit-into-baskets/)



## 2.4 无算法题目



### 0059. 螺旋矩阵II

原题链接：[59. 螺旋矩阵 II - 力扣（LeetCode）](https://leetcode.cn/problems/spiral-matrix-ii/)

**1.我的思路**

* 根据输入的正整数 `n` 创建一个 $n×n$ 的二维数组；
* 根据一层一层地来放元素：
  * 填充上行从左到右；
  * 填充右列从上到下；
  * 填充下行从右到左；
  * 填充左列从下到上；
* 依然要遵循二分法里提到的循环不变量原则，即坚持左闭右闭或左闭右开的区间原则。才能有条不紊地处理边界条件。本题我按照左闭右开的法则来处理，如下图所示：每个相同颜色的色块就是我一次要处理的。

![image-20220603211640697](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220603211640697.png)





# 三、 链表

在 LeetCode 中，链表的结构已经给定义好了，不需要再自己定义。



## 3.1 设计链表及其功能



### 0203. 移除链表元素

原题链接：[203. 移除链表元素 - 力扣（LeetCode）](https://leetcode.cn/problems/remove-linked-list-elements/)

Ps. 链表的增删改查也能作为算法题出现是我没想到的。本题用的是单向链表，但 LeetCode 定义的单链表没有定义头节点属性。因此我会在代码中虚拟一个头节点。

**1.我的思路**

![image-20220604134226305](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220604134226305.png)

* 注意此题和以前的链表删除节点不同，以前的链表删除节点只删除一个。但此题要求删除所有 `val` 相同的节点，因此在下面第 18 行的代码一定要用 else 分支：如果定位到要删除节点的前一个节点，并且已经把 `temp.next` 修改为要删除节点的下一个节点的话，就不需要后移辅助节点 `temp` 了。因为还要接着判断新接上来的节点是否仍然是要删除的节点。

```java
//使用虚拟头节点
public ListNode removeElement(ListNode head, int val) {
    //如果链表为空，则直接返回空链表
    if (head == null) {
        return head;
    }
    //创建虚拟头节点，其next指向传入的链表
    ListNode virHead = new ListNode(-1, head);
    //创建辅助节点temp，用于遍历链表
    ListNode temp = virHead;
    //开始循环遍历，定位到要删除节点的前一个节点
    while (temp.next != null) {//遍历到最后一个节点，退出循环
        if (temp.next.val == val) {
            //把当前next指向要移除节点的后一个节点
            temp.next = temp.next.next;
        } else {//注意这里一定要用else，因为是要移除所有val相同的节点
            //辅助节点后移一位
            temp = temp.next;
        }
    }
    //virHead.next才是新的头节点
    return virHead.next;
}
```



### 0707. 设计链表

原题链接：[707. 设计链表 - 力扣（LeetCode）](https://leetcode.cn/problems/design-linked-list/)

Ps. 惊喜梅开二度，不得不感慨韩顺平真是牛逼！实现链表的功能韩顺平的视频中全部都讲解得非常详细，这道中等题信手拈来。所以，要不是赶着期末考试，还是一定要回去把韩顺平的[【尚硅谷】数据结构与算法（Java数据结构与算法）](https://www.bilibili.com/video/BV1E4411H73v?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click) 这套视频好好看一看。



**1.我的思路**

思路就不写了，已经非常熟练了。直接上代码。

```java
//我的思路，本题我使用双向链表
class MyLinkedList {
    //属性
    Node head = new Node();

    //节点内部类
    class Node {
        int val;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node prev, int val, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //空参构造器
    public MyLinkedList() {
    }

    public void show() {
        if (head.next == null) {
            return;
        }
        Node temp = head.next;
        while (true) {
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        Node temp = head;
        while (index >= 0) {
            if (temp.next == null) {
                return -1;
            }
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    //在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
    public void addAtHead(int val) {
        if (head.next == null) {
            head.next = new Node(head, val, null);
            return;
        }
        Node newNode = new Node(head, val, head.next);
        head.next.prev = newNode;
        head.next = newNode;
    }

    //将值为 val 的节点追加到链表的最后一个元素。
    public void addAtTail(int val) {
        if (head.next == null) {
            head.next = new Node(head, val, null);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(temp, val, null);
    }

    //在指定索引index前一个位置插入元素val
    //注意这个index是可以超出链表长度一个的，即左闭右开的
    public void addAtIndex(int index, int val) {
        if (head.next == null && index > 0) {
            return;
        }
        if (head.next == null && index == 0) {
            head.next = new Node(head, val, null);
        }
        if (index < 0) {
            addAtHead(val);
        }
        Node temp = head;
        //此题定位到index所指的节点本身
        while (index >= 0) {
            if (temp.next == null) {
                temp.next = new Node(temp, val, null);
                return;
            }
            temp = temp.next;
            index--;
        }
        Node newNode = new Node(temp.prev, val, temp);
        temp.prev.next = newNode;
        temp.prev = newNode;
    }

    //如果索引 index 有效，则删除链表中的第 index 个节点。
    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }
        if (head.next == null) {
            return;
        }
        Node temp = head;
        while (index >= 0) {
            if (temp.next == null) {
                return;
            }
            temp = temp.next;
            index--;
        }
        if (temp.next == null) {
            temp.prev.next = null;
            return;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }
}
```

我的思路虽然通过了，但是只采用了头节点，没有使用尾节点和 `size` 。效率明显会逊色很多。看了代码随想录的代码，才意识到我写的代码太low了。下面是代码随想录的思路。



**2.代码随想录思路**

* 采用双向链表，采用 `head` 头节点和 `tail` 尾节点两个哨兵节点：在链表很长时可以根据 `index` 在链表前半部分还是后半部分，判断应该从前遍历还是从后遍历，以提高效率。
* 此外，链表属性还增加了链表的长度 `size` ，可以方便地进行更多的判断。

![image-20220604180831101](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220604180831101.png)



### 0206. 反转链表

原题链接：[206. 反转链表 - 力扣（LeetCode）](https://leetcode.cn/problems/reverse-linked-list/)



**1.我的思路：头插法**

题目说只能用单链表，要自己虚拟头节点。

![image-20220620154729670](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220620154729670.png)

* 我的想法是，不能破坏原有链表的结构，想另外新建一个单链表存放反转后的链表。采用“头插法”反转原有链表。
* 第一步，传入待反转的链表，设置链表指针 `temp1` ，从head头节点开始遍历。
* 第二步，新建单链表头节点 `temp2` ，用于保存反转后的链表。
* 把头插法的方法抽象出来，当 `temp1` 在从前往后遍历时，每取出一个节点，就以头插法的方式插入 `temp2` 的链表。
* 最后返回 `temp2` 的链表，得到反转后的链表。

```java
//我的思路：不破坏原有链表，使用头插法
public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
        return head;
    }
    ListNode originHead = new ListNode(); //对传入的节点设置头节点
    originHead.next = head; //设置的头节点与传入的链表第一个进行绑定
    ListNode newHead = new ListNode(); //设置新链表用于保存反转后的结果
    ListNode originTemp = originHead; //原链表的辅助节点(指针)
    while (true) { //开始遍历原链表
        if (originTemp.next == null) { //最后一个节点了，退出遍历
            break;
        }
        ListNode node = new ListNode(originTemp.next.val, newHead.next); //依次取出原链表的节点
        newHead.next = node;//取出的节点以头插法插入到新头节点和newHead.next之间
        originTemp = originTemp.next; //辅助节点后移一位
    }
    return newHead.next;
}
```



**2.carl哥思路一：双指针法**

Carl哥采用了双指针法。不断地把当前 `cur` 指针的next指向 `pre` 指针。直到 `cur` 指针为空，返回 `pre` 作为链表头节点入口即可。



* 双指针初始化：双指针也是节点 Node 类的对象，注意不要糊涂了。`pre` 指针是一个空节点 (类似于 head 头节点) 。`cur` 指向原链表的第一个节点。
* 把 `cur.next` 指向 `pre` 。

![image-20220620182412751](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220620182412751.png)

* `cur` 和 `pre` 同时向后移动一位。在移动之前，需要新建一个辅助节点 `temp` ，用来保存反转前的 `cur.next` 的节点信息。否则 `cur.next` 指向 `pre` 之后，反转前的 `cur.next` 的节点就丢失了。

![image-20220620183155198](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220620183155198.png)

* 不断循环上述过程，直到 `cur` 指向 null 为止。把 `pre` 返回。

动态过程图如下所示：

![68747470733a2f2f747661312e73696e61696d672e636e2f6c617267652f30303865476d5a456c7931676e7266316f626f757067333067793063343471702e676966](https://camo.githubusercontent.com/36cf9298bccf54091dbcabb9ede884bf98d5b2f6f04bd89a36ac2904b26d0971/68747470733a2f2f747661312e73696e61696d672e636e2f6c617267652f30303865476d5a456c7931676e7266316f626f757067333067793063343471702e676966)

```java
//carl哥思路一：双指针法
public static ListNode reverseList2(ListNode head) {
    if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
        return head;
    }
    ListNode pre = null; //pre初始化为空，类似于head头节点
    ListNode cur = head; //cur初始化为链表第一个节点
    ListNode temp = null; //temp保存反转前cur.next的节点，防止反转后丢失
    while (cur != null) {
        temp = cur.next;
        cur.next = pre; //反转
        pre = cur; //pre后移一位
        cur = temp; //cur后移一位
    }
    return pre;
}
```



**3.Carl哥思路二：双指针的递归法**

* 思路和双指针一样，只是用了递归。

```java
//carl哥思路二：双指针的递归法
public static ListNode reverseList(ListNode head) {
    return reverse(null, head);
}

private static ListNode reverse(ListNode pre, ListNode cur) {
    if (cur == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
        return pre;
    }
    ListNode temp = null;
    temp = cur.next;
    cur.next = pre;
    return reverse(cur, temp);
}
```

【总结】

* 双指针法：只要涉及到线性数据结构中，两个节点之间的操作，就要立马想到用双指针法。双指针关注的是两个指针所指向的两个节点之间的关系和操作；而滑动窗口则更关注两个指针所指的两个节点之间所有节点的关系和操作。前者是两点，后者是一段。
* 递归：当出现循环，且更新赋值的时候，可以考虑使用递归实现。





### 0024. 两两交换链表中的节点

原题链接：[24. 两两交换链表中的节点 - 力扣（LeetCode）](https://leetcode.cn/problems/swap-nodes-in-pairs/)



**1.我的思路：双指针法**

* 疑问点：如果链表节点个数为奇数，如何两两交换？
* 体会：辅助节点 `temp` 是用来给双指针往后移动用的，不是在交换的时候用的。`temp` 和 `temp.next` 是绝对不允许被改变的。操作过程只由 `pre` 和 `cur` 双指针完成。
* 注意：做这种模拟其过程的题目，一定要画图，否则会乱成一团浆糊。

【节点数量为双数】

![image-20220621171405339](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220621171405339.png)



【节点数量为单数】

![image-20220621171605203](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220621171605203.png)

【代码】

```java
//我的思路：双指针法
public static ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
        return head;
    }
    ListNode cur = head; //cur当前指针
    ListNode virHead = new ListNode(); //虚拟头节点
    virHead.next = head;
    ListNode pre = virHead; //pre前指针
    ListNode temp = null; //辅助节点temp
    while (cur != null) {
        if (cur.next == null) { //解决节点个数为奇数时，没法两两交换时直接退出
            break;
        }
        temp = cur.next; //保存交换前的cur.next节点信息，以免丢失
        pre.next = temp; //第一个箭头
        if (temp.next == null) {
            cur.next = null;
        } else {
            cur.next = temp.next; //第二个箭头
        }
        //双指针后移，temp.next没改，要在下一次循环中改
        pre = cur; //pre后移
        temp.next = cur; //第三个箭头
        cur = cur.next; //cur后移
    }
    return virHead.next;
}
```

实际上用到了 `pre` 、`cur` 和 `temp` 一共三个指针，没有想到传入的参数 `head` 也是可以当作节点的指针，过程模拟得还是有点复杂了。



**2.Carl哥的思路**

Carl哥思路仅用到  `pre` 和 `temp` 两个指针，此外，还把传入的参数 `head` 也当作节点的指针加以利用。代码一下子就简洁很多了。

![image-20220625205614107](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220625205614107.png)

```java
//Carl哥的思路：
public static ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
        return head;
    }
    ListNode virHead = new ListNode(0, head); //创建虚拟头节点
    ListNode pre = virHead; //前指针
    ListNode temp = null; //创建辅助节点
    while (pre.next != null && pre.next.next != null) { //这样的循环条件可以一举解决奇偶节点数量问题
        temp = head.next.next; //缓存head指针后移位置
        pre.next = head.next; //步骤①
        head.next.next = head; //步骤②
        head.next = temp; //步骤③
        //指针后移
        pre = head; //pre后移2位
        head = head.next; //head后移1位
    }
    return virHead.next;
}
```



【总结】

* 在链表类的题目中，`temp` 辅助节点在绝大多数情况下，是用来缓存链表变动前给指针往后移动的位置的。在操作过程中绝不允许改变 `temp` 和 `temp.next` 。
* 有时候传入的参数 `head` 也可以作为指针使用。即可以往后移动更新其位置。



### 0019. 删除链表的倒数第N个结点

原题链接：[19. 删除链表的倒数第 N 个结点 - 力扣（LeetCode）](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)



**1.我的思路：先反转，再删除，再反转**

这样的时间复杂度无疑是 $O(3n)$ ，最坏的情况下要遍历 3 次。

```java
//我的思路：先反转，再删除
public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) { //如果链表为空，直接返回
        return null;
    }
    ListNode newHead = reverseList(head);
    //反转完毕，下面开始搜索n并删除
    ListNode virHead = new ListNode(0, newHead); //创建虚拟头节点
    ListNode temp = virHead; //创建辅助节点
    int counter = n; //计数器
    temp = virHead; //辅助节点指向反转后的虚拟头节点
    while (counter > 1) { //定位到要删除节点的前一个节点
        temp = temp.next; //辅助节点后移一位
        counter--;
    }
    //定位完毕，开始删除
    temp.next = temp.next.next; //删除节点
    return reverseList(virHead.next); //再反转回开始顺序的链表，返回
}

//把反转链表的功能抽象出来
private static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode pre = null;
    ListNode temp = null;
    while (head != null) { //双指针法反转链表
        temp = head.next;
        head.next = pre;
        pre = head; // pre后移一位
        head = temp; //head后移一位
    }
    return pre;
}
```



**2.Carl哥思路：快慢指针，快针先跑n+1，再快慢同时移动**

这个方法我学习之后也是叹为观止。核心思想是先让快指针 `fast` 先移动 `n+1` 步，慢指针 `slow` 不动。然后再让快慢指针同时往后移动，直到快指针 `fast` 到达链表末尾为止。此时 `slow` 就定位到要删除节点的上一个节点了，接下来只要 `slow.next = slow.next.next` 就完成了倒数第 n 个节点的删除。

这样只遍历一遍就可以完成，时间复杂度为 $O(n)$ 。以下是步骤详解：

* 创建虚拟头节点 `virHead` ，创建快慢指针并初始化，都指向虚拟头节点 `virHead` 。

  ![image-20220626141337001](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220626141337001.png)

* 慢指针 `slow` 先不动，快指针 `fast` 先移动 `n + 1` 步。之所以是 `n + 1` ，是为了让慢指针 `slow` 定位到要删除节点的上一个节点。

  ![image-20220626141406334](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220626141406334.png)

* 快慢指针同时移动，直到快指针 `fast` 指向 `null` 为止。此时慢指针 `slow` 定位到要删除节点的上一个节点。

  ![image-20220626141440515](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220626141440515.png)

* 接下来只要 `slow.next = slow.next.next` 就完成了倒数第 n 个节点的删除。

  ![image-20220626141637776](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220626141637776.png)

```java
//Carl哥思路：快慢指针，快针先跑n，再快慢同时移动
public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) { //如果链表为空或只有一个节点，直接返回空链表
        return null;
    }
    ListNode virHead = new ListNode(0, head); //创建虚拟头节点
    ListNode fast = virHead; //快指针
    ListNode slow = virHead; //慢指针
    while (fast != null) {
        if (n > -1) { //这里是-1是为了让slow定位到要删除节点的上一个节点
            fast = fast.next; //快指针先移动n步
            n--;
        } else { //快慢指针同时后移
            fast = fast.next;
            slow = slow.next;
        }
    }
    slow.next = slow.next.next; //删除倒数第n个节点
    return virHead.next;
}
```