package solution

import inputs

// https://adventofcode.com/2025/day/11
fun main() {
    val edges = inputs("Day.11.Reactor").associate { line ->
        val arr = line.split(": ")
        val node = arr[0]
        val edges = arr[1].split(" ")
        node to edges
    }

    var ans = 0
    val stack = ArrayDeque<String>()
    stack.addLast("you")

    while (stack.isNotEmpty()) {
        val curr = stack.removeLast()

        if (curr == "out") {
            ans++
            continue
        }

        edges[curr]?.forEach(stack::addLast)
    }

    println(ans)
}