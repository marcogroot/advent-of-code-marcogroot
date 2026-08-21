import java.util.*;
import java.nio.file.*;

public class Day03 {
    record Slope(Integer row_slope, Integer col_slope) {
    };

    private static Long dfs(List<String> input, Integer row, Integer col) {
        Integer new_col = col;
        if (row >= input.size())
            return 0L;
        if (new_col >= input.get(0).length())
            new_col = new_col % input.get(0).length();

        var curr = input.get(row).charAt(new_col);
        var treeCount = (curr == '#') ? 1L : 0L;
        return dfs(input, row + 1, new_col + 3) + treeCount;
    }

    private static Long dfsTwo(List<String> input, Integer row, Integer col, Integer rowIncrease,
            Integer columnIncrease) {
        Integer new_col = col;
        if (row >= input.size())
            return 0L;
        if (new_col >= input.get(0).length())
            new_col = new_col % input.get(0).length();

        var curr = input.get(row).charAt(new_col);
        var treeCount = (curr == '#') ? 1L : 0L;
        return dfsTwo(input, row + rowIncrease, new_col + columnIncrease, rowIncrease,
                columnIncrease) + treeCount;
    }

    public static Long part1(List<String> input) {
        return dfs(input, 0, 0);
    }

    public static Long part2(List<String> input) {


        var slopes = List.of(new Slope(1, 1), new Slope(1, 3), new Slope(1, 5), new Slope(1, 7),
                new Slope(2, 1));

        var treeList = slopes.stream()
                .map(slope -> dfsTwo(input, 0, 0, slope.row_slope(), slope.col_slope())).toList();


        var answer = 1L;

        for (var treeCount : treeList) {
            System.out.println("Tree count: " + treeCount);
            answer *= treeCount;
        }

        return answer;

    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day3.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
