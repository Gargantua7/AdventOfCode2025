package solution

import inputs
import com.microsoft.z3.Context
import com.microsoft.z3.Status

// https://adventofcode.com/2025/day/10
fun main() {
    inputs("Day.10.Factory").sumOf { line ->

        val items = line.split(' ')

        val target= items.last().removeSurrounding("{", "}")
            .split(',')
            .map(String::toInt)
        val options = items.subList(1, items.lastIndex).map {
            it.removeSurrounding("(", ")")
                .split(',')
                .map(String::toInt)
        }

        Context().use { ctx ->
            val opt = ctx.mkOptimize()
            val vars = options.indices.map { ctx.mkIntConst("b$it") }
            vars.forEach { opt.Add(ctx.mkGe(it, ctx.mkInt(0))) }
            target.indices.forEach { i ->
                val terms = options.withIndex().filter { i in it.value }.map { vars[it.index] }
                if (terms.isNotEmpty()) {
                    val sum = if (terms.size == 1) terms[0]
                    else ctx.mkAdd(*terms.toTypedArray())
                    opt.Add(ctx.mkEq(sum, ctx.mkInt(target[i])))
                } else if (target[i] != 0) error("")
            }
            opt.MkMinimize(ctx.mkAdd(*vars.toTypedArray()))
            if (opt.Check() == Status.SATISFIABLE) {
                vars.sumOf { opt.model.evaluate(it, false).toString().toInt() }
            } else 0
        }

    }.let(::println)
}

