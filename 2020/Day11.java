import java.util.*;
import java.nio.file.*;


public class Day11 {
    record Pair(Integer r, Integer c) {

    }

    private static Boolean sameAsPrevious(List<List<Character>> previous,
            List<List<Character>> curr) {

        if (previous.size() != curr.size())
            return false;

        for (int i = 0; i < previous.size(); i++) {
            for (int j = 0; j < previous.get(0).size(); j++) {
                if (previous.get(i).get(j) != curr.get(i).get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Pair> dirs = List.of(new Pair(-1, -1), new Pair(-1, 0), new Pair(-1, 1),
            new Pair(0, 1), new Pair(1, 1), new Pair(1, 0), new Pair(1, -1), new Pair(0, -1));


    private static Integer getOccupiedCount(Integer r, Integer c, ArrayList<List<Character>> curr) {
        var count = 0;
        for (var dir : dirs) {
            var new_r = r + dir.r();
            var new_c = c + dir.c();

            if (new_r >= 0 && new_r < curr.size() && new_c >= 0 && new_c < curr.get(0).size()) {
                if (curr.get(new_r).get(new_c).equals('#')) {
                    count++;
                }
            }
        }

        return count;
    }

    private static Integer getOccupiedCount2(Integer r, Integer c,
            ArrayList<List<Character>> curr) {
        var count = 0;
        // System.out.println("STARTING FROM " + r + " : " + c);
        for (var dir : dirs) {
            int nr = r + dir.r();
            int nc = c + dir.c();

            while (nr >= 0 && nr < curr.size() && nc >= 0 && nc < curr.get(0).size()) {
                var currChar = curr.get(nr).get(nc);
                // System.out.println(nr + " " + nc);
                if (currChar.equals('#')) {
                    count += 1;
                    break;
                }
                if (currChar.equals('L')) {
                    break;
                }
                nr += dir.r();
                nc += dir.c();
            }
        }
        return count;
    }

    private static List<List<Pair>> getSeatChanges(ArrayList<List<Character>> curr,
            Integer toleranceLimit) {
        var becomeOccupied = new ArrayList<Pair>();
        var becomeVacant = new ArrayList<Pair>();
        for (int r = 0; r < curr.size(); r++) {
            for (int c = 0; c < curr.get(0).size(); c++) {
                var seat = curr.get(r).get(c);
                var occupiedCount = getOccupiedCount(r, c, curr);
                if (toleranceLimit == 5) {
                    occupiedCount = getOccupiedCount2(r, c, curr);
                }
                if (seat.equals('L')) {
                    if (occupiedCount.equals(0)) {
                        becomeOccupied.add(new Pair(r, c));
                    }
                }
                if (seat.equals('#')) {
                    // System.out.println("r: " + r + " c: " + c + "count: " + occupiedCount);
                    if (occupiedCount.intValue() >= toleranceLimit) {
                        becomeVacant.add(new Pair(r, c));
                    }
                }
            }
        }
        // System.out.println(becomeOccupied.size() + " : " + becomeVacant.size());

        return List.of(becomeOccupied, becomeVacant);
    }

    public static Long part1(ArrayList<List<Character>> input) {
        var previous = new ArrayList<List<Character>>();
        var curr = input;

        // for (var row : curr) {
        // System.out.println(row);
        // }
        // System.out.println("-------STARTING---------");

        while (!sameAsPrevious(previous, curr)) {
            previous = curr;
            var seatChanges = getSeatChanges(curr, 4);
            var newGrid = new ArrayList<List<Character>>();
            for (var a : curr) {
                newGrid.add(new ArrayList<>(a));
            }
            var becomeOccupied = seatChanges.get(0);
            var becomeVacant = seatChanges.get(1);

            for (var seatChange : becomeOccupied) {
                newGrid.get(seatChange.r()).set(seatChange.c(), '#');
            }

            for (var seatChange : becomeVacant) {
                newGrid.get(seatChange.r()).set(seatChange.c(), 'L');
            }

            curr = newGrid;

            // for (var row : curr) {
            // System.out.println(row);
            // }

            // System.out.println("------------");
        }

        long count = 0;
        for (var row : curr) {
            for (var c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    public static Long part2(ArrayList<List<Character>> input) {
        var previous = new ArrayList<List<Character>>();
        var curr = input;

        // for (var row : curr) {
        // System.out.println(row);
        // }
        // System.out.println("-------STARTING---------");

        while (!sameAsPrevious(previous, curr)) {
            previous = curr;
            var seatChanges = getSeatChanges(curr, 5);
            var newGrid = new ArrayList<List<Character>>();
            for (var a : curr) {
                newGrid.add(new ArrayList<>(a));
            }
            var becomeOccupied = seatChanges.get(0);
            var becomeVacant = seatChanges.get(1);

            for (var seatChange : becomeOccupied) {
                newGrid.get(seatChange.r()).set(seatChange.c(), '#');
            }

            for (var seatChange : becomeVacant) {
                newGrid.get(seatChange.r()).set(seatChange.c(), 'L');
            }

            curr = newGrid;

            // for (var row : curr) {
            // System.out.println(row);
            // }

            // System.out.println("------------");
        }

        long count = 0;
        for (var row : curr) {
            for (var c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        var input = new ArrayList<>(Files.readAllLines(Path.of("day11.txt")).stream()
                .filter(s -> !s.isBlank()).map(s -> {
                    return s.chars().mapToObj(c -> (char) c).toList();
                }).toList());

        // System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
