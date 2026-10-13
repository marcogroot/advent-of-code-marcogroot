use std::collections::HashMap;

fn main() {
    let input = include_str!("./input.txt");
    let mut wire_values: HashMap<&str, u16> = HashMap::new();
    wire_values.insert("b", 16076);

    fn resolve_value(vals: &HashMap<&str, u16>, val: &str) -> Option<u16> {
        return match val.parse::<u16>() {
            Ok(v) => Some(v),
            Err(_) => {
                return vals.get(val).copied();
            }
        };
    }

    for i in 0..1000 {
        input.lines().for_each(|l| {
            let parts: Vec<&str> = l.split("->").collect();
            let location = parts.last().unwrap().trim();

            let left_side: Vec<&str> = parts[0]
                .split(" ")
                .map(|e| e.trim())
                .filter(|e| !e.is_empty())
                .collect();

            match left_side.len() {
                1 => {
                    let val = resolve_value(&wire_values, &left_side[0]);
                    if let Some(val) = val {
                        if location != "b" {
                            wire_values.insert(location, val);
                        }
                    }
                }
                2 => {
                    let _instruction = left_side[0];
                    let target = left_side[1];

                    let val = resolve_value(&wire_values, &target);
                    if let Some(val) = val {
                        wire_values.insert(location, !val);
                    }
                }
                _ => {
                    let target_one = left_side[0];
                    let op = left_side[1];
                    let target_two = left_side[2];
                    match op {
                        "AND" | "OR" => {
                            let a = resolve_value(&wire_values, target_one);
                            let b = resolve_value(&wire_values, target_two);

                            if a.is_some() && b.is_some() {
                                let v = if op == "OR" {
                                    a.unwrap() | b.unwrap()
                                } else {
                                    a.unwrap() & b.unwrap()
                                };
                                wire_values.insert(location, v);
                            }
                        }
                        "LSHIFT" | "RSHIFT" => {
                            let a = resolve_value(&wire_values, target_one);
                            let b = resolve_value(&wire_values, target_two);
                            if a.is_some() && b.is_some() {
                                let v = if op == "LSHIFT" {
                                    a.unwrap() << b.unwrap()
                                } else {
                                    a.unwrap() >> b.unwrap()
                                };
                                wire_values.insert(location, v);
                            }
                        }
                        _ => panic!("wtf"),
                    }
                }
            }
        });
    }
    if wire_values.contains_key("a") {
        println!("{}", wire_values.get("a").unwrap())
    }

    dbg!(wire_values.get("a"));
}
