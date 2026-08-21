import java.io.File

val input = File("4.txt").readLines()

var directions = listOf(
    Pair(0, -1),
    Pair(-1, -1),
    Pair(-1, 0),
    Pair(-1, 1),
    Pair(0, 1),
    Pair(1, 1),
    Pair(1, 0),
    Pair(1, -1),
)

var rollsRemoved = 1

val mutableInput = input.map { it.toMutableList() }.toMutableList()

var answer = 0
while (rollsRemoved > 0) {
    rollsRemoved = 0
    rollsRemoved = removeRoles(mutableInput)
    answer += rollsRemoved
}

println(answer)

fun printGrid(grid: MutableList<MutableList<Char>>) : Unit {
    grid.forEach { row ->
        row.forEach { print(it) }
        println("\n")
    }
    println("-------------------------------------")
}

fun removeRoles(grid: MutableList<MutableList<Char>>) : Int {
    //printGrid(grid)
    var rolesRemoved = 0
    grid.forEachIndexed { r, row ->
        row.forEachIndexed { c, character ->
            if (character == '@') {
                val adjacentRolls = directions.sumOf { dir ->
                    val new_r = r + dir.first
                    val new_c = c + dir.second

                    if (is_valid(new_r, new_c)) {
                        val adjacentChar = grid[new_r][new_c]
                        if (adjacentChar == '@') 1 else 0
                    } else {
                        0L
                    }
                }

                //println("At $r $c and there are $adjacentRolls adjacent")
                if (adjacentRolls <= 3) {
                    rolesRemoved ++
                    grid[r][c] = '.'
                }
            }
        }
    }
    return rolesRemoved
}

fun is_valid(r: Int, c: Int) : Boolean {
    if (r < 0 || r >= input.size) return false;
    if (c < 0 || c >= input.first().length) return false;
     return true
}
