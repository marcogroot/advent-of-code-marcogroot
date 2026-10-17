fn main() {
    let input = include_str!("./input.txt");

    let vals: Vec<(i64, i64, i64, i64, i64)> = input
        .lines()
        .map(|line| {
            let updated_line = line.replace(",", "");
            let parts: Vec<&str> = updated_line.split(" ").collect();
            let cap = parts[2].parse::<i64>().unwrap();
            let dur = parts[4].parse::<i64>().unwrap();
            let flav = parts[6].parse::<i64>().unwrap();
            let text = parts[8].parse::<i64>().unwrap();
            let cal = parts[10].parse::<i64>().unwrap();

            return (cap, dur, flav, text, cal);
        })
        .collect();

    dbg!(&vals);
    println!("abc");
    let mut best: i64 = 0;
    for a in 1..101 {
        for b in 0..101 {
            for c in 0..101 {
                for d in 0..101 {
                    if a + b + c + d == 100 {
                        let capacity =
                            ((vals[0].0 * a) + (vals[1].0 * b) + (vals[2].0 * c) + (vals[3].0 * d))
                                .max(0);

                        let durability =
                            ((vals[0].1 * a) + (vals[1].1 * b) + (vals[2].1 * c) + (vals[3].1 * d))
                                .max(0);

                        let flavour =
                            ((vals[0].2 * a) + (vals[1].2 * b) + (vals[2].2 * c) + (vals[3].2 * d))
                                .max(0);

                        let texture =
                            ((vals[0].3 * a) + (vals[1].3 * b) + (vals[2].3 * c) + (vals[3].3 * d))
                                .max(0);

                        let res = capacity * durability * flavour * texture;
                        if res > best {
                            println!("{a} {b} {c} {d}: {best}");
                        }
                        best = best.max(res);
                    }

            }
        }
    }

    dbg!(best);
}
