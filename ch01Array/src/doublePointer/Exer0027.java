package doublePointer;

import java.util.Arrays;

/**
 * @Author: Sihang Xie
 * @Description: 27. 移除元素 - 力扣（LeetCode）
 * @Date: 2022/6/2 20:01
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0027 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 3, 0, 4, 2};
        int val = 2;
        int size = removeElement(nums, val);
        System.out.println(size);
        System.out.println(Arrays.toString(nums));
    }

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
}
