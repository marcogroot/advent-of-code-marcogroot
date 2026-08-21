import java.io.File

val input = File("7.txt").readLines().map { it.toMutableList() }.toMutableList()

var start = Pair(0, input.first().indexOf('S'))

val dp = mutableMapOf<Pair<Int, Int>, Long>()

fun dfs(row: Int, col: Int) : Long {
    if (row < 0 || row >= input.size) return 0
    if (col < 0 || col >= input.first().size) return 0
    val p = Pair(row, col)
    val res = dp[p]
    res?.let {
        return res
    }

    val curr = input[row][col]

    return if (curr != '^') {
        dp[p] = dfs(row+1, col)
        dp[p]!!
    }
    else {
        dp[p] = dfs(row, col+1) + dfs(row, col-1) + 1
        dp[p]!!
    }
}

val answer = dfs(start.first+1, start.second) + 1


println(answer)

