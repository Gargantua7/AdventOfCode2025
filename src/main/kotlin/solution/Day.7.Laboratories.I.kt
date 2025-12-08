package solution

import inputs

// https://adventofcode.com/2025/day/7
fun main() {
    var ans = 0
    inputs("Day.7.Laboratories").fold("") { prev, line ->
        if (prev.isEmpty()) line
        else buildString(line.length) {
            append(prev)
            line.forEachIndexed { index, c ->
                if (c == '^' && prev[index] == 'S') {
                    ans++
                    this[index - 1] = 'S'
                    this[index] = '.'
                    this[index + 1] = 'S'
                }
            }
        }
    }
    println(ans)
}