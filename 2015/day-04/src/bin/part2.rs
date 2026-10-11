use md5::compute;

fn main() {
    //let input = include_str!("./input.txt");
    let input = "yzbqklnj";

    let mut count = 0;

    loop {
        let temp = format!("{}{}", input, count);
        let hash = md5::compute(temp);
        let wtf: String = format!("{:?}", hash);
        if wtf.starts_with("000000") {
            break;
        }
        count += 1;
    }

    println!("{count}");
}
