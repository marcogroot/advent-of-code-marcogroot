import java.util.*;
import java.nio.file.*;

public class Day05 {
    private static Integer getPos(String input) {
        var l = 0;
        Integer r = (int) Math.pow(2.0, input.length()) - 1;

        // System.out.println(l + " " + r);
        for (int i = 0; i < input.length() - 1; i++) {
            var curr = input.charAt(i);
            var mid = (l + r) / 2;
            if (curr == 'F' || curr == 'L') {
                r = mid;
            } else {
                l = mid + 1;
            }
            // System.out.println(l + " " + r);
        }

        var finalChar = input.charAt(input.length() - 1);
        if (finalChar == 'F' || finalChar == 'L') {
            return l;
        } else {
            return r;
        }
    }

    public static Integer part1(List<String> input) {
        var res = input.stream().mapToInt(line -> {
            var rowString = line.substring(0, 7);
            var row = getPos(rowString);
            // out.println(rowString + " " + row);
            var colString = line.substring(7);
            var col = getPos(colString);
            // System.out.println(colString + " " + col);

            return (row * 8) + col;
        }).max().orElse(0);

        return res;
    }

    public static Integer part2(List<String> input) {
        var seatNums = input.stream().map(line -> {
            var rowString = line.substring(0, 7);
            var row = getPos(rowString);
            // out.println(rowString + " " + row);
            var colString = line.substring(7);
            var col = getPos(colString);
            // System.out.println(colString + " " + col);

            return (row * 8) + col;
        }).sorted().toList();

        for (int i = 0; i < seatNums.size() - 2; i++) {
            var behind = seatNums.get(i);
            var infront = seatNums.get(i + 1);
            if ((infront - behind) == 2)
                return behind + 1;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day5.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
