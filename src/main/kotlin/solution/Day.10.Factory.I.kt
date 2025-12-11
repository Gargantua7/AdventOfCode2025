package solution

import inputs

// https://adventofcode.com/2025/day/10
fun main() {
    inputs("Day.10.Factory").sumOf { line ->

        val items = line.split(' ')

        val target= items.first().removeSurrounding("[", "]").map { if (it == '#') 1 else 0 }.reversed()
        val options = items.subList(1, items.lastIndex).map {
            it.removeSurrounding("(", ")")
                .split(',')
                .map(String::toInt)
                .fold(0) { acc, bit ->
                    acc or (1 shl bit)
                }
        }

        val dp = IntArray(1 shl target.size) { Int.MAX_VALUE }
        dp[0] = 0

        val queue = ArrayDeque<Int>()
        queue.addLast(0)

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            val used = dp[curr]

            options.forEach { option ->
                val next = curr xor option
                if (used + 1 < dp[next]) {
                    dp[next] = used + 1
                    queue.addLast(next)
                }
            }
        }

        dp[target.joinToString("").toInt(2)]
    }.let(::println)
}