fn main() {
    let input = include_str!("./input.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> i64 {
    let mut nums: Vec<i64> = input
        .split(",")
        .filter_map(|s| s.trim().parse::<i64>().ok())
        .collect();

    nums[1] = 12;
    nums[2] = 2;
    let mut i = 0;
    let mut instruction = nums[i];

    while instruction != 99 {
        let pos1 = nums[i + 1];
        let pos2 = nums[i + 2];
        let pos3 = nums[i + 3];

        //println!("The poses are {} {} {}", pos1, pos2, pos3);

        let val1 = nums[pos1 as usize];
        let val2 = nums[pos2 as usize];

        //println!("The vals are {} {}", val1, val2);

        match instruction {
            1 => nums[pos3 as usize] = val1 + val2,
            2 => nums[pos3 as usize] = val1 * val2,
            _ => {
                dbg!("Reached end");
            }
        }

        i = i + 4;
        instruction = nums[i];
    }

    return nums[0];
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = part1("1,9,10,3,2,3,11,0,99,30,40,50");
        assert_eq!(result, 3500);
    }
}
