package leetcode;

public class LC0055_JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }

        int maxIdx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && i >= maxIdx) {
                return false;
            }
            if (maxIdx < nums[i] + i) {
                maxIdx = nums[i] + i;
            }
        }
        System.out.println(maxIdx);
        return maxIdx >= nums.length - 1;
    }

    public static void main(String[] args) {
        LC0055_JumpGame solution = new LC0055_JumpGame();
        int[] nums = {2,0,0};
        boolean result = solution.canJump(nums);
        System.out.println(result);
    }
}