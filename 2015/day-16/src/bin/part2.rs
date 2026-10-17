use std::collections::HashMap;
fn main() {
    let input = include_str!("./input.txt");
    let mut possible_count = 0;

    input.lines().for_each(|line| {
        let updated_line = line.replace(":", "").replace(",", "");
        let parts: Vec<&str> = updated_line.split(" ").collect();

        let number = parts[1];

        let updated_parts: Vec<&str> = parts.iter().skip(2).copied().collect();
        let mut m: HashMap<String, i32> = HashMap::new();
        updated_parts.windows(2).step_by(2).for_each(|a| {
            let part1 = a[0];
            let part2 = a[1].parse::<i32>().unwrap();
            m.insert(part1.to_owned(), part2);
        });

        let mut possible = true;

        let reqs: Vec<(&str, i32)> = vec![
            ("children", 3),
            //("cats", 7),
            ("samoyeds", 2),
            //("pomeranians", 3),
            ("akitas", 0),
            ("vizslas", 0),
            //("goldfish", 5),
            //("trees", 3),
            ("cars", 2),
            ("perfumes", 1),
        ];

        let req2: Vec<(&str, i32)> = vec![("cats", 7), ("trees", 3)];
        let req3: Vec<(&str, i32)> = vec![("pomeranians", 3), ("goldfish", 5)];

        for req in reqs {
            if let Some(val) = m.get(req.0) {
                if *val != req.1 {
                    possible = false;
                }
            }
        }

        for req in req2 {
            if let Some(val) = m.get(req.0) {
                if *val <= req.1 {
                    possible = false;
                }
            }
        }

        for req in req3 {
            if let Some(val) = m.get(req.0) {
                if *val >= req.1 {
                    possible = false;
                }
            }
        }

        if possible {
            possible_count += 1;
            dbg!(number);
        }
    });

    dbg!(possible_count);
}
