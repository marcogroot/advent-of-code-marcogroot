use std::collections::HashSet;

fn main() {
    let input = include_str!("./input.txt");
    let output = solve(input);
    dbg!(output);
}

struct Instruction {
    dr: i64,
    dc: i64,
    magnitude: i64,
}

fn parse_instructions(line: &str) -> Vec<Instruction> {
    line.trim()
        .split(",")
        .map(|s| {
            let (d, mag) = s.split_at(1);
            let magnitude = mag.parse::<i64>().unwrap();
            match d.chars().next().unwrap() {
                'U' => Instruction {
                    dr: 1,
                    dc: 0,
                    magnitude,
                },
                'D' => Instruction {
                    dr: -1,
                    dc: 0,
                    magnitude,
                },
                'L' => Instruction {
                    dr: 0,
                    dc: -1,
                    magnitude,
                },
                _ => Instruction {
                    dr: 0,
                    dc: 1,
                    magnitude,
                },
            }
        })
        .collect()
}

fn walk(instructions: Vec<Instruction>) -> HashSet<(i64, i64)> {
    let mut set = HashSet::new();
    let mut r = 0;
    let mut c = 0;

    instructions.into_iter().for_each(|instruction| {
        for _ in 0..instruction.magnitude {
            r += instruction.dr;
            c += instruction.dc;
            set.insert((r, c));
        }
    });

    return set;
}

fn solve(input: &str) -> i64 {
    let mut it = input.lines().filter(|l| !l.trim().is_empty());
    let instructions1 = parse_instructions(it.next().unwrap());
    let instructions2 = parse_instructions(it.next().unwrap());

    let visited1 = walk(instructions1);
    let visited2 = walk(instructions2);

    visited1
        .into_iter()
        .filter(|coord| visited2.contains(coord))
        .map(|(x, y)| x.abs() + y.abs())
        .min()
        .unwrap()
}
