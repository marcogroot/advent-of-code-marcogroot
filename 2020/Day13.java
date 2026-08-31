import java.util.*;
import java.nio.file.*;

public class Day13 {
    public static Long part1(List<String> input) {
        var t = Long.valueOf(input.get(0));
        List<Long> times = Arrays.asList(input.get(1).split(",")).stream().map(time -> {
            if (time.equals("x")) {
                return Long.valueOf(-1);
            }
            return Long.valueOf(time);
        }).toList();

        var minWait = Long.MAX_VALUE;
        var busId = Long.MAX_VALUE;

        for (Long time : times) {
            if (time != -1) {
                var repeats = (t / time) + 1;
                var closestTime = (repeats * time);
                var timeToWait = closestTime - t;
                // System.out.println("T " + time + " Repeat: " + repeats + " to get " +
                // closestTime);
                // System.out.println("Remaining " + timeToWait);
                if (timeToWait < minWait) {
                    minWait = timeToWait;
                    busId = time;
                }
            }
        }

        // System.out.println("id: " + busId + " wait: " + minWait);
        return (long) busId * minWait;
    }

    public static Long part2(List<String> input) {
        List<Long> times = Arrays.asList(input.get(1).split(",")).stream().map(time -> {
            if (time.equals("x")) {
                return Long.valueOf(-1);
            }
            return Long.valueOf(time);
        }).toList();


        // var t = 1068781;
        // for (int i = 0; i < times.size(); i++) {
        // var time = times.get(i);
        // if (time != -1) {
        // var repeats = (t / time) + 1;
        // var closestTime = (repeats * time);
        // var timeToWait = closestTime - t;
        // if (timeToWait == time) {
        // timeToWait = 0;
        // }

        // System.out.println("Bus " + time + " wait:i " + timeToWait + ":" + i);

        // if (timeToWait != i) {
        // System.out.println(i);
        // break;
        // }
        // }
        // }

        long t = 0;
        long step = 1;
        var previousBest = 0;
        var count = 0;
        while (true) {
            System.out.println(step);
            count++;
            if (count % 10000000000L == 0) {
                System.out.println("At t: " + t);
            }
            var ret = true;
            // System.out.println(t);
            for (int i = 0; i < times.size(); i++) {
                var time = times.get(i);
                if (time != -1) {
                    var repeats = (t / time) + 1;
                    var closestTime = (repeats * time);
                    var timeToWait = closestTime - t;
                    if (timeToWait == time) {
                        timeToWait = 0;
                    }

                    if (timeToWait != i) {
                        // System.out.println("Is " + i + " was " + previousBest);
                        if (i > previousBest) {
                            for (int j = previousBest; j < i; j++) {
                                var c = times.get(j);
                                if (c != -1) {
                                    step *= c;
                                }
                            }
                            // System.out.println("t: " + t + " step: " + step);
                            previousBest = i;
                        }
                        ret = false;
                        break;
                    }
                }
            }
            if (ret)
                return t;
            t += step;
            // if (t >= 150L || t < 0) {
            // return -10000L;
            // }

        }
    }



    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day13.txt")).stream().filter(s -> !s.isBlank())
                .toList();
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
