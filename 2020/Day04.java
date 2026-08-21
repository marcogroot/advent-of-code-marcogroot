import java.util.*;
import java.nio.file.*;

public class Day04 {
    static List<HashMap<String, String>> parseInput(List<String> input) {
        var groupedInputs = new ArrayList<String>();

        var base = "";

        for (String line : input) {
            if (line.isBlank()) {
                groupedInputs.add(base);
                base = "";
            } else {
                base += " " + line;
            }
        }
        groupedInputs.add(base);

        return groupedInputs.stream().map(groupedInput -> {
            var parts = groupedInput.split(" ");
            var abc = new HashMap<String, String>();

            for (String part : parts) {
                var field_w_value = part.split(":");
                if (field_w_value.length >= 2) {
                    var field = field_w_value[0];
                    var value = field_w_value[1];
                    abc.put(field, value);
                }
            }

            return abc;
        }).toList();
    }

    public static Integer part1(List<String> input) {
        var identities = parseInput(input);

        var validCount = identities.stream().mapToInt(id_fields -> {
            var valid = id_fields.containsKey("byr") && id_fields.containsKey("iyr")
                    && id_fields.containsKey("eyr") && id_fields.containsKey("hgt")
                    && id_fields.containsKey("hcl") && id_fields.containsKey("ecl")
                    && id_fields.containsKey("pid");
            return valid ? 1 : 0;
        }).sum();

        return validCount;
    }

    private static Boolean validYear(String year, Integer start, Integer end) {
        if (year == null)
            return false;
        try {
            var value = Integer.valueOf(year);
            if (value < start || value > end) {
                return false;

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Boolean validHeight(String height) {
        if (height == null)
            return false;
        try {
            var unit = height.substring(height.length() - 2).trim();
            var length = Integer.valueOf(height.substring(0, height.length() - 2));

            if (unit.equals("in")) {
                return length >= 59 && length <= 76;
            } else if (unit.equals("cm")) {

                return length >= 150 && length <= 193;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static Boolean validHairColour(String colour) {
        if (colour == null)
            return false;
        try {
            if (colour.length() != 7) {
                return false;
            }
            if (colour.charAt(0) != '#') {
                return false;
            }
            var a = colour.substring(1);
            return a.chars().allMatch(c -> {
                return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f');
            });
        } catch (Exception e) {
            return false;
        }
    }


    private static Boolean validEyeColour(String colour) {
        if (colour == null)
            return false;
        try {
            var validColours = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            return validColours.contains(colour);
        } catch (Exception e) {
            return false;
        }
    }

    private static Boolean validPid(String pid) {
        if (pid == null)
            return false;
        try {
            if (pid.length() != 9)
                return false;
            var a = Integer.valueOf(pid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Integer part2(List<String> input) {
        var identities = parseInput(input);
        System.out.println(identities.size());

        var validCount = identities.stream().mapToInt(id_fields -> {
            var byr = id_fields.get("byr");
            var iyr = id_fields.get("iyr");
            var eyr = id_fields.get("eyr");
            var hgt = id_fields.get("hgt");
            var hcl = id_fields.get("hcl");
            var ecl = id_fields.get("ecl");
            var pid = id_fields.get("pid");



            var validByr = validYear(byr, 1920, 2002);
            System.out.println("validByr " + byr + " " + validByr);
            var validIyr = validYear(iyr, 2010, 2020);
            System.out.println("validIyr " + iyr + " " + validIyr);
            var validEyr = validYear(eyr, 2020, 2030);
            System.out.println("validEyr " + eyr + " " + validEyr);
            var validHgt = validHeight(hgt);
            System.out.println("validHgt " + hgt + " " + validHgt);
            var validHcl = validHairColour(hcl);
            System.out.println("validHcl " + hcl + " " + validHcl);
            var validEcl = validEyeColour(ecl);
            System.out.println("validEcl " + ecl + " " + validEcl);
            var validPid = validPid(pid);
            System.out.println("validPid " + pid + " " + validPid);


            var valid = validByr && validIyr && validEyr && validHgt && validHcl && validEcl
                    && validPid;
            System.out.println("------- IS VALID? : " + valid);

            return valid ? 1 : 0;
        }).sum();
        return validCount;
    }

    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("day4.txt"));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
