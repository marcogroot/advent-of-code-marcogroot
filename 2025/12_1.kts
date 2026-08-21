import java.io.File

val inputs = File("12.txt").readLines()

var size = 0

var firstInstruction = inputs.indexOfFirst { it.contains("x") }

val sizes = mutableListOf<Int>()

inputs.take(firstInstruction).forEach {
    if (it.isBlank()) {
        sizes.add(size)
        size = 0
    }
    else {
        size += it.count { it == '#'}
    }
}

sizes.forEach { println(it) }
println(inputs.take(firstInstruction))
println("--------------")

val a = inputs.drop(firstInstruction).sumOf { instruction ->
    val abc = instruction.split(":")
    val si = abc.first().split("x").map { it.toLong() }
    var area = si.first() * si.last()
    val nums = abc.last().split(" ").filter{it.isNotBlank() }.map { it.toLong() }
    println("The area is $area")
    println("The shapes are $nums")
    nums.forEachIndexed { i, num ->
        val numSize = sizes[i]
        area -= (numSize * num)
    }
    println(area)
    if (area >= 0) 1L else 0L
}
println(a)