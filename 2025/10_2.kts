import org.ojalgo.optimisation.ExpressionsBasedModel
import java.io.File
import kotlin.math.max

data class Switch(
    val goalState: List<Boolean>,
    var switches: List<List<Long>>,
    var energy: List<Long>,
) {
    fun getMinPresses() : Int {
        return dfs(0, listOf())
    }

    fun dfs(i: Int, sw: List<List<Long>>) : Int {
        if (i == switches.size) {
            val start = goalState.map { false }.toMutableList()
            sw.forEach { switch ->
                switch.forEach { s ->
                    start[s.toInt()] = !start[s.toInt()]
                }
            }
            if (start == goalState) return sw.size
            return Int.MAX_VALUE
        }
        return minOf(
            dfs(i+1, sw + listOf(switches[i])),
            dfs(i+1, sw)
        )
    }
}
fun parseSwitches(str: String, list: List<List<Long>>): List<List<Long>> {
    val l = str.indexOfFirst { it == '(' }
    if (l == -1) return list
    val r = str.indexOfFirst { it == ')' }
    val switchesString = str.substring(startIndex = l + 1, endIndex = r).split(",").map { it.toLong() }
    val newList = list + listOf(switchesString)
    return parseSwitches(str.substring(startIndex = r + 1), newList)
}

fun main() {
    val input = File("src/10.txt").readLines().map { line ->
        val lSquareIndex = line.indexOfFirst { it == '[' }
        val rSquareIndex = line.indexOfFirst { it == ']' }

        val statesString = line.substring(startIndex = lSquareIndex + 1, endIndex = rSquareIndex)
        val states = statesString.map {
            it == '#'
        }

        val lCurlyIndex = line.indexOfFirst { it == '{' }
        val switchesString = line.substring(startIndex = rSquareIndex + 1, endIndex = lCurlyIndex - 1)
        val switches = parseSwitches(switchesString, listOf())

        val rCurlyIndex = line.indexOfFirst { it == '}' }
        val energies =
            line.substring(startIndex = lCurlyIndex + 1, endIndex = rCurlyIndex).split(",").map { it.toLong() }

        Switch(
            goalState = states,
            switches = switches,
            energy = energies
        )
    }

    val answer = input.sumOf {
        val model = ExpressionsBasedModel()
        val maxValue = it.energy.max()
        val ks = Array(it.switches.size) { k ->
            model.newVariable("button_$k").integer().lower(0).upper(maxValue)
        }

        for (counterIndex in it.energy.indices) {
            it.switches
                .mapIndexedNotNull { i, counters -> if (counters.contains(counterIndex.toLong())) i else null }
                .map { ks[it] }
                .toTypedArray()
                .let { abc ->
                    val addExpression = model.addExpression().level(it.energy[counterIndex])
                    for (variable in abc) addExpression[variable] = 1.0
                }
        }
        val total = model.addExpression("total")
            .weight(1.0)
        ks.forEach { total[it] = 1.0 }
        Math.round(model.minimise().value).toInt()
    }

    println(answer)
}