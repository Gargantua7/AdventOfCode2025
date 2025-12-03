package solution

import inputs

// https://adventofcode.com/2025/day/3
fun main() {
    inputs("Day.3.Lobby").sumOf {

        var maxHeight = 0
        var maxHeightIndex = 0

        for (i in 0..< it.lastIndex) {
            val v = it[i] - '0'
            if (v > maxHeight) {
                maxHeight = v
                maxHeightIndex = i
            }
        }

        var maxLower = 0
        for (i in maxHeightIndex + 1..it.lastIndex) {
            val v = it[i] - '0'
            if (v > maxLower) {
                maxLower = v
            }
        }

        maxHeight * 10 + maxLower

    }.let(::println)
}