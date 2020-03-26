package Exercise3

class Question4 (val radius: Float){

    fun area(): Double {
        return 3.14*radius*radius
    }
}

fun main(args: Array<String>) {
    fun Question4.perimeter(): Double{
        return 2*3.14*radius
    }

    var question4 = Question4(5.0F)
    println("Area of circle with radius = 5 is ${question4.area()}")

    println("Perimeter of circle with radius = 5 is ${question4.perimeter()}")

}