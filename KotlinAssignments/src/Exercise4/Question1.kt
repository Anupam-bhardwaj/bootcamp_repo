package Exercise4

//WAP using Lambda function to calculate the Simple Interest.

fun main(args: Array<String>) {

    var principle: Double
    var rate: Float
    var time: Int

//  Lambda Function to calculate Simple Interest.
    val interest = {p: Double, r: Float, t: Int -> (p*r*t)/100}

    println("Enter Principle, Rate & Time for the Interest: ")
    println("Principle: ")
    principle = Integer.valueOf(readLine()).toDouble()
    println("Rate: ")
    rate = Integer.valueOf(readLine()).toFloat()
    println("Time: ")
    time = Integer.valueOf(readLine())

//  Lambda Function Called.
    println("Simple Interest: ${interest(principle,rate, time)}")

}