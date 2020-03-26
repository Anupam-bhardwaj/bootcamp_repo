package Exercise1

//Check letter in string which do not have pair.

fun main(args: Array<String>){

    var string = "heeooyuyu"
    var duplicate: Int
//    var occurence = 1

    string = string.toLowerCase()
    val word = string.split("".toRegex()).toTypedArray()

    duplicate = 1
    for (i in 0 until word.size) {

        for (j in i+1 until word.size) {
            if (word[i].equals(word[j])) {
                duplicate++;
                word[j] = "0"
//                occurence += duplicate
            }
        }
        if (duplicate == 1 && word[i] != "0"){

            println(word[i])
        }
    }
    if (duplicate != 1){
        println("No Element without a pair exists.")
    }
}