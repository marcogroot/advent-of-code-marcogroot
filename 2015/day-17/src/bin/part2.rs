use std::cmp::min;

fn main() {
    let input = include_str!("./input.txt");

    let nums: Vec<i32> = input
        .lines()
        .map(|line| line.parse::<i32>().unwrap())
        .collect();

    fn min_containers(i: usize, sum: i32, nums: &Vec<i32>, container_count: i32) -> i32 {
        if i == nums.len() {
            if sum == 150 {
                return container_count;
            } else {
                return 10000;
            }
        }

        let a = min_containers(i + 1, sum, nums, container_count);
        let b = min_containers(i + 1, sum + nums[i], nums, container_count + 1);

        return a.min(b);
    }

    let min_containers = min_containers(0, 0, &nums, 0);

    fn dfs(
        i: usize,
        sum: i32,
        nums: &Vec<i32>,
        container_count: i32,
        min_container_count: i32,
    ) -> i32 {
        if i == nums.len() {
            if sum == 150 && container_count == min_container_count {
                return 1;
            } else {
                return 0;
            }
        }

        return dfs(i + 1, sum, nums, container_count, min_container_count)
            + dfs(
                i + 1,
                sum + nums[i],
                nums,
                container_count + 1,
                min_container_count,
            );
    }

    let res = dfs(0, 0, &nums, 0, min_containers);
    dbg!(res);
}
