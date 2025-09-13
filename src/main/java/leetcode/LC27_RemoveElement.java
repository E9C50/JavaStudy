package leetcode;

public class LC27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LC27_RemoveElement solution = new LC27_RemoveElement();
        int[] nums = {1};
        int val = 1;
        int result = solution.removeElement(nums, val);
        System.out.println(result);
    }
}
