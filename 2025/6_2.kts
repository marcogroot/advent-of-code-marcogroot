import java.io.File

val input = File("6.txt").readLines()
val numStringsWithPlaceholders = input.dropLast(1).map { numString ->
    numString.replace(" ", "*")
}

val shouldAddOperations = input.takeLast(1).first().split(Regex("\\s+")).map { operation ->
    operation == "+"
}

val numberRanges = mutableListOf<Pair<Int, Int>>()

var operationIndexes = mutableListOf<Int>()
input.takeLast(1).first().forEachIndexed { i, it ->
    if (it != ' ') operationIndexes.add(i)
}

////println(operationIndexes)
operationIndexes.forEachIndexed { i, it ->
   if (i+1 < operationIndexes.size) {
       numberRanges.add(Pair(it, operationIndexes[i+1]-it-1))
   }
}
numberRanges.add(Pair(operationIndexes.last(), numStringsWithPlaceholders.first().length))

////println(numberRanges)

val nums = mutableListOf<List<String>>()
numStringsWithPlaceholders.forEach {
    val numsRow = numberRanges.map { range ->
        it.drop(range.first).take(range.second)
    }
    nums.add(numsRow)
}


var answer = 0L

for (i in nums.first().indices) {
    val shouldAdd = shouldAddOperations[i]

    val numbers = mutableListOf<String>()
    for (j in nums.indices) {
        numbers.add(nums[j][i])
    }

    val longest = numbers.maxBy { it.length }

    val newNumbers = mutableListOf<Long>()

    for (c in 0..<longest.length) {
        val builder = StringBuilder()
        for (r in numbers.indices) {
            var currentNumber = numbers[r].reversed()
            if (currentNumber.length > c && currentNumber[c] != '*') {
                builder.append(currentNumber[c])
            }

        }
        val abc = builder.toString().toLong()
        //print("$abc ")
        newNumbers.add(abc)
    }


    var total = if (shouldAdd) 0L else 1L
    newNumbers.forEach { newNum ->
        if (shouldAdd) {
            total += newNum
        } else {
            total *= newNum
        }
    }
//    if (shouldAdd) {
//        println("ADD Total: $total")
//    } else {
//        println("TIMES Total: $total")
//    }


    answer += total
}

println(answer)
