package Exercise3

//WAP to produce NoClassDefFoundError and ClassNotFoundException exception.

fun main(args: Array<String>) {

    try {
        Class.forName("Anupam1")
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }

}