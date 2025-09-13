package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LC1733_MinimumNumberOfPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<Integer> cantCommunicate = new ArrayList<>();

        for (int i = 0; i < friendships.length; i++) {
            int[] aLanguages = languages[friendships[i][0] - 1];
            int[] bLanguages = languages[friendships[i][1] - 1];
            boolean canCommunicate = false;
            for (int aLanguage : aLanguages) {
                for (int bLanguage : bLanguages) {
                    if (aLanguage == bLanguage) {
                        canCommunicate = true;
                        break;
                    }
                }
            }
            if (!canCommunicate) {
                cantCommunicate.add(friendships[i][0]);
                cantCommunicate.add(friendships[i][1]);
            }
        }

        cantCommunicate = cantCommunicate.stream().distinct().collect(Collectors.toList());

        if (cantCommunicate.isEmpty()) {
            return 0;
        }

        // 统计这些人会的语言
        List<Integer> allLangList = cantCommunicate.stream().map(id -> languages[id - 1])
                .flatMap(langArr -> {
                    List<Integer> langList = new ArrayList<>();
                    for (int lang : langArr) {
                        langList.add(lang);
                    }
                    return langList.stream();
                })
                .collect(Collectors.toList());


        // 给cantCommunicate分组求出某种语言会的最多的种类
        Map<Integer, Long> langCount = allLangList.stream().collect(Collectors.groupingBy(Integer::valueOf, Collectors.counting()));
        Integer mostLang = langCount.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        int result = cantCommunicate.size() - langCount.get(mostLang).intValue();
        return result;
    }

    public static void main(String[] args) {
        LC1733_MinimumNumberOfPeopleToTeach solution = new LC1733_MinimumNumberOfPeopleToTeach();
        int n = 11;
        int[][] languages = {{3,11,5,10,1,4,9,7,2,8,6},{5,10,6,4,8,7},{6,11,7,9},{11,10,4},{6,2,8,4,3},{9,2,8,4,6,1,5,7,3,10},{7,5,11,1,3,4},{3,4,11,10,6,2,1,7,5,8,9},{8,6,10,2,3,1,11,5},{5,11,6,4,2}};
        int[][] friendships = {{7,9},{3,7},{3,4},{2,9},{1,8},{5,9},{8,9},{6,9},{3,5},{4,5},{4,9},{3,6},{1,7},{1,3},{2,8},{2,6},{5,7},{4,6},{5,8},{5,6},{2,7},{4,8},{3,8},{6,8},{2,5},{1,4},{1,9},{1,6},{6,7}};
        int result = solution.minimumTeachings(n, languages, friendships);
        System.out.println(result);
    }
}
