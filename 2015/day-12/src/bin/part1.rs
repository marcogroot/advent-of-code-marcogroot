fn main() {
    let input = include_str!("./input.txt");

    println!("Hello");

    let numbers: Vec<u8> = input
        .as_bytes()
        .to_vec()
        .iter()
        .map(|&b| {
            if b.is_ascii_digit() || b == b'-' {
                b
            } else {
                b' '
            }
        })
        .collect();

    let a: Vec<i32> = String::from_utf8(numbers)
        .unwrap()
        .split(" ")
        .filter(|x| !x.is_empty())
        .map(|it| it.parse::<i32>().unwrap())
        .collect();

    let b: i32 = a.iter().sum();

    dbg!(b);
}
