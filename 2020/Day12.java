import java.util.*;
import java.nio.file.*;

public class Day12 {
    enum Dir {
        N, W, S, E, L, R, F
    }

    record Instruction(Dir dir, Integer magnitude) {
    }

    record DirDiff(Integer rDiff, Integer cDiff) {

    }

    public static Long part1(List<String> input) {
        var dirs = List.of(new DirDiff(0, 1), // E
                new DirDiff(1, 0), // S
                new DirDiff(-1, 0), // W
                new DirDiff(0, -1) // N
        );
        var directionIndex = 0;

        var directionMap = Map.of(Dir.N, 3, Dir.W, 2, Dir.S, 1, Dir.E, 0);

        var instructions = input.stream().map(line -> {
            var d = Dir.valueOf(line.substring(0, 1));
            var mag = Integer.valueOf(line.substring(1));

            return new Instruction(d, mag);
        }).toList();

        var r = 0;
        var c = 0;

        for (var instruction : instructions) {
            var dir = instruction.dir();
            var mag = instruction.magnitude();
            switch (dir) {
                case N, E, W, S -> {
                    var direction = dirs.get(directionMap.get(dir));
                    r += direction.rDiff() * mag;
                    c += direction.cDiff() * mag;
                }
                case L, R -> {
                    var turns = mag / 90;

                    if (dir.equals(Dir.L)) {
                        directionIndex -= turns;
                    } else {
                        directionIndex += turns;
                    }

                    if (directionIndex < 0) {
                        directionIndex += 4;
                    }
                    if (directionIndex >= 4) {
                        directionIndex -= 4;
                    }
                }
                case F -> {
                    var direction = dirs.get(directionIndex);
                    r += direction.rDiff() * mag;
                    c += direction.cDiff() * mag;
                }
            }
        }

        return (long) (Math.abs(r) + Math.abs(c));
    }

    public static Long part2(List<String> input) {
        var dirs = List.of(new DirDiff(0, 1), // E
                new DirDiff(1, 0), // S
                new DirDiff(-1, 0), // W
                new DirDiff(0, -1) // N
        );
        var directionIndex = 0;

        var directionMap = Map.of(Dir.N, 3, Dir.W, 2, Dir.S, 1, Dir.E, 0);

        var instructions = input.stream().map(line -> {
            var d = Dir.valueOf(line.substring(0, 1));
            var mag = Integer.valueOf(line.substring(1));

            return new Instruction(d, mag);
        }).toList();

        var r = 0;
        var c = 0;

        for (var instruction : instructions) {
            var dir = instruction.dir();
            var mag = instruction.magnitude();
            switch (dir) {
                case N, E, W, S -> {
                    var direction = dirs.get(directionMap.get(dir));
                    r += direction.rDiff() * mag;
                    c += direction.cDiff() * mag;
                }
                case L, R -> {
                    var turns = mag / 90;

                    if (dir.equals(Dir.L)) {
                        directionIndex -= turns;
                    } else {
                        directionIndex += turns;
                    }

                    if (directionIndex < 0) {
                        directionIndex += 4;
                    }
                    if (directionIndex >= 4) {
                        directionIndex -= 4;
                    }
                }
                case F -> {
                    var direction = dirs.get(directionIndex);
                    r += direction.rDiff() * mag;
                    c += direction.cDiff() * mag;
                }
            }
        }

        return (long) (Math.abs(r) + Math.abs(c));
    }

    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day12.txt")).stream().filter(s -> !s.isBlank())
                .toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
