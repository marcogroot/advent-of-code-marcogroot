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

var answer = 0L

ranges.forEach {
    answer += it.calc()
}

fun Ranges.calc() : Long {
    var curr = getNextEvenDigitNumber(lower-1)
    if (curr > upper) return 0

    var count = 0L;
    while (curr <= upper) {
        val currString = curr.toString()
        val numberOfDigits = currString.length
        val lLeft = currString.take(numberOfDigits/2)
        val lRight = currString.takeLast(numberOfDigits/2)

        if (lLeft == lRight) {
            count += curr
        }
        curr = getNextEvenDigitNumber(curr)
    }

    return count
}

fun getNextEvenDigitNumber(num: Long) : Long {
    val newNum = num + 1
    val numberOfDigits = newNum.toString().length
    val firstEvenDigitNumberGreaterThanLower = if (numberOfDigits.mod(2) == 0) {
        newNum.toString()
    } else {
        val strBuilder = StringBuilder()
        strBuilder.append("1")
        (0..<numberOfDigits).forEach { _ -> strBuilder.append("0") }
        strBuilder.toString()
    }

    return firstEvenDigitNumberGreaterThanLower.toLong()
}

println(answer)