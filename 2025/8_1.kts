import java.io.File
import kotlin.math.sqrt

data class Node(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun getHash(): String = "$x:$y:$z"

    fun combinedHash(otherNode: Node) : String {
        val nodes = listOf(this, otherNode).sortedBy { it.getHash() }
        return "${nodes.first().getHash()}:${nodes.last().getHash()}"
    }

    fun distanceFrom(otherNode: Node): Double {
        val xd: Long = (this.x - otherNode.x).toLong() * (this.x - otherNode.x).toLong()
        val yd: Long = (this.y - otherNode.y).toLong() * (this.y - otherNode.y).toLong()
        val zd: Long = (this.z - otherNode.z).toLong() * (this.z - otherNode.z).toLong()

        return sqrt(xd.toDouble() + yd.toDouble() + zd.toDouble())
    }
}

data class Obj(
    val a: Node,
    val b: Node,
    val distance: Double,
)

val input = File("8.txt").readLines()

val nodes = input.map {
    val nodeStrings = it.split(",")
    Node(nodeStrings[0].toInt(), nodeStrings[1].toInt(), nodeStrings[2].toInt())
}

val connections = mutableMapOf<String, Int>()
val ropes = mutableMapOf<Int, MutableList<String>>()

val objs = mutableSetOf<Obj>()
val seen = mutableSetOf<String>()

nodes.forEachIndexed { i, n1 ->
    nodes.forEachIndexed { j, n2 ->
        val combinedHash = n1.combinedHash(n2)
        if (i != j && !seen.contains(combinedHash)) {
            seen.add(combinedHash)
            val newObj = Obj(n1, n2, n1.distanceFrom(n2))
            objs.add(newObj)
        }
    }
}

var currentKey = 0

objs.sortedBy { it.distance }.take(1000).forEach {
    val a = it.a.getHash()
    val b = it.b.getHash()

    val aKey = connections[a]
    val bKey = connections[b]

    // if both connected, make b all a part of a
    if (aKey != null && bKey != null) {
        if (aKey != bKey) {
            val bNodes = ropes[bKey]!!
            bNodes.forEach {
                connections[it] = aKey
            }
            ropes[aKey]!!.addAll(bNodes)
            ropes.remove(bKey)
        }
    }
    // if one has a connection, add to that rope
    else if (aKey != null) {
        ropes[aKey]!!.add(b)
        connections[b] = aKey
    } else if (bKey != null) {
        ropes[bKey]!!.add(a)
        connections[a] = bKey
    }
    // else create a new rope
    else {
        connections[a] = currentKey
        connections[b] = currentKey
        ropes[currentKey] = listOf(a, b).toMutableList()
        currentKey++
    }
}

val answer: Long = ropes.values.sortedByDescending { it.size }.take(3).fold(1L) { acc, s -> acc * s.size }

println(answer)

