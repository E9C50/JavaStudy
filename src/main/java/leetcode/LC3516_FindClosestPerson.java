package leetcode;

public class LC3516_FindClosestPerson {
    public int findClosest(int x, int y, int z) {
        if (Math.abs(x - z) < Math.abs(y - z)) {
            return 1;
        } else if (Math.abs(x - z) > Math.abs(y - z)) {
            return 2;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        LC3516_FindClosestPerson solution = new LC3516_FindClosestPerson();
        int x = 1, y = 4, z = 3;
        int result = solution.findClosest(x, y, z);
        System.out.println(result);
    }
}
