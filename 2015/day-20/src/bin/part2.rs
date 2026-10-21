fn main() {
    let input = 36000000;

    let mut presents = 0;

    let mut house_count = 0;
    while presents < input {
        house_count += 1;

        presents = 0;
        let rt: i32 = (house_count as u32).isqrt() as i32 + 1;
        let mut comps: Vec<i32> = Vec::new();
        for i in 1..rt + 1 {
            if house_count % i == 0 {
                let res = house_count / i;
                comps.push(i);
                if res > rt {
                    comps.push(res);
                }
            }
        }
        for c in comps {
            let max_house = c * 50;
            if house_count <= max_house {
                presents += c * 11;
            }
        }

        //dbg!(presents);
    }

    dbg!(house_count);
}
