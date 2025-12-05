package solution

import inputs

// https://adventofcode.com/2025/day/5
fun main() {
    val input = inputs("Day.5.Cafeteria").toList()
    val space = input.indexOf("")
    val ranges = input.subList(0, space).map {
        it.split("-").let { (a, b) -> a.toLong()..b.toLong() }
    }

    input.subList(space + 1, input.size).count {
        ranges.any { range ->
            it.toLong() in range
        }
    }.let(::println)
}