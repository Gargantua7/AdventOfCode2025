package solution

import inputs

// https://adventofcode.com/2025/day/1
fun main() {
    var ans = 0
    inputs("Day.1.SecretEntrance").fold(50) { acc, s ->

        val number = s.substring(1).toInt()
        val value = if (s.startsWith("L")) -number else number
        val next = acc + value

        if (next > 100) {
            ans += next / 100
        } else if (next < 0) {
            ans -= (next / 100)
            if (acc > 0) ans++
        } else if (next % 100 == 0) {
            ans++
        }

        (next % 100 + 100) % 100
    }
    println(ans)
}