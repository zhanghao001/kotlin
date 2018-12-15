package base

/**
 * @author hao_zhang3
 * @date 2018-12-15
 */
fun main(args: Array<String>) {
    val str = "12.345-6.A"
    println(str.split(".", "-"))
    println(str.split("[.\\-]".toRegex()))

    val str2 = "/User/hao/kotlin-bool/chapter.doc"
    parsePath(str2)
}

val kotlinLogo = """| //
                   .|//
                   .|/ \""".trimMargin(".")

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
    println(kotlinLogo)
}