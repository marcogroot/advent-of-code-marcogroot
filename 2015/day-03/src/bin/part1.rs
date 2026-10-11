use std::collections::HashSet;

fn main() {
    let input = include_str!("./input.txt");
    let mut x1 = 0;
    let mut y1 = 0;

    let mut visited: HashSet<(i32, i32)> = HashSet::new();

    visited.insert((0, 0));
    input.chars().filter(|(c)| c != &'\n').for_each(|c| {
        //dbg!(c);
        match c {
            '^' => y1 += 1,
            'v' => y1 -= 1,
            '>' => x1 += 1,
            '<' => x1 -= 1,
            _ => panic!("wtf"),
        }

        visited.insert((x1, y1));
    });

    println!("{}", visited.len());
}
