package Exercise1

//Check letter in string which do not have pair.

fun main(args: Array<String>){

    val arr1 = intArrayOf(2, 3, 4, 2, 3, 4, 5, 6, 7, 5, 6, 7, 1, 9, 1)
    var result = arr1[0]
    for (i in 1 until arr1.size) {
        result = result xor arr1[i]
    }
    println("$result")


}