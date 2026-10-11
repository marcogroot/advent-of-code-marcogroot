use std::collections::HashSet;

fn main() {
    let input = include_str!("./input.txt");
    let mut x1 = 0;
    let mut x2 = 0;
    let mut y1 = 0;
    let mut y2 = 0;

    let mut visited: HashSet<(i32, i32)> = HashSet::new();

    visited.insert((0, 0));
    input
        .chars()
        .enumerate()
        .filter(|(_i, c)| c != &'\n')
        .for_each(|(i, c)| {
            //dbg!(c);
            if i % 2 == 0 {
                match c {
                    '^' => y1 += 1,
                    'v' => y1 -= 1,
                    '>' => x1 += 1,
                    '<' => x1 -= 1,
                    _ => panic!("wtf"),
                }
            } else {
                match c {
                    '^' => y2 += 1,
                    'v' => y2 -= 1,
                    '>' => x2 += 1,
                    '<' => x2 -= 1,
                    _ => panic!("wtf"),
                }
            }

            visited.insert((x1, y1));
            visited.insert((x2, y2));
        });

    println!("{}", visited.len());
}
