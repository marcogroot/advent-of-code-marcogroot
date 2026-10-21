fn main() {
    let input = 36000000;

    let mut presents = 0;

    let mut house_count = 0;
    while presents < input {
        house_count += 1;

        presents = 0;
        let rt: i32 = (house_count as u32).isqrt() as i32 + 1;
        //println!("{house_count} sqrt = {rt}");
        let mut comps: Vec<i32> = Vec::new();
        for i in 1..rt + 1 {
            if house_count % i == 0 {
                let res = house_count / i;
                if res > rt {
                    comps.push(res);
                }
                presents += i * 10;
            }
        }
        for c in comps {
            presents += c * 10;
        }

        //dbg!(presents);
    }

    dbg!(house_count);
}
