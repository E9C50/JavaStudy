package leetcode;

public class LC3541_FindMostFrequentVowelAndConsonant {
    public int maxFreqSum(String s) {
        int[] vowelCount = new int[26];
        int[] consonantCount = new int[26];
        String vowels = "aeiou";
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                vowelCount[c - 'a']++;
            } else {
                consonantCount[c - 'a']++;
            }
        }
        int maxVowel = 0;
        for (int count : vowelCount) {
            maxVowel = Math.max(maxVowel, count);
        }
        int maxConsonant = 0;
        for (int count : consonantCount) {
            maxConsonant = Math.max(maxConsonant, count);
        }
        return maxVowel + maxConsonant;
    }

    public static void main(String[] args) {
        LC3541_FindMostFrequentVowelAndConsonant lc3541 = new LC3541_FindMostFrequentVowelAndConsonant();
        System.out.println(lc3541.maxFreqSum("successes"));
    }
}
