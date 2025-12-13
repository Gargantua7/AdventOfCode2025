package solution

import inputs

// https://adventofcode.com/2025/day/12
fun main() {
    inputs("Day.12.ChristmasTreeFarm").mapNotNull { line ->
        if (line.isNotEmpty()) {
            val split = line.split(": ")
            if (split.size == 2) {
                val (w, h) = split[0].split('x').map(String::toInt)
                val need = split[1].split(" ").sumOf(String::toInt)

                (w to h) to need
            } else null
        } else null
    }.count { (area, need) ->
        val (w, h) = area
        val ws = w / 3
        val hs = h / 3
        ws * hs >= need
    }.let(::println)
}