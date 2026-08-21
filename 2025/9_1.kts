import java.io.File
import kotlin.math.abs
import kotlin.math.sqrt

val input = File("9.txt").readLines()
    .map {
        val nums = it.split(",")
        Pair(nums.first().toLong(), nums.last().toLong())
    }


var answer = 0L

input.forEachIndexed { i, p1 ->
    input.forEachIndexed { j, p2 ->
        if (i != j) {
            val h = abs(p1.first - p2.first) + 1
            val w = abs(p1.second - p2.second) + 1
            answer = answer.coerceAtLeast(w * h)
        }
    }
}

println(answer)

