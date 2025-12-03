package solution

import inputs

// https://adventofcode.com/2025/day/2
fun main() {
    inputs("Day.2.GiftShop").first().split(",")
        .map {
            it.split("-")
                .map(String::toLong)
        }.sumOf { (s, e) ->
            (s..e).sumOf { n ->
                val s = n.toString()
                if (s.length % 2 == 0 && s.take(s.length / 2) == s.takeLast(s.length / 2)) {
                    n
                } else 0
            }
        }.let(::println)
}
