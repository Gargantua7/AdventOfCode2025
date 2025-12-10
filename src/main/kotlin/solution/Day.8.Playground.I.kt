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


    val edgeList = ArrayList<Triple<Int, Int, Double>>()
    for (i in points.indices) {
        for (j in (i + 1)..points.lastIndex) {
            val (ax, ay, az) = points[i]
            val (bx, by, bz) = points[j]
            val distance = sqrt((ax - bx).pow(2) + (ay - by).pow(2) + (az - bz).pow(2))

            edgeList.add(Triple(i, j, distance))
        }
    }

    val edges = HashMap<Int, ArrayList<Int>>()
    edgeList.sortedBy { it.third }.take(1000).forEach { (a, b) ->
        edges.getOrPut(a, ::ArrayList).add(b)
        edges.getOrPut(b, ::ArrayList).add(a)
    }

    val found = HashSet<Int>()
    val group = ArrayList<HashSet<Int>>()
    edges.forEach { (p, _) ->
        if (p !in found) {
            val nodeQueue = ArrayDeque<Int>()
            val set = HashSet<Int>()

            nodeQueue.addLast(p)
            set.add(p)

            while (nodeQueue.isNotEmpty()) {
                val node = nodeQueue.removeFirst()
                edges[node]?.forEach {
                    if (it !in set) {
                        nodeQueue.addLast(it)
                        set.add(it)
                    }
                }
            }

            found.addAll(set)
            group.add(set)
        }
    }

    group.map { it.size }.sortedDescending().take(3).fold(1L, Long::times)
        .let(::println)

}