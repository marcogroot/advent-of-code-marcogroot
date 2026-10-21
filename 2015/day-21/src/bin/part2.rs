fn simulate(dmg: i32, amr: i32, cost: i32) -> bool {
    // let mut hp = 100;
    //let mut e_hp = 100;
    let e_dmg = 8;
    let e_amr = 2;

    let dmg_dealt = (dmg - e_amr).max(1);
    let dmg_taken = (e_dmg - amr).max(1);

    // if cost < 10 {
    //     println!("{dmg}, {amr}");
    //     println!("dealt: {dmg_dealt} taken: {dmg_taken}, cost: {cost}");
    // }
    return dmg_dealt >= dmg_taken;
}
fn main() {
    let input = include_str!("./input.txt");

    let items: Vec<(i32, i32, i32)> = vec![
        (8, 4, 0),
        (10, 5, 0),
        (25, 6, 0),
        (40, 7, 0),
        (74, 8, 0),
        //armor
        (13, 0, 1),
        (31, 0, 2),
        (53, 0, 3),
        (75, 0, 4),
        (102, 0, 5),
        //
        (25, 1, 0),
        (50, 2, 0),
        (100, 3, 0),
        (20, 0, 1),
        (40, 0, 2),
        (80, 0, 3),
    ];

    fn dfs(
        i: usize,
        items: &Vec<(i32, i32, i32)>,
        cost: i32,
        dmg: i32,
        amr: i32,
        ring_count: i32,
    ) -> i32 {
        if i == items.len() {
            if simulate(dmg, amr, cost) {
                return 0;
            } else {
                return cost;
            }
        } else {
            if i < 4 {
                let item = items[i];
                let buy = dfs(5, items, cost + item.0, dmg + item.1, amr + item.2, 0);
                let skip = dfs(i + 1, items, cost, dmg, amr, 0);
                return buy.max(skip);
            } else if i == 4 {
                let item = items[i];
                return dfs(5, items, cost + item.0, dmg + item.1, amr + item.2, 0);
            } else if i <= 9 {
                let item = items[i];
                let buy = dfs(10, items, cost + item.0, dmg + item.1, amr + item.2, 0);
                let skip = dfs(i + 1, items, cost, dmg, amr, 0);
                return buy.max(skip);
            } else {
                if ring_count < 2 {
                    let item = items[i];
                    let buy = dfs(
                        i + 1,
                        items,
                        cost + item.0,
                        dmg + item.1,
                        amr + item.2,
                        ring_count + 1,
                    );
                    let skip = dfs(i + 1, items, cost, dmg, amr, ring_count);
                    return buy.max(skip);
                } else {
                    return dfs(i + 1, items, cost, dmg, amr, ring_count);
                }
            }
        }
    }

    let ans = dfs(0, &items, 0, 0, 0, 0);
    dbg!(ans);
}
