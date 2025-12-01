package solution

import inputs

// https://adventofcode.com/2025/day/1
fun main() {
    inputs("Day.1.SecretEntrance").runningFold(50) { acc, s ->

        val number = s.substring(1).toInt()
        val value = if (s.startsWith("L")) -number else number

        ((acc + value) % 100 + 100) % 100
    }.count { it == 0 }.let(::println)
}