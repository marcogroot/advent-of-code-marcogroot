import java.util.*;
import java.nio.file.*;

public class Day06 {
    public static Long part1(List<String> input) {
        var answerGroups = new ArrayList<Set<Character>>();
        var uniqueAnswers = new HashSet<Character>();
        for (String line : input) {
            if (line.isBlank()) {
                answerGroups.add(new HashSet<>(uniqueAnswers));
                uniqueAnswers.clear();
            } else {
                for (Character c : line.toCharArray()) {
                    uniqueAnswers.add(c);
                }
            }
        }
        answerGroups.add(new HashSet<>(uniqueAnswers));
        return (long) answerGroups.stream().mapToInt(group -> group.size()).sum();
    }

    public static Long part2(List<String> i) {
        var sum = 0;
        var answerCount = new HashMap<Character, Integer>();
        var input = new ArrayList<>(i);
        input.add("");
        Integer s = 0;
        for (String line : input) {
            if (line.isBlank()) {
                for (var a : answerCount.entrySet()) {
                    if (a.getValue() == s) {
                        System.out.println("Adding 1 for " + a.getKey());
                        sum++;
                    }
                }
                answerCount.clear();
                s = 0;
                System.out.println("NEXT WORD");
            } else {
                s++;
                for (Character c : line.toCharArray()) {
                    answerCount.put(c, answerCount.getOrDefault(c, 0) + 1);
                }
            }
        }
        return (long) sum;
    }

    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day6.txt")).stream().toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
