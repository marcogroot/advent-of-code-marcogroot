import java.io.File

val input = File("4.txt").readLines()

var answer = 0

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

input.forEachIndexed { r, row ->
    row.forEachIndexed { c, character ->
        if (character == '@') {
            val adjacentRolls = directions.sumOf { dir ->
                val new_r = r + dir.first
                val new_c = c + dir.second

                if (is_valid(new_r, new_c)) {
                    val adjacentChar = input[new_r][new_c]
                    if (adjacentChar == '@') 1 else 0
                } else {
                    0L
                }
            }

            //println("At $r $c and there are $adjacentRolls adjacent")

            if (adjacentRolls <= 3) {
                answer ++
            }
        }
    }
}

fun is_valid(r: Int, c: Int) : Boolean {
    if (r < 0 || r >= input.size) return false;
    if (c < 0 || c >= input.first().length) return false;
     return true
}

println(answer)
