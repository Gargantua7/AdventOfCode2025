package solution

import inputs

// https://adventofcode.com/2025/day/4
fun main() {
    val matrix = inputs("Day.4.PrintingDepartment").toList()
    val direction = arrayOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1, 0 to 1,
        1 to -1, 1 to 0, 1 to 1
    )

    var ans = 0
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            if (matrix[row][col] == '@') {
                val count = direction.count { (dx, dy) ->
                    matrix.getOrNull(row + dx)
                        ?.getOrNull(col + dy) == '@'
                }

                if (count < 4) ans++
            }
        }
    }

    println(ans)
}