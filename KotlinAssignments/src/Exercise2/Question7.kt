package Exercise2

//Write a program to create mutable map. print all the value and key of map.

fun main(args: Array<String>) {
    var map: MutableMap<Int, String> = mutableMapOf<Int, String>(1 to "Ashutosh", 4 to "Lakshya", 2 to "Anupam",
        3 to "Subarno", 5 to "Tanvi")

    println("Map:")
    for (key in map.keys)
        println("Keys: $key, Value: ${map[key]}")
}