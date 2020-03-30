package Exercise3

//WAP to create sealed Base class and 3 subclasses of Base class,
// write a function which will have base class object as an argument
// and it will return name of the subclass based argument type.

sealed class Base{

    class A(val a:Int): Base()
    class B(val b: Int): Base()
    class C(val c: Int): Base()

}

fun getClass(o: Base)=
    when(o){

        is Base.A -> println("Subclass A")
        is Base.B -> println("Subclass B")
        is Base.C -> println("Subclass C")
    }

fun main(args: Array<String>) {
    val obja = Base.A(1)
    val objb = Base.B(1)
    val objc = Base.C(1)

    getClass(obja)
    getClass(objb)
    getClass(objc)
    
}
