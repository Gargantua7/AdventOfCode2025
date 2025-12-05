package solution

import inputs
import kotlin.math.max

// https://adventofcode.com/2025/day/5
fun main() {
    val input = inputs("Day.5.Cafeteria").toList()
    val ranges = input.subList(0, input.indexOf("")).map {
        it.split("-").let { (a, b) -> a.toLong() to b.toLong() }
    }.sortedWith { (s1, e1), (s2, e2) ->
        val r = if (s1 == s2) e1 - e2 else s1 - s2
        when {
            r == 0L -> 0
            r < 0L -> -1
            else -> 1
        }
    }

    var count = 0L
    var max = 0L
    ranges.forEach { (start, end) ->
        val s = max(start - 1, max)
        count += (end - s).coerceAtLeast(0L)
        max = max(end, max)
    }

    println(count)
}