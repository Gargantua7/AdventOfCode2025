package solution

import inputs

// https://adventofcode.com/2025/day/6
fun main() {
    val input = inputs("Day.6.TrashCompactor").toList()
    val func = input.last()
    val maxLength = input.maxOf { it.length }

    val fIndex = ArrayList<Int>()
    func.forEachIndexed { index, c ->
        if (c != ' ') fIndex.add(index)
    }

    var ans = 0L

    fIndex.forEachIndexed { index, start ->
        val end = (fIndex.getOrNull(index + 1) ?: (maxLength + 1)) - 1
        val numbers = LongArray(end - start)

        for (r in 0..<input.lastIndex) {
            val line = input[r]
            for (i in start..<end) {
                val offset = i - start
                val char = line.getOrNull(i) ?: continue
                if (char != ' ') {
                    numbers[offset] *= 10
                    numbers[offset] += char - '0'
                }
            }
        }

        val function = func[fIndex[index]]
        ans += numbers.fold(
            if (function == '*') 1L else 0L,
            if (function == '*') Long::times else Long::plus
        )
    }

    println(ans)
}