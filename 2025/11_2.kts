import java.io.File

val inputs = File("11.txt").readLines()

val m = mutableMapOf<String, List<String>>()

inputs.forEach {
    val a = it.replace(":", "")
    val b = a.split(" ")
    val key = b.first()
    val values = b.drop(1)
    m.put(key, values)
}

val theList = listOf("fft", "dac")
val isPossible = mutableMapOf<String, MutableMap<String, Long>>()

fun dfs(curr: String, visited: Set<String>) : Long {
    if (curr == "out" && visited.containsAll(theList)) {
        return 1L
    }

    val adj = m.getOrDefault(curr, emptyList())

    var count = 0L
    adj.forEach {
        if (!visited.contains(it)) {
            val pos = isPossible.getOrPut(it) { mutableMapOf() }
            val newVisited = visited + it
            val nextAdj = m.getOrDefault(it, emptyList())
            val blockedNeighbors = nextAdj.filter { n -> newVisited.contains(n) }.toSortedSet()

            val requiredKey = when {
                newVisited.containsAll(theList) -> "both"
                newVisited.contains("dac") -> "dac"
                newVisited.contains("fft") -> "fft"
                else -> "none"
            }
            val key = "$requiredKey|$blockedNeighbors"

            val cached = pos[key]
            if (cached != null) {
                count += cached
            } else {
                val res = dfs(it, newVisited)
                pos[key] = res
                count += res
            }
        }
    }

    return count
}

val res = dfs("svr", setOf())
println(res)