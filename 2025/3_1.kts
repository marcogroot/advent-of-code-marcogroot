import java.io.File

val input = File("3.txt").readText()

val answer = input
    .split("\n")
    .sumOf {
        var leftDigit = it[0].toString()

        var ans = (leftDigit+ it[1].toString()).toLong()
        for (i in 1..<it.length) {
            var currDigit = it[i].toString()
            var currVal = (leftDigit+currDigit).toLong()
            ans = ans.coerceAtLeast(currVal)

            if (currDigit.toInt() > leftDigit.toInt()) {
                leftDigit = currDigit
            }
        }

        ans
    }

println(answer)