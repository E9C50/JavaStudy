package leetcode;

/**
 * 给你两个整数：num1 和 num2 。
 * 在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2^i + num2 。
 * 请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。
 * 如果无法使 num1 等于 0 ，返回 -1 。
 */
public class LC2749_MinimumOperationsToMakeTheIntegerZero {
    public int makeTheIntegerZero(int num1, int num2) {
        int count = 1;
        while (true) {
            int x = num1 - (num2 * count);
            if (x < count) {
                return -1;
            }
            if (count >= Long.bitCount(x)) {
                return count;
            }
            count++;
        }
    }

    public static void main(String[] args) {
        LC2749_MinimumOperationsToMakeTheIntegerZero solution = new LC2749_MinimumOperationsToMakeTheIntegerZero();
        int num1 = 3, num2 = -2;
        int result = solution.makeTheIntegerZero(num1, num2);
        System.out.println(result);
    }
}
