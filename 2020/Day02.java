import java.util.*;
import java.nio.file.*;


public class Day02 {
    record PasswordSchema(Integer fewest, Integer most, Character letter, String password) {

        Boolean isValidP1() {
            var charOccurences = password.chars().filter(c -> c == letter).count();
            return charOccurences >= fewest() && charOccurences <= most;
        }

        Boolean isValidP2() {
            var first = false;
            if (fewest() - 1 >= 0) {
                first = password.charAt(fewest() - 1) == letter();
            }

            var second = false;
            if (most() - 1 >= 0) {
                second = password.charAt(most() - 1) == letter();
            }

            return first ^ second;
        }


    }

    private static List<PasswordSchema> parseInput(List<String> input) {
        var passwordSchemas = input.stream().map(it -> {
            var pieces = it.split(" ");
            var numRangeStrings = pieces[0].split("-");
            Integer start = Integer.valueOf(numRangeStrings[0]);
            Integer end = Integer.valueOf(numRangeStrings[1]);

            var letter = pieces[1].charAt(0);
            var password = pieces[2];

            return new PasswordSchema(start, end, letter, password);
        }).toList();

        return passwordSchemas;
    }

    public static Long part1(List<String> input) {
        var passwordSchemas = parseInput(input);
        return passwordSchemas.stream().map(password -> password.isValidP1()).filter(b -> b)
                .count();
    }

    public static Long part2(List<String> input) {
        var passwordSchemas = parseInput(input);
        return passwordSchemas.stream().map(password -> password.isValidP2()).filter(b -> b)
                .count();
    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day2.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
