import java.util.*;
import java.nio.file.*;

public class Day01 {
    public static Integer part1(List<Integer> nums) {
        var seen = new HashSet<Integer>();
        for (var curr : nums) {
            Integer need = 2020 - curr;
            if (seen.contains(need)) {
                return curr * need;
            }
            seen.add(curr);
        }
        return -1;
    }

    public static Integer part2(List<Integer> nums) {
        var seen = new HashSet<Integer>(nums);

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                var a = nums.get(i);
                var b = nums.get(j);
                var need = 2020 - a - b;
                if (seen.contains(need)) {
                    return a * b * need;
                }
            }
        }


        return -1;
    }

    public static void main(String[] args) throws Exception {
        var nums = Files.readAllLines(Path.of("day1.txt")).stream().filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList();

        System.out.println(part1(nums));
        System.out.println(part2(nums));
    }
}
