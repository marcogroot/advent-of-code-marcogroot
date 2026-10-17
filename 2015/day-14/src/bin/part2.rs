use std::collections::HashMap;

struct Rein {
    name: String,
    speed: i32,
    secs: i32,
    rest: i32,
}

fn main() {
    let input = include_str!("./input.txt");

    let reins: Vec<Rein> = input
        .lines()
        .map(|line| {
            let parts: Vec<&str> = line.split(" ").collect();
            let name = parts[0].into();
            let speed = parts[3].parse::<i32>().unwrap();
            let secs = parts[6].parse::<i32>().unwrap();
            let rest = parts[13].parse::<i32>().unwrap();
            return Rein {
                name,
                speed,
                secs,
                rest,
            };
        })
        .collect();

    let mut m: HashMap<String, (i32, i32, i32, i32)> = HashMap::new();
    reins.iter().for_each(|r| {
        m.insert(r.name.clone(), (0, 0, r.secs, 0));
    });

    for _ in 0..2503 {
        for (name, (_, distance, running_left, resting_left)) in m.iter_mut() {
            let stats = reins.iter().find(|r| r.name == name.clone()).unwrap();
            if *running_left > 0 {
                *distance += stats.speed;
                *running_left -= 1;
                if *running_left == 0 {
                    *resting_left = stats.rest;
                }
            } else {
                *resting_left -= 1;
                if *resting_left == 0 {
                    *running_left += stats.secs;
                }
            }
        }
        let mut best_dist = 0;
        for (_, (_, distance, _, _)) in m.iter() {
            if distance > &best_dist {
                best_dist = *distance;
            }
        }
        let names: Vec<String> = m
            .iter()
            .filter(|m| m.1.1 == best_dist)
            .map(|m| m.0.clone())
            .collect();

        names.iter().for_each(|name| {
            let existing_entry = m.get(name).unwrap();
            let new_entry = (
                existing_entry.0 + 1,
                existing_entry.1,
                existing_entry.2,
                existing_entry.3,
            );
            m.insert(name.into(), new_entry);
        });
    }

    m.clone()
        .values()
        .for_each(|(points, dist, _, _)| println!("points: {points}, {dist}"));
}
