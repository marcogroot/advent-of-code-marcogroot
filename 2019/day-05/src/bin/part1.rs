fn main() {
    let input = include_str!("./input1.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> i64 {
    input
        .lines()
        .filter_map(|line| line.parse::<i64>().ok())
        .map(|num| (num / 3) - 2)
        .sum()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = part1("12");
        assert_eq!(result, 2);
    }
}
