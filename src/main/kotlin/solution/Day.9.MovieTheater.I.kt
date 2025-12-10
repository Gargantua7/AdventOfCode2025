package solution

import inputs
import kotlin.math.abs
import kotlin.math.max

// https://adventofcode.com/2025/day/9
fun main() {
    val points = inputs("Day.9.MovieTheater").map {
        it.split(',').map(String::toLong).let { (x, y) -> x to y }
    }.toList()

    var ans = 0L
    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            ans = max(ans, (abs(points[i].first - points[j].first) + 1) * (abs(points[i].second - points[j].second) + 1))
        }
    }

    println(ans)

}