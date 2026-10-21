fn count(arr: &[[bool; 100]; 100]) -> i32 {
    let mut count = 0;
    for r in 0..arr.len() {
        for c in 0..arr.len() {
            if arr[r][c] {
                count += 1;
            } else {
            }
        }
    }
    return count;
}

fn print(arr: &[[bool; 100]; 100]) {
    for r in 0..arr.len() {
        for c in 0..arr.len() {
            if arr[r][c] {
                print!("#");
            } else {
                print!(".");
            }
        }
        println!("");
    }
}

fn get_new_arr(old: &[[bool; 100]; 100]) -> [[bool; 100]; 100] {
    let mut new: [[bool; 100]; 100] = [[false; 100]; 100];

    let dirs: Vec<(i32, i32)> = vec![
        (-1, -1),
        (-1, 0),
        (-1, 1),
        (1, -1),
        (1, 0),
        (1, 1),
        (0, -1),
        (0, 1),
    ];

    for r in 0..old.len() {
        for c in 0..old.len() {
            let mut count = 0;
            for (rd, cd) in dirs.clone() {
                let tr = r as i32 - rd;
                let tc = c as i32 - cd;
                if tr >= 0 && tr < old.len() as i32 && tc >= 0 && tc < old.len() as i32 {
                    if old[tr as usize][tc as usize] {
                        count += 1;
                    }
                }
            }
            let t = old[r][c];
            if t {
                if count == 2 || count == 3 {
                    new[r][c] = true
                } else {
                    new[r][c] = false;
                }
            } else {
                if count == 3 {
                    new[r][c] = true;
                }
            }
            let start = 0 as usize;
            let end = (old.len() - 1) as usize;
            new[start][start] = true;
            new[start][end] = true;
            new[end][start] = true;
            new[end][end] = true;
        }
    }
    return new;
}

fn main() {
    let input = include_str!("./input.txt");
    dbg!(input);

    let mut start: [[bool; 100]; 100] = [[false; 100]; 100];

    input.lines().enumerate().for_each(|(r, line)| {
        let bs = line.as_bytes();
        dbg!(bs.len());
        for c in 0..bs.len() {
            dbg!(bs[c]);
            if bs[c] == b'#' {
                start[r][c] = true;
            }
        }
    });
    let begin = 0 as usize;
    let end = (start.len() - 1) as usize;
    start[begin][begin] = true;
    start[begin][end] = true;
    start[end][begin] = true;
    start[end][end] = true;

    let mut old = start;
    for _ in 0..100 {
        let new = get_new_arr(&old);
        old = new;
    }
    print(&old);
    let res = count(&old);
    dbg!(res);
}
