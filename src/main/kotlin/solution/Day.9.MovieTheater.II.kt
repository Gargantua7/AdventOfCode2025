package solution

import inputs
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://adventofcode.com/2025/day/9
fun main() {
    val points = inputs("Day.9.MovieTheater").map {
        it.split(',').map(String::toLong).let { (x, y) -> x to y }
    }.toList()

    var ans = 0L
    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            val square = (abs(points[i].first - points[j].first) + 1) * (abs(points[i].second - points[j].second) + 1)
            if (square > ans && isValidRectangle(points[i], points[j], points)) {
                ans = square
            }
        }
    }

    println(ans)
}

private typealias Point = Pair<Long, Long>
private val Point.x get() = first
private val Point.y get() = second

private fun isValidRectangle(p1: Point, p2: Point, polygon: List<Point>): Boolean {

    val minX = min(p1.x, p2.x)
    val maxX = max(p1.x, p2.x)
    val minY = min(p1.y, p2.y)
    val maxY = max(p1.y, p2.y)

    val midX = (minX + maxX) / 2
    val midY = (minY + maxY) / 2
    if (!isPointInPolygon(Point(midX, midY), polygon)) {
        return false
    }
    for (i in polygon.indices) {
        val v1 = polygon[i]
        val v2 = polygon[(i + 1) % polygon.size]

        if (v1.x == v2.x) {
            if (v1.x > minX && v1.x < maxX) {
                val edgeMinY = min(v1.y, v2.y)
                val edgeMaxY = max(v1.y, v2.y)
                if (edgeMaxY > minY && edgeMinY < maxY) {
                    return false
                }
            }
        }
        else if (v1.y == v2.y) {
            if (v1.y > minY && v1.y < maxY) {
                val edgeMinX = min(v1.x, v2.x)
                val edgeMaxX = max(v1.x, v2.x)
                if (edgeMaxX > minX && edgeMinX < maxX) {
                    return false
                }
            }
        }
    }

    return true
}

private fun isPointInPolygon(p: Point, polygon: List<Point>): Boolean {
    var inside = false
    var j = polygon.size - 1
    for (i in polygon.indices) {
        if ((polygon[i].y > p.y) != (polygon[j].y > p.y) && (p.x < (polygon[j].x - polygon[i].x) * (p.y - polygon[i].y) / (polygon[j].y - polygon[i].y) + polygon[i].x)) {
            inside = !inside
        }
        j = i
    }
    return inside
}