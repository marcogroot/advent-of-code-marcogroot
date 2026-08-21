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

m.forEach {
    println("${it.key}: ${it.value}")
}

fun dfs(curr: String, visited: Set<String>) : Int {
    if (curr == "out") return 1

    val adj = m.getOrDefault(curr, emptyList())

    var count = 0
    adj.forEach {
        if (!visited.contains(it)) {
            count += dfs(it, visited + it)
        }
    }

    return count
}


val res = dfs("you", setOf())
println(res)