import java.io.File

fun inputs(name: String, sample: Boolean = false) =
    object {}.javaClass.classLoader
        .getResourceAsStream("inputs/$name${if (sample) ".sample" else ""}.txt")!!
        .bufferedReader().lineSequence()