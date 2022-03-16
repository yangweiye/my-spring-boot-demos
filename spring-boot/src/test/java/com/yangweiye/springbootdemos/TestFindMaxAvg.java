package com.yangweiye.springbootdemos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFindMaxAvg {
    @Test
    public void test() {
        List<Integer> list = Stream.of(12, -3, -4, 10, 11).collect(Collectors.toList());

        System.out.println(findMaxAverage(list, 3));
    }

    /*
        使用二分查找猜测结果 因为有容错
        什么时候修改边界？
            如果有符合条件的序列 大于 猜测的平均数 提高下界，反之降低上界。
    */
    public Double findMaxAverage(List<Integer> list, Integer k) {
        Double l = -10000.0, r = 10000.0, avg = 0.0, ans = 0.0;
        //题目容错为小于 10的负5次方 所以可以使用二分查找猜测 猜测的是结果
        //1e - 6 = 0.000001
        while (r - l > 1e-6) {
            avg = l + (r - l) / 2;
            if (isok(list, avg, k)) {
                l = avg;
            } else {
                r = avg;
            }
            ans = avg;
        }
        return ans;
    }

    // 这个方法检车是否有符合调用的序列大于 avg 这个方法我觉得是核心 很绕 多看看吧
    public Boolean isok(List<Integer> nums, Double avg, int k) {
        Double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += nums.get(i) - avg;
        }

        //sum 大于0说明 这个序列 的平均数大于 当前avg
        if (sum >= 0)
            return true;

        //这里计算前面应该舍弃额部门
        //我觉这是是最难算的 不太好理解
        Double preSum = 0.0, min = 0.0;

        //当 k 长度的序列不满足时 尝试扩大序列。
        //在尝试扩大序列时 还有尝试能否抛弃前面的负面（降低平均值的）部分
        for (int i = k; i < nums.size(); i++) {
            sum += nums.get(i) - avg;
            //累加前面所有可以抛弃的部分 具体抛不抛弃有下面这条语句决定。
            preSum += nums.get(i - k) - avg;
            //如果 min 小于 0 就抛弃这部分 sum 减去一个负数 平均值就变大了
            //因为要求必须连续 算的时候只有整体更小才会被抛弃
            //-3：和为-3 抛弃。 -3，4：和为 1 -3抛弃4保留。-3，4，-5：和为-4 整体抛弃
            //这就是 最难理解的点 希望自己以后能记住吧
            min = Math.min(min, preSum);
            if (sum - min > 0)
                return true;
        }

        return false;
    }
}
