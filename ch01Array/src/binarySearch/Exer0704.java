package binarySearch;

/**
 * @Author: Sihang Xie
 * @Description: 704. 二分查找 - 力扣（LeetCode）
 * @Date: 2022/6/4 9:43
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 3, 4, 7, 10};
        int index = search(10, nums);
        System.out.println(index);
    }

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
}
