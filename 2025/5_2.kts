import java.io.File

val input = File("5.txt").readLines()
val splitPos = input.indexOf("")
val ranges = input.take(splitPos).map { rangeString ->
    val rangeList = rangeString.split("-")
    Pair(
        rangeList.first().toLong(),
        rangeList.last().toLong(),
    )
}.sortedBy { it.first }

val mergedRanges = mutableListOf<Pair<Long, Long>>()

fun dfs(lower: Long, upper: Long, i: Int) {
    if (i >= ranges.size) {
        if (mergedRanges.last().second != upper) {
            mergedRanges.add(Pair(lower, upper))
        }
        return
    }

    val currentRange = ranges[i]
    val newLower = currentRange.first
    val newUpper = currentRange.second

    if (newLower in lower..upper+1) {
        dfs(lower, currentRange.second.coerceAtLeast(upper), i+1)
    } else {
        mergedRanges.add(Pair(lower, upper))
        dfs(newLower, newUpper, i+1)
    }
}

dfs(ranges.first().first, ranges.first().second, 1)

//mergedRanges.forEach {
//    println(it)
//}

val answer = mergedRanges.sumOf {
    it.second-it.first+1
}

println(answer)

332998283036769