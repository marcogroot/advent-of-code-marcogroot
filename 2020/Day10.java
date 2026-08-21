import java.util.*;
import java.nio.file.*;

public class Day10 {
    public static Long part1(List<String> input) {
        var nums = new ArrayList<Integer>();
        nums.add(0);
        var max = Integer.MIN_VALUE;
        for (String line : input) {
            var value = Integer.valueOf(line);
            max = Math.max(max, value);
            nums.add(value);
        }
        nums.add(max + 3);

        nums.sort(null);

        Long oneDiff = 0L;
        Long threeDiff = 0L;

        for (int i = 1; i < nums.size(); i++) {
            Integer curr = nums.get(i);
            Integer prev = nums.get(i - 1);
            Integer diff = -1 * (prev - curr);
            // System.out.println(curr + " " + prev + " " + diff);
            if (diff == Integer.valueOf(1))
                oneDiff++;

            if (diff == Integer.valueOf(3))
                threeDiff++;
        }


        // System.out.println("1: " + oneDiff + " 3: " + threeDiff);
        return oneDiff * threeDiff;
    }

    private static Long dfs(Integer curr, HashSet<Integer> nums, Integer goal,
            HashMap<Integer, Long> cache) {

        // System.out.println(curr);

        if (curr.equals(goal)) {

            System.out.println(curr);

            return 1L;
        }

        if (cache.containsKey(curr)) {
            return cache.get(curr);
        }

        var res = 0L;
        for (int i = 1; i <= 3; i++) {
            var next = curr + i;
            if (nums.contains(next)) {
                res += dfs(next, nums, goal, cache);
            }
        }

        cache.put(curr, res);

        return res;
    }

    public static Long part2(List<String> input) {
        var nums = new HashSet<Integer>();
        var cache = new HashMap<Integer, Long>();
        var max = Integer.MIN_VALUE;
        for (String line : input) {
            var value = Integer.valueOf(line);
            max = Math.max(max, value);
            nums.add(value);
        }
        nums.add(max + 3);
        System.out.println("Looking for MAX: " + (max + 3));

        return dfs(0, nums, max, cache);
    }

    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day10.txt")).stream().filter(s -> !s.isBlank())
                .toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
