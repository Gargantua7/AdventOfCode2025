package solution

import inputs
import kotlin.math.pow
import kotlin.math.sqrt

// https://adventofcode.com/2025/day/8
fun main() {
    val points = inputs("Day.8.Playground").map {
        it.split(",")
            .map(String::toDouble)
            .let { (x, y, z) -> Triple(x, y, z) }
    }.toList()


    val edgeList = buildList {
        for (i in points.indices) {
            for (j in (i + 1)..points.lastIndex) {
                val (ax, ay, az) = points[i]
                val (bx, by, bz) = points[j]
                val distance = sqrt((ax - bx).pow(2) + (ay - by).pow(2) + (az - bz).pow(2))

                add(Triple(i, j, distance))
            }
        }
    }.sortedBy { it.third }

    val edges = HashMap<Int, ArrayList<Int>>()
    val found = HashSet<Int>()
    found.add(0)

    edgeList.forEachIndexed { index, (a, b) ->

        edges.getOrPut(a, ::ArrayList).add(b)
        edges.getOrPut(b, ::ArrayList).add(a)

        val queue = ArrayDeque<Int>()
        if (a in found) queue.addLast(a)
        if (b in found) queue.addLast(b)

        while (queue.isNotEmpty() && found.size < points.size) {
            val node = queue.removeFirst()
            edges[node]?.forEach { next ->
                if (next !in found) {
                    found.add(next)
                    queue.addLast(next)
                }
            }
        }

        if (found.size == points.size) {
            println(points[a].first * points[b].first)
            return
        }
    }
}