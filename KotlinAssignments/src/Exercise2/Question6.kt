package Exercise2

import java.util.ArrayList

//Write a program to create mutable list of Integer. replace the second item in the list with new value.
// Print the list value.

fun main(args: Array<String>) {

    var list: ArrayList<Int> = ArrayList(20)
    for (i in 1..20){
        list.add(i)
    }

    println("Array List Before replacement")
    println(list)

    list.add(1,21)
    println("Array List After replacement")
    println(list)


}