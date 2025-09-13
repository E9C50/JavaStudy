package leetcode;

public class LC0005_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String longest = String.valueOf(s.charAt(0));

        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                String palindrome = expandAroundCenter(s, i, i + 1);
                if (palindrome.length() > longest.length()) {
                    longest = palindrome;
                }
            }
            if (i - 1 >= 0 && i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) {
                String palindrome = expandAroundCenter(s, i - 1, i + 1);
                if (palindrome.length() > longest.length()) {
                    longest = palindrome;
                }
            }
        }
        return longest;
    }

    private String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        LC0005_LongestPalindromicSubstring solution = new LC0005_LongestPalindromicSubstring();
        String s = "babadcbbd";
        String result = solution.longestPalindrome(s);
        System.out.println(result);
    }
}
