fn main() {
    let input = include_str!("./input.txt");

    let i: i32 = input
        .lines()
        .map(|l| {
            let bytes_len = l.bytes().len() as i32;
            let mut i = 1 as usize;
            let mut minuses = 0;

            //"aaa\"aaa"

            let bytes = l.as_bytes();
            while i < (bytes_len - 1) as usize {
                dbg!(l);
                let curr = bytes[i];
                //dbg!(i);
                //dbg!(curr as char);
                if i + 1 < bytes.len() && curr == b'\\' {
                    let next = l.bytes().nth(i + 1).unwrap();
                    match next {
                        b'\\' | b'"' => {
                            i += 2;
                            minuses += 1;
                        }
                        b'x' => {
                            i += 4;
                            minuses += 3
                        }
                        _ => {
                            i += 1;
                        }
                    }
                } else {
                    i += 1;
                }
            }
            let chars = bytes_len - minuses - 2;

            //dbg!(abc);
            let ans = (bytes_len - chars) as i32;
            //println!("Word: {l} {bytes_len}, {chars}: {ans}");
            ans
        })
        .sum();

    dbg!(i);
}
