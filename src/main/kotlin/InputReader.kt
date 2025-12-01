fun inputs(name: String, sample: Boolean = false) =
    ClassLoader.getSystemClassLoader()
        .getResourceAsStream("inputs/$name${if (sample) ".sample" else ""}.txt")!!
        .bufferedReader().lineSequence()