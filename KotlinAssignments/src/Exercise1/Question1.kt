package Exercise1

//Write a program to replace a substring inside a string with another string.

fun main(args: Array<String>){

    var string1 ="How You"
    var string2 = "trainee"

    print(string1.replace(" ", " "+string2.subSequence(1,2)+ " "))


}