use std::collections::{HashMap, HashSet};

fn main() {
    let input = include_str!("./input.txt");

    let mut connections: HashMap<String, HashMap<String, i32>> = HashMap::new();
    input.lines().for_each(|l| {
        let parts: Vec<&str> = l.split(" ").collect();
        let start = parts[0].to_owned();
        let end = parts[2].to_owned();
        let dist = parts[4].parse::<i32>().unwrap();

        connections
            .entry(start.clone())
            .and_modify(|entry| {
                entry.insert(end.clone(), dist);
            })
            .or_insert({
                let mut hm = HashMap::new();
                hm.insert(end.clone(), dist);
                hm
            });

        connections
            .entry(end)
            .and_modify(|entry| {
                entry.insert(start.clone(), dist);
            })
            .or_insert({
                let mut hm = HashMap::new();
                hm.insert(start, dist);
                hm
            });
    });

    dbg!(&connections);

    fn dfs(
        curr: &str,
        seen: HashSet<String>,
        connections: &HashMap<String, HashMap<String, i32>>,
        city_count: usize,
        distance: i32,
    ) -> i32 {
        //println!("curr at {curr} with dist {distance}");
        if seen.len() == city_count {
            return distance;
        } else {
            let city_connections = connections.get(curr).unwrap();
            let max_dist = city_connections
                .iter()
                .filter(|(name, _dist)| !seen.contains(name.to_owned()))
                .map(|(name, dist)| {
                    let set1 = HashSet::from([name.clone()]);
                    let new_seen = seen.clone().union(&set1).cloned().collect();
                    return dfs(name, new_seen, connections, city_count, distance + dist);
                })
                .max();

            return max_dist.unwrap_or(-1);
        }
    }

    let ans = connections
        .keys()
        .map(|start| {
            let seen = HashSet::from([start.to_owned()]);
            dfs(start, seen, &connections, connections.keys().len(), 0)
        })
        .filter(|dist| *dist > 0)
        .max();

    dbg!(ans);
}
