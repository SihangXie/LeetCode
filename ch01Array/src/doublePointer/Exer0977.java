package doublePointer;

import java.util.Arrays;

/**
 * @Author: Sihang Xie
 * @Description: 977. 有序数组的平方 - 力扣（LeetCode）
 * @Date: 2022/6/3 9:40
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0977 {
    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] squares = sortedSquares(nums);
        System.out.println(Arrays.toString(squares));
    }

    //暴力解法
    //先平方，再排序
    public static int[] sortedSquares1(int[] nums) {
        //先平方
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        //再排序
        Arrays.sort(nums);
        return nums;
    }

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
}
