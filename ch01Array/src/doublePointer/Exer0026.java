package doublePointer;

import java.util.Arrays;

/**
 * @Author: Sihang Xie
 * @Description: 26. 删除有序数组中的重复项 - 力扣（LeetCode）
 * @Date: 2022/6/3 8:48
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0026 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }

    //快慢指针
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

    //优化版本
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
}
