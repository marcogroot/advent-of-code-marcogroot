fn main() {
    let input = "124075-580769";
    let output = solve(input);
    dbg!(output);
}

fn is_ascending(num: &str) -> bool {
    let chars: Vec<char> = num.chars().collect();
    chars.windows(2).all(|w| {
        let a = w[0].to_digit(10).unwrap();
        let b = w[1].to_digit(10).unwrap();

        a <= b
    })
}

fn has_precise_double(num: &str) -> bool {
    let chars: Vec<char> = num.chars().collect();
    let mut l = 0;

    while l < chars.len() - 1 {
        let left_char = chars[l];
        let mut r = l;
        while r < chars.len() - 1 && left_char == chars[r + 1] {
            r += 1;
        }
        let length = r - l + 1;
        if length == 2 {
            return true;
        }
        l = r + 1;
    }

    false
}

fn solve(input: &str) -> i64 {
    let a: Vec<&str> = input.split("-").collect();
    let start = a[0].parse::<i64>().unwrap();
    let end = a[1].parse::<i64>().unwrap();

    let mut count = 0;

    for num in start..end + 1 {
        let num_string = num.to_string();
        let is_asc = is_ascending(&num_string);
        let dub = has_precise_double(&num_string);

        if is_asc && dub {
            count += 1
        }
    }

    count
}
