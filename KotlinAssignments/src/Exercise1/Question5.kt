package Exercise1

//Find common elements between two arrays.

fun main(args: Array<String>){

    val arr1 = intArrayOf(1, 2, 3, 4, 5)
    val arr2 = intArrayOf(3, 4, 5, 6, 7, 8)
    for (i in 0 until arr1.size) {
        for (j in 0 until arr2.size) {
            if (arr1[i] === arr2[j]) {
                println(arr1[i])
            }
        }
    }
}