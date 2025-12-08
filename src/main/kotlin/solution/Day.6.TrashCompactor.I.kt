package solution

import inputs

// https://adventofcode.com/2025/day/6
fun main() {
    val list = ArrayList<ArrayList<Long>>()
    inputs("Day.6.TrashCompactor").forEach { line ->
        line.split(" ").filter { it.isNotBlank() }
            .forEachIndexed { index, value ->
                value.toLongOrNull()?.let { number ->
                    if (index > list.lastIndex) {
                        list.add(ArrayList())
                    }
                    list[index].add(number)
                } ?: run {
                    val res = list[index].fold(
                        if (value == "*") 1L else 0L,
                        if (value == "*") Long::times else Long::plus
                    )

                    list[index].add(res)
                }
            }
    }

    println(list.sumOf { it.last() })
}