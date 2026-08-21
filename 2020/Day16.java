import java.util.*;
import java.nio.file.*;

public class Day16 {
    public static Long part1(List<String> input) {
        return -1L;
    }

    public static Long part2(List<String> input) {
        return -1L;
    }

    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day16.txt")).stream()
            .filter(s -> !s.isBlank())
            .toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
