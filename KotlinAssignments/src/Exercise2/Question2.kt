package Exercise2

//Write a single program for following operation using overloading
//  A) Adding 2 integer number
//  B) Adding 2 double
//  D) multiplying 2 int
//  E) concate 2 string
//  F) Concate 3 String

class Question2 {

    fun sum(a: Int, b: Int): Int{
        return a+b
    }

    fun sum(a: Float, b: Float): Float{
        return a+b
    }

    fun product(a: Int, b: Int): Int{
        return a*b       
    }
    
    fun concat(a: String, b: String): String{
        return a+b
    }

    fun concat(a: String, b: String, c: String): String{
        return a+b+c
    }
    



}


fun main(args: Array<String>) {
    val obj = Question2()
    println("Sum of 12 & 15: " + obj.sum(12, 15))
    println("Sum of 15.0f & 12.8f: " + obj.sum(15.0f, 12.8f))
    println("Product of 8 & 19: " + obj.product(8, 19))
    println("Concatination of two string hello & world: " + obj.concat("hello", "world"))
    println("Concatination of three strings how, are & you: " + obj.concat("how","are","you"))
}