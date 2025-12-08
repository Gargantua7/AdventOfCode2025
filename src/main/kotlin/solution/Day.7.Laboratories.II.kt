package solution

import inputs

// https://adventofcode.com/2025/day/7
fun main() {
    inputs("Day.7.Laboratories").fold(LongArray(0)) { prev, line ->
        if (prev.isEmpty()) LongArray(line.length) { if (line[it] == 'S') 1L else 0L }
        else LongArray(line.length) { index ->
            var count = if (line[index] != '^') prev[index] else 0L
            if (line.getOrNull(index - 1) == '^') {
                count += prev[index - 1]
            }
            if (line.getOrNull(index + 1) == '^') {
                count += prev[index + 1]
            }
            count
        }
    }.sum().let(::println)
}