use std::collections::HashSet;
fn main() {
    let input = include_str!("./input.txt");

    let mut vowels = HashSet::new();
    vowels.insert('a');
    vowels.insert('e');
    vowels.insert('i');
    vowels.insert('o');
    vowels.insert('u');

    let nice_count = input
        .lines()
        .map(|line| {
            let vowel_check = line.chars().filter(|c| vowels.contains(c)).count() >= 3;
            let chars: Vec<char> = line.chars().collect();
            let double_check = chars.windows(2).any(|w| w[0] == w[1]);
            let bad_check = !chars.windows(2).any(|w| {
                w[0] == 'a' && w[1] == 'b'
                    || w[0] == 'a' && w[1] == 'b'
                    || w[0] == 'c' && w[1] == 'd'
                    || w[0] == 'p' && w[1] == 'q'
                    || w[0] == 'x' && w[1] == 'y'
            });

            let res = vowel_check && double_check && bad_check;
            //println!("{} {}", line, res);

            return res;
        })
        .filter(|&t| t)
        .count();

    println!("{nice_count}");
}
