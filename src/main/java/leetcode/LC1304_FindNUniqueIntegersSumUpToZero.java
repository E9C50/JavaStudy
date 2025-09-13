package leetcode;

public class LC1304_FindNUniqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        int index = 0;
        int[] result = new int[n];
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = i;
            result[index++] = -i;
        }
        if (n % 2 == 1) {
            result[index] = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        new LC1304_FindNUniqueIntegersSumUpToZero().sumZero(5);
    }
}
