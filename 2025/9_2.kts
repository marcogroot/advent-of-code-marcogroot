import java.io.File
import java.lang.Math.abs
import kotlin.math.absoluteValue

val nums = File("9.txt").readLines().map { it.split(",") }.map { Pair(it[1].toInt(), it[0].toInt()) }


val tnums = nums + nums.first()

enum class DIRECTION(val dir: Pair<Int, Int>) {
	UP(Pair(0, 1)),
	DOWN(Pair(0, -1)),
	LEFT(Pair(1, 0)),
	RIGHT(Pair(-1, 0))
}

val fart = mutableSetOf<Pair<Int, Int>>()
val rim = mutableSetOf<Pair<Int, Int>>()

for ((a, b) in tnums.zipWithNext()) {
	if (a.first != b.first) {
		val coordos = if (a.first < b.first) (a.first..b.first) else (b.first..a.first)
		coordos.forEach { c ->
			fart.add(c to a.second)
		}
	}
	else {
		val coordos = if (a.second < b.second) (a.second..b.second) else (b.second..a.second)
		coordos.forEach { c ->
			fart.add(a.first to c)
		}
	}
}

for ((a, b) in tnums.zipWithNext()) {
	var dir = getDirection(a, b)
	if (a.first != b.first) {
		val coordos = if (a.first < b.first) (a.first..b.first) else (b.first..a.first)
		coordos.forEach { c ->
			val abc = c+dir.dir.first to a.second+dir.dir.second
			if (!fart.contains(abc)) {
				rim.add(abc)
			}
		}
	}
	else {
		val coordos = if (a.second < b.second) (a.second..b.second) else (b.second..a.second)
		coordos.forEach { c ->
			val abc = a.first+dir.dir.first to c+dir.dir.second
			if (!fart.contains(abc)) {
				rim.add(abc)
			}
		}
	}

//	(0..20).forEach { i ->
//		(0..20).forEach { j ->
//			if (rim.contains(Pair(i, j))) print("$")
//			else if (nums.contains(Pair(i, j))) print("#")
//			else print(".")
//		}
//		println()
//	}
}


fun getDirection(a: Pair<Int, Int>, b: Pair<Int, Int>): DIRECTION =
	if (b.first > a.first) {
		DIRECTION.UP
	} else if (b.first < a.first) {
		DIRECTION.DOWN
	} else if (
		b.second > a.second) {
		DIRECTION.RIGHT
	} else {
		DIRECTION.LEFT
	}

var bestArea = 0

nums.forEachIndexed { i, a ->
	nums.forEachIndexed { j, b ->
		if (i != j) {
			val height = (a.first - b.first).absoluteValue + 1
			val width = (a.second - b.second).absoluteValue + 1
			val potentialArea = height * width
			if ((potentialArea > bestArea) && (validArea(a, b))) {
				bestArea = potentialArea
			}
		}
	}
}

fun validArea(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
	val rows = listOf(a.first, b.first).sorted()
	val cols = listOf(a.second, b.second).sorted()

	(rows.first()..rows.last()).forEach { r ->
		if (rim.contains(r to cols.first())) return false;
		if (rim.contains(r to cols.last())) return false;
	}
	(cols.first()..cols.last()).forEach { c ->
		if (rim.contains(rows.first() to c)) return false;
		if (rim.contains(rows.last() to c)) return false;
	}
	return true
}

println(bestArea)

