fn main() {
    let input = include_str!("./input.txt");

    let i: i32 = input
        .lines()
        .map(|l| {
            let bytes_len = l.bytes().len();
            let a = l.bytes().filter(|&b| b == b'\\' || b == b'"').count() + 2 + bytes_len;
            let ans = (a - bytes_len) as i32;
            println!("Word: {l} {bytes_len}, {a}: {ans}");
            ans
        })
        .sum();

    dbg!(i);
}
