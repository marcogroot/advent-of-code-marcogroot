use std::collections::HashMap;
use std::ops::Range;

#[derive(Debug)]
enum Inst {
    OFF,
    ON,
    TOGGLE,
}

#[derive(Debug)]
struct Instruction {
    inst: Inst,
    rows: Range<u64>,
    cols: Range<u64>,
}
fn main() {
    let input = include_str!("./input.txt");

    let instructions: Vec<Instruction> = input
        .lines()
        .map(|line| {
            let parts: Vec<&str> = line.split(" ").collect();

            if parts[0] == "toggle" {
                let coords1: Vec<u64> = parts[1]
                    .split(",")
                    .map(|s| s.parse::<u64>().unwrap())
                    .collect();
                let coords2: Vec<u64> = parts[3]
                    .split(",")
                    .map(|s| s.parse::<u64>().unwrap())
                    .collect();

                return Instruction {
                    inst: Inst::TOGGLE,
                    rows: coords1[0]..coords2[0] + 1,
                    cols: coords1[1]..coords2[1] + 1,
                };
            } else {
                let on = parts[1] == "on";
                let inst = if on { Inst::ON } else { Inst::OFF };

                let coords1: Vec<u64> = parts[2]
                    .split(",")
                    .map(|s| s.parse::<u64>().unwrap())
                    .collect();
                let coords2: Vec<u64> = parts[4]
                    .split(",")
                    .map(|s| s.parse::<u64>().unwrap())
                    .collect();

                return Instruction {
                    inst: inst,
                    rows: coords1[0]..coords2[0] + 1,
                    cols: coords1[1]..coords2[1] + 1,
                };
            }
        })
        .collect();

    instructions.iter().for_each(|inst| println!("{:?}", inst));

    let mut lights: HashMap<(u64, u64), bool> = HashMap::new();

    instructions.iter().for_each(|instruction| {
        instruction.rows.clone().for_each(|row| {
            instruction
                .cols
                .clone()
                .for_each(|col| match instruction.inst {
                    Inst::TOGGLE => {
                        lights
                            .entry((row, col))
                            .and_modify(|v| *v = !*v)
                            .or_insert(true);
                    }
                    Inst::OFF => {
                        lights.insert((row, col), false);
                    }
                    Inst::ON => {
                        lights.insert((row, col), true);
                    }
                });
        });
    });

    println!("{}", lights.values().filter(|v| **v).count());
}
