import java.io.File

data class Ranges(
    val lower: Long,
    val upper: Long,
)

val input = File("2.txt").readText()

val ranges = input
    .split(",")
    .map { it.split("-") }
    .map { Ranges(it.first().trim().toLong(), it.last().trim().toLong()) }

val answer = ranges.sumOf {
    var curr = it.lower
    var count = 0L
    while (curr <= it.upper) {
        val currString = curr.toString()
        val numberOfDigits = currString.length
        for (i in 1..<numberOfDigits) {
            if (numberOfDigits.mod(i) == 0) {
                val nums = currString.chunked(i)
                if (nums.all { it == nums.first() }) {
                    count += curr
                    break
                }
            }
        }
        curr++
    }
    count
}

println(answer)