package Exercise1

//Write a program to find the number of occurrences of the duplicate words in a string and print them.

fun main(args: Array<String>){

    var string = "I scream, you scream, we all scream for ice cream"
    var duplicate: Int

    string = string.toLowerCase()
    val word = string.split(" ".toRegex()).toTypedArray()
//    for (i in 0 until word.size){
//    println(word[i])}
//
//    println("Duplicate word: ")

    for (i in 0 until word.size) {
        duplicate = 1;
        for (j in i+1 until word.size) {
            if (word[i].equals(word[j])) {
                duplicate++;
                word.set(j, "0");
        }
    }
        if (duplicate > 1 && word[i] != "0")
            println(word[i]);
    }
}

//{
//    var string1 = "I scream, you scream, we all scream for ice cream"
//    var duplicate: Int
//    var j = 1
//
//    string1.toLowerCase()
//    var word: Array<String>
//    word = string1.split(" ").toTypedArray()
//    println("Duplicate Word")
//
//    for (i in word){
//        duplicate = 1
//        for (j in word){
//            if (i.equals(j)){
//                duplicate++
//                word[word.indexOf(j)] = "0"
//
//            }
//        }
//        if (duplicate>1 && i !="0")
//            println(i)
//    }
//}