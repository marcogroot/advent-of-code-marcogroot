#[derive(Debug)]
struct Dim {
    l: u128,
    w: u128,
    h: u128,
}

fn main() {
    let input = include_str!("./input.txt");
    let dims = input
        .lines()
        .map(|l| {
            let dimensions: Vec<&str> = l.split("x").collect();
            return Dim {
                l: dimensions[0].parse::<u128>().unwrap(),
                w: dimensions[1].parse::<u128>().unwrap(),
                h: dimensions[2].parse::<u128>().unwrap(),
            };
        })
        .collect();

    //dims.for_each(|f| println!("{:?}", f));
    let output = part2(&dims);
    dbg!(output);
}

fn part1(input: &Vec<Dim>) -> u128 {
    input
        .iter()
        .map(|dim| {
            let a = dim.l * dim.w * 2;
            let b = dim.w * dim.h * 2;
            let c = dim.h * dim.l * 2;

            let s = a.min(b).min(c) / 2;

            return a + b + c + s;
        })
        .sum()
}

fn part2(input: &Vec<Dim>) -> u128 {
    input
        .iter()
        .map(|dim| {
            let mut nums: Vec<u128> = vec![dim.l, dim.w, dim.h];
            nums.sort();

            let a = (nums[0] * 2) + (nums[1] * 2);
            let rib = dim.l * dim.w * dim.h;
            return a + rib;
        })
        .sum()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        assert_eq!(1, 1 + 1);
    }
}
