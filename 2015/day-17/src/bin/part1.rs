fn main() {
    let input = include_str!("./input.txt");

    let nums: Vec<i32> = input
        .lines()
        .map(|line| line.parse::<i32>().unwrap())
        .collect();

    fn dfs(i: usize, sum: i32, nums: &Vec<i32>) -> i32 {
        if i == nums.len() {
            if sum == 150 {
                return 1;
            } else {
                return 0;
            }
        }

        return dfs(i + 1, sum, nums) + dfs(i + 1, sum + nums[i], nums);
    }

    let res = dfs(0, 0, &nums);
    dbg!(res);
}
