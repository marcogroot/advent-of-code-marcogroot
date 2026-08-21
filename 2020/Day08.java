import java.util.*;
import java.nio.file.*;

public class Day08 {
    record Instruction(String kind, Integer amount) {

    }

    private static ArrayList<Instruction> parseInput(List<String> input) {
        return new ArrayList<>(input.stream().map(line -> {
            var parts = line.split(" ");
            var instruction = parts[0];
            var sign = parts[1].charAt(0);
            var magnitude = Integer.valueOf(parts[1].substring(1));
            if (sign == '-') {
                magnitude *= -1;
            }

            return new Instruction(instruction, magnitude);
        }).toList());
    }

    private static Long solve(

            List<Instruction> instructions) {

        var seen = new HashSet<Integer>();
        var i = 0;

        Long sum = 0L;
        while (true) {
            if (i == instructions.size()) {
                System.out.println(sum);
                return -1L;
            }
            if (seen.contains(i))
                return sum;
            seen.add(i);
            var instruction = instructions.get(i);
            var kind = instruction.kind();

            if (kind.equals("nop")) {
                i++;
            } else if (kind.equals("acc")) {
                i++;
                sum += instruction.amount();
            } else {
                i += instruction.amount();
            }
        }
    }

    public static Long part1(List<String> input) {
        var instructions = parseInput(input);
        return solve(instructions);
    }

    public static Long part2(List<String> input) {
        var instructions = parseInput(input);

        for (int i = 0; i < instructions.size(); i++) {
            var curr = instructions.get(i);
            if (curr.kind().equals("nop") || curr.kind().equals("jmp")) {
                var instruct = curr.kind().equals("nop") ? "jmp" : "nop";
                var newInstruction = new Instruction(instruct, curr.amount());
                instructions.set(i, newInstruction);
                solve(instructions);
                instructions.set(i, curr);
            }
        }


        return -1L;

    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day8.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
