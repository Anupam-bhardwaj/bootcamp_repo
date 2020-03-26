package Exercise2

//Write a function which take marks as an argument and return the the grade as follows:
//marks between 50 to 60 , return “Good”
//marks between 60 to 70, return “Very Good”
//marks between 70 to  80, return “Excellent”
//marks between  80 to 100, return “Rockstar”

fun main(args: Array<String>) {

    var marks: Int = 0
    println("Enter Marks: ")
    marks = Integer.valueOf(readLine())

    getGrade(marks)
}

fun getGrade(marks: Int) {
    when(marks){
        in 80..100 -> println("Rockstar")
        in 70..80 -> println("Excellent")
        in 60..70 -> println("Very Good")
        in 50..60 -> println("Good")
        else -> println("poor")
    }
}
