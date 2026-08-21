import java.io.File

val input = File("5.txt").readLines()
val splitPos = input.indexOf("")
val ranges = input.take(splitPos).map { rangeString ->
    val rangeList = rangeString.split("-")
    Pair(
        rangeList.first().toLong(),
        rangeList.last().toLong(),
    )
}

val nums = input.drop(splitPos+1).map { it.toLong() }

val answer = nums.sumOf { num ->
    if (ranges.any {  range ->
        num >= range.first && num <= range.second
    }) 1L else 0L
}



println(answer)
