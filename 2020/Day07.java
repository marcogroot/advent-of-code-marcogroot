import java.util.*;
import java.nio.file.*;


public class Day07 {
    record BagCount(String name, Integer count) {
    }

    private static HashMap<String, List<BagCount>> parseBagMappings(List<String> input) {
        var mappings = new HashMap<String, List<BagCount>>();

        for (String line : input) {
            var components = line.split(" ");
            var bagName = components[0] + "_" + components[1];
            var inside = new ArrayList<BagCount>();

            var index = 4;
            Integer loops = 1;
            for (Character c : line.toCharArray()) {
                if (c == ',') {
                    loops++;
                }
            }
            if (!line.contains("no other bags")) {
                // 0 1 2 3
                // l r b c
                for (int i = 0; i < loops; i++) {
                    var count = Integer.parseInt(components[index]);
                    var n1 = components[index + 1];
                    var n2 = components[index + 2];
                    var name = n1 + "_" + n2;
                    var bagM = new BagCount(name, count);
                    inside.add(bagM);
                    index += 4;
                }
            }

            mappings.put(bagName, inside);
        }

        return mappings;
    }

    private static Boolean dfs(String name, HashMap<String, List<BagCount>> bagMappings,
            HashMap<String, Boolean> canContainMap) {

        if (canContainMap.containsKey(name)) {
            return canContainMap.get(name);
        }

        if (name.equals("shiny_gold")) {
            return true;
        }

        var mappings = bagMappings.get(name);
        var canReach = false;
        for (var bagCount : mappings) {
            var bag = bagCount.name();
            canReach = canReach || dfs(bag, bagMappings, canContainMap);
        }

        canContainMap.put(name, canReach);
        return canReach;
    }

    public static Long part1(List<String> input) {
        var bagMappings = parseBagMappings(input);
        var canContainShinyGoldBag = new HashMap<String, Boolean>();

        // for (var bm : bagMappings.entrySet()) {
        // System.out.println(bm.getKey() + ":");
        // for (var x : bm.getValue()) {
        // System.out.print(x + " ");
        // }
        // System.out.println("\n");
        // }

        for (var bag : bagMappings.keySet()) {
            dfs(bag, bagMappings, canContainShinyGoldBag);
        }

        // for (var x : canContainShinyGoldBag.entrySet()) {
        // System.out.println(x.getKey() + ":" + x.getValue());
        // }

        return (long) canContainShinyGoldBag.values().stream().filter(it -> it).toList().size();
    }

    private static Long dfsTwo(String name, HashMap<String, List<BagCount>> bagMappings,
            HashMap<String, Long> cache) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }

        var mappings = bagMappings.get(name);
        Long total = 0L;
        for (var bagCount : mappings) {
            total += bagCount.count();
            total += bagCount.count() * dfsTwo(bagCount.name(), bagMappings, cache);
        }

        cache.put(name, total);

        return total;
    }

    public static Long part2(List<String> input) {
        var mappings = parseBagMappings(input);
        // for (var x : mappings.entrySet()) {
        // System.out.println(x.getKey());

        // }
        return dfsTwo("shiny_gold", mappings, new HashMap<>());
    }

    public static void main(String[] args) throws Exception {
        var input =
                Files.readAllLines(Path.of("day7.txt")).stream().filter(s -> !s.isBlank()).toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
