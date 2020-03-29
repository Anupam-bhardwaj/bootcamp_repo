package Exercise3

//WAP to create singleton class.

object Singleton{

   init {
       println("Singleton Class Called")
   }
    fun printVar(){
        println("function of singleton")
    }
}

fun main(args: Array<String>) {

    val a = A()

}

class A{

    init {
        println("Init Method")
        Singleton.printVar()
    }

}