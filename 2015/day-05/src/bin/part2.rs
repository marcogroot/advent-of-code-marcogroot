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
            let chars: Vec<char> = line.chars().collect();
            let repeat_check = chars.clone().windows(3).any(|w| w[0] == w[2]);

            println!("Current line: {}", line);

            let first_check = (0..line.len() - 1).any(|i| {
                let substr = &line[i + 2..];
                let search_term: String = format!("{}{}", &chars[i], &chars[i + 1]);

                //println!("Searching substring {} for {}", substr, search_term);

                return substr.contains(&search_term);
            });

            let res = repeat_check && first_check;
            println!("{} {}", line, res);
            return res;
        })
        .filter(|&t| t)
        .count();

    println!("{nice_count}");
}
