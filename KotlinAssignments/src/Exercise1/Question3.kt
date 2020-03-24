package Exercise1

//Write a program to find the number of occurrences of a character in a string without using loop.


fun main(args: Array<String>){

    var str = "Yellowwooddoor"
    val character = "o"

    val num = (str.length - (str.replace(character, "")).length)

    print("$num")

}