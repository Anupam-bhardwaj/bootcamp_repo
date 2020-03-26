package Exercise3

object Singleton{

   init {
       println("Singleton Class Called")
   }
    fun printVar(){
        println("function of singleton")
    }
}

fun main(args: Array<String>) {

    var a = A()

}

class A{

    init {
        println("Init Method")
        Singleton.printVar()
    }

}