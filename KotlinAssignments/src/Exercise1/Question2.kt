package Exercise1

//Write a program to find the number of occurrences of the duplicate words in a string and print them.

fun main(args: Array<String>) {

    var string = "I scream, you scream, we all scream for ice cream"
    var duplicate: Int
    var occurence = 1

    string = string.toLowerCase()
    val word = string.split("".toRegex()).toTypedArray()



    for (i in 0 until word.size) {
        duplicate = 1
        for (j in i+1 until word.size) {
            if (word[i].equals(word[j])) {
                duplicate++;
                word[j] = "0"
                occurence += duplicate
        }
    }
        if (duplicate > 1 && word[i] != "0")

            println(word[i] + " Occurences: $occurence")
    }
}
