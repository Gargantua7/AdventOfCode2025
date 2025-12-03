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

                judge@ for (len in 1..s.length / 2) {
                    if (s.length % len == 0) {
                        for (i in len..s.lastIndex) {
                            if (s[i] != s[i - len]) continue@judge
                        }
                        return@sumOf n
                    }
                }

                0L
            }
        }.let(::println)
}
