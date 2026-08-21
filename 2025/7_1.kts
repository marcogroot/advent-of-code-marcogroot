import java.io.File

val input = File("7.txt").readLines().map { it.toMutableList() }.toMutableList()

var start = Pair(0, input.first().indexOf('S'))

val visited = mutableSetOf<Pair<Int, Int>>()

fun dfs(row: Int, col: Int) : Long {
    if (row < 0 || row >= input.size) return 0
    if (col < 0 || col >= input.first().size) return 0
    val p = Pair(row, col)

    if (visited.contains(p)) return 0
    visited.add(p)

    val curr = input[row][col]

    return if (curr != '^' && curr != '$') {
        dfs(row+1, col)
    }
    else {
        input[row][col] = '$'
        dfs(row, col+1) + dfs(row, col-1)
    }
}

dfs(start.first+1, start.second)
val answer = input.sumOf { it.count { it == '$' } }


println(answer)

