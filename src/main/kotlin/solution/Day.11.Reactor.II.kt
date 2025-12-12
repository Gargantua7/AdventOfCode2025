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

    val route = HashSet<String>()
    val cache = HashMap<String, Long>()

    fun dfs(curr: String): Long {
        val key = curr + route.joinToString("")

        if (key in cache) return cache[key]!!
        if (curr == "out") return if (route.size == 2) 1L else 0L

        if (curr == "dac" || curr == "fft") route.add(curr)
        cache[key] = edges[curr]?.sumOf(::dfs) ?: 0L
        route.remove(curr)
        return cache[key]!!
    }

    dfs("svr")

    println(cache["svr"] ?: 0)
}