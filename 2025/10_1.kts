import java.io.File

data class Switch(
    val goalState: List<Boolean>,
    var switches: List<List<Long>>,
    var energy: List<Long>,
) {
    fun getMinPresses() : Int {
        return dfs(0, listOf())
    }

    fun dfs(i: Int, sw: List<List<Long>>) : Int {
        if (i == switches.size) {
            val start = goalState.map { false }.toMutableList()
            sw.forEach { switch ->
                switch.forEach { s ->
                    start[s.toInt()] = !start[s.toInt()]
                }
            }
            if (start == goalState) return sw.size
            return Int.MAX_VALUE
        }
        return minOf(
            dfs(i+1, sw + listOf(switches[i])),
            dfs(i+1, sw)
        )
    }
}

val input = File("10.txt").readLines().map { line ->
    val lSquareIndex = line.indexOfFirst { it == '[' }
    val rSquareIndex = line.indexOfFirst { it == ']' }

    val statesString = line.substring(startIndex = lSquareIndex+1, endIndex = rSquareIndex)
    val states = statesString.map { it == '#'
    }

    val lCurlyIndex = line.indexOfFirst { it == '{' }
    val switchesString = line.substring(startIndex = rSquareIndex+1, endIndex = lCurlyIndex-1)
    val switches = parseSwitches(switchesString, listOf())

    val rCurlyIndex = line.indexOfFirst { it == '}' }
    val energies = line.substring(startIndex = lCurlyIndex+1, endIndex = rCurlyIndex).split(",").map { it.toLong() }

    Switch(
        goalState = states,
        switches = switches,
        energy = energies
    )
}

val answer = input.sumOf {
    val a = it.getMinPresses()
    println(a)
    a
}

println(answer)
// [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}

fun parseSwitches(str: String, list: List<List<Long>>) : List<List<Long>> {
    val l = str.indexOfFirst { it == '(' }
    if (l == -1) return list
    val r = str.indexOfFirst { it == ')' }
    val switchesString = str.substring(startIndex = l+1, endIndex = r).split(",").map { it.toLong() }
    val newList = list + listOf(switchesString)
    return parseSwitches(str.substring(startIndex = r+1), newList)
}