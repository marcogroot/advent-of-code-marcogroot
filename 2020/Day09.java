import java.util.*;
import java.nio.file.*;

public class Day09 {
    public static Long part1(List<String> input) {
        // var startLength = 25;
        // Queue<Long> previous = new LinkedList<>();
        // for (int i = 0; i < startLength; i++) {
        // previous.add(Long.valueOf(input.get(i)));
        // }

        // var nums = input.stream().map(line -> Long.valueOf(line)).toList();
        // var subList = new ArrayList<>(nums.subList(startLength, nums.size()));

        // for (Long num : subList) {
        // // for (var a : previous) {
        // // System.out.print(a + " ");
        // // }
        // // System.out.print("\n");
        // var can = false;
        // for (var a : previous) {
        // for (var b : previous) {
        // if (a != b) {
        // Long c = a + b;
        // if (c.equals(num)) {
        // can = true;
        // break;
        // }
        // }
        // }
        // }
        // previous.poll();
        // previous.add(num);
        // if (!can) {


        // return (long) num;
        // }
        // }

        return -1L;
    }

    public static Long part2(List<String> input) {
        var nums = input.stream().map(line -> Long.valueOf(line)).toList();
        Long goal = 15690279L;

        for (int i = 0; i < nums.size(); i++) {
            Long sum = 0L;
            Long min = Long.MIN_VALUE;
            Long max = Long.MAX_VALUE;

            int j = i;
            while (sum < goal) {
                var curr = nums.get(j);
                sum += curr;
                min = Math.max(min, curr);
                max = Math.min(max, curr);
                if (sum.equals(goal)) {
                    return max + min;
                }
                j++;
            }
            if (sum == goal) {
                return max + min;
            }
        }


        return -1L;
    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day9.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
