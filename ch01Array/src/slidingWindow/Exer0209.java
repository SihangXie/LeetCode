package slidingWindow;

/**
 * @Author: Sihang Xie
 * @Description: 209. 长度最小的子数组 - 力扣（LeetCode）
 * @Date: 2022/6/3 11:26
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int minLen = minSubArrayLen(7, nums);
        System.out.println(minLen);
    }

    //我对滑动窗口的理解思路
    //正确，但时间复杂度明显为O(n^3)，LeetCode超时了
    public static int minSubArrayLen1(int target, int[] nums) {
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
}
