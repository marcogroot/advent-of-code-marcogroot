import java.io.File

val input = File("3.txt").readText()

val answer = input
    .split("\n")
    .sumOf { battery ->
        val batteryIndexes = battery.indices.reversed().take(12).reversed().toMutableList()

        var frontCutoff = -1

        println(battery)
        batteryIndexes.forEachIndexed { i, batteryIndex ->
            val batteryValue = battery[batteryIndex]

            var currentBestIndex = batteryIndex
            var currentBestValue = batteryValue

            for (j in batteryIndex-1 downTo 0) {
                val curr = battery[j]
                if (curr >= currentBestValue) {
                    if (j > frontCutoff) {
                        currentBestIndex = j
                        currentBestValue = curr
                    }
                }
            }

            frontCutoff = currentBestIndex
            batteryIndexes[i] = currentBestIndex
        }
        batteryIndexes.map { i -> battery[i] }.joinToString("").toLong()
    }

println(answer)