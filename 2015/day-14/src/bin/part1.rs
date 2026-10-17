fn main() {
    let input = include_str!("./input.txt");

    let answer: i32 = input
        .lines()
        .map(|line| {
            //println!("Abc");
            //println!("{line}");
            let parts: Vec<&str> = line.split(" ").collect();
            let speed = parts[3].parse::<i32>().unwrap();
            let secs = parts[6].parse::<i32>().unwrap();
            let rest = parts[13].parse::<i32>().unwrap();

            println!("{speed}, {secs}, {rest}");

            let seconds = 2503;
            let total_time = secs + rest;
            let full_reps = seconds / total_time;
            let dist_so_far = full_reps * (speed * secs);
            let remainder = seconds - (full_reps * total_time);
            let r = remainder.min(secs) * speed;

            //println!("{remainder}, {dist_so_far}");
            let ans = dist_so_far + r;

            println!("{ans}");
            return ans;
        })
        .max()
        .unwrap();

    dbg!(answer);
}
