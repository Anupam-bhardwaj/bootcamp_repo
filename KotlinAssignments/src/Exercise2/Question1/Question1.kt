package Exercise2.Question1

//Write a program to print your Firstname,LastName & age using init block,companion object.

class Question1 {

    init {
        
        println("Init block called")
        println("anupam")
        println("bhardwaj")
        println(20)
        
    }



}
fun main(args: Array<String>) {

    var obj = Question1()
    println("Companion Object Called")
    println(Question1Companion.fname)
    println(Question1Companion.lname)
    println(Question1Companion.age)

}
