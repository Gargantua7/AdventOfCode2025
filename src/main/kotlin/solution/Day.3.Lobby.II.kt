package solution

import inputs

// https://adventofcode.com/2025/day/3
fun main() {
    inputs("Day.3.Lobby").sumOf { s ->

        var res = 0L
        var prevIndex = -1

        repeat(12) {

            var max = -1
            var maxIndex = -1

            for (i in (prevIndex + 1)..(s.length - 12 + it)) {
                val v = s[i] - '0'
                if (v > max) {
                    max = v
                    maxIndex = i
                }
            }

            prevIndex = maxIndex

            res = res * 10 + max
        }

        res

    }.let(::println)
}