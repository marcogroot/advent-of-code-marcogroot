use std::collections::HashMap;

fn dfs(q: Vec<String>, people: &HashMap<String, HashMap<String, i32>>) -> i32 {
    if q.len() == people.len() {
        let total: i32 = q
            .iter()
            .enumerate()
            .map(|(i, curr)| {
                let prev_index = if i == 0 { q.len() - 1 } else { i - 1 };
                let next_index = if i == q.len() - 1 { 0 } else { i + 1 };

                let prev = &q[prev_index];
                let next = &q[next_index];

                let m = people.get(curr).unwrap();
                let a = m[prev];
                let b = m[next];

                return a + b;
            })
            .sum();
        return total;
    } else {
        return people
            .keys()
            .filter(|p| !q.contains(p))
            .map(|p| {
                let mut a = q.clone();
                a.push(p.clone());
                dfs(a, people)
            })
            .max()
            .unwrap();
    }
}

fn main() {
    let input = include_str!("./input.txt");

    let mut people: HashMap<String, HashMap<String, i32>> = HashMap::new();

    input.lines().for_each(|line| {
        let parts: Vec<&str> = line.split(" ").collect();
        let name_one = parts[0];
        let sign = if parts[2] == "gain" { 1 } else { -1 };
        let amount = parts[3].parse::<i32>().unwrap() * sign;
        let name_two = parts.last().unwrap().replace(".", "");

        people
            .entry(name_one.to_owned())
            .and_modify(|m| {
                m.insert(name_two.clone(), amount);
            })
            .or_insert({
                let mut new_m = HashMap::new();
                new_m.insert("marco".to_owned(), 0);
                new_m.insert(name_two, amount);
                new_m
            });
    });

    let mut m: HashMap<String, i32> = HashMap::new();

    people.keys().for_each(|name| {
        m.insert(name.clone(), 0);
    });
    people.insert("marco".into(), m);

    let res = dfs(vec![], &people);

    dbg!(res);

    println!("{}", people.len());
}
