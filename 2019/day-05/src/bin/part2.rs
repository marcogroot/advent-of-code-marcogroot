fn main() {
    let input = include_str!("./input1.txt");
    let output = part2(input);
    dbg!(output);
}

fn part2(input: &str) -> String {
    let mut answer = 0;
    input.split("\n").into_iter().for_each(|num| {
        let n = num.parse::<i32>();
        match n {
            Ok(mut value) => {
                while value > 0 {
                    value = (value / 3) - 2;
                    if value >= 0 {
                        answer += value;
                    }
                }
            }
            Err(_) => answer += 0,
        }
    });

    return answer.to_string();
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = part2("100756");
        assert_eq!(result, "2".to_string());
    }
}
