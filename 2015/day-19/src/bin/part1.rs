use std::collections::{HashMap, HashSet};

fn main() {
    let input = include_str!("./input.txt");
    let mut replacements: HashMap<String, Vec<String>> = HashMap::new();

    input.lines().for_each(|line| {
        let parts: Vec<&str> = line.split(" ").collect();
        if parts.len() == 3 {
            let start = parts[0];
            let end = parts[2];

            replacements
                .entry(start.to_owned())
                .and_modify(|v| v.push(end.to_owned()))
                .or_insert(vec![end.to_owned()]);
        }
    });

    let content = input.lines().last().unwrap();

    let mut seen: HashSet<String> = HashSet::new();

    for window_size in 1..3 {
        for (i, byte_window) in content.as_bytes().windows(window_size).enumerate() {
            let substring = std::str::from_utf8(byte_window).unwrap();
            if replacements.contains_key(substring) {
                let start = &content[0..i];
                let end = &content[i + (window_size)..];
                for replacement in replacements.get(substring).unwrap() {
                    //println!("{} {} {}", start, replacement, end);
                    let mut new_str = String::new();
                    new_str.push_str(start);
                    new_str.push_str(replacement);
                    new_str.push_str(end);

                    seen.insert(new_str);
                }
            }
        }
    }

    dbg!(seen.len());
}
