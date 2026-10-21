use std::collections::HashMap;

fn simulate() -> i32 {
    let input = include_str!("./input.txt");
    let mut replacements: HashMap<String, String> = HashMap::new();

    input.lines().for_each(|line| {
        let parts: Vec<&str> = line.split(" ").collect();
        if parts.len() == 3 {
            let end = parts[0];
            let start = parts[2];

            if replacements.contains_key(start) {
                println!("wtf nonono");
            }
            replacements.insert(start.to_owned(), end.to_owned());
        }
    });

    let mut names: Vec<&String> = replacements.keys().into_iter().collect();

    names.sort_by(|a, b| b.len().cmp(&a.len()));
    //dbg!(&names);
    let mut curr = input.lines().last().unwrap().to_owned();
    let mut count = 0;
    while curr != "e" {
        //println!("{curr}");
        let mut i = 0;
        let mut cont = true;
        count += 1;
        while cont {
            if curr.contains(names[i]) {
                let updated = curr.replacen(names[i], replacements.get(names[i]).unwrap(), 1);
                curr = updated;
                cont = false;
            } else {
                i += 1;
            }
        }
    }
    return count;
}
fn main() {
    let mut ans = 1000;
    for i in 0..1000 {
        ans = ans.min(simulate());
    }
    //dbg!(ans);
    println!("{ans}");
}
