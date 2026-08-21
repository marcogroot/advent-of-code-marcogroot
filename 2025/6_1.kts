import java.io.File

val input = File("6.txt").readLines()
val nums = input.dropLast(1).map { numsString ->
    numsString.split(Regex("\\s+")).mapNotNull {
        if (it.isBlank()) null else it.toLong()
    }
}

val shouldAddOperations = input.takeLast(1).first().split(Regex("\\s+")).map { operation ->
    operation == "+"
}

var answer = 0L

for (i in nums.first().indices) {
    val shouldAdd = shouldAddOperations[i]
    var total = if (shouldAdd) 0L else 1L

    for (j in nums.indices) {
        val num = nums[j][i]
        if (shouldAdd) {
            total += num
        } else {
            total *= num
        }
    }
    answer += total
}

println(answer)
