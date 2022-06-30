package simple;

import java.util.Arrays;

/**
 * @Author: Sihang Xie
 * @Description: 455. 分发饼干 - 力扣（LeetCode）
 * @Date: 2022/6/7 10:20
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0455 {
    public static void main(String[] args) {
        int[] g = {1, 2, 7, 10};//小朋友胃口
        int[] s = {1, 3, 5};//饼干尺寸
        int num = findContentChildren(g, s);
        System.out.println(num);
    }

    //思路一：先喂饱大胃口
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);//小孩胃口数组排序
        Arrays.sort(s);//饼干尺寸数组排序
        int result = 0;//记录已满足的小孩数
        int index = s.length - 1;//控制饼干循环
        //从后往前遍历小孩数组
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && g[i] <= s[index]) {
                result++;//满足人数加一
                index--;//消耗一块饼干，饼干指针前移
            }
        }
        return result;
    }
}
