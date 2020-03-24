package Exercise1

//Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String.

fun main(argd: Array<String>) {


    var uCount = 0
    var lCount = 0
    var digitCount = 0
    var specialCount = 0

    val str = "HHHHHH World !! 122244"

    for (i in 0 until str.length) {
        val ch = str[i]
        if (Character.isUpperCase(ch)) {
            uCount++
        } else if (Character.isLowerCase(ch)) {
            lCount++
        } else if (Character.isDigit(ch)) {
            digitCount++
        } else {
            specialCount++
        }
    }

    val length = str.length.toDouble()
    val upperPercent = uCount / length * 100
    val lowerPercent = lCount / length * 100
    val otherPercent = digitCount / length * 100
    val specialPercent = specialCount / length * 100

    println("upperCase count = $uCount");
    println("lowerCase count = $lCount");
    println("other character count = $digitCount");
    println("special character count = $specialCount");
    println("upperCase percentage = $upperPercent");
    println("lowerCase percentage = $lowerPercent");
    println("other percentage = $otherPercent");
    println("special percentage = $specialPercent");

}
//    {  var range = 0.rangeTo(str.length)
//
//        for (i in range){
//            var ch: Array<Char> = str[i]
//            if (ch.isLowerCase()){
//                lCount++
//            }else if (ch.isUpperCase()){
//                uCount++
//            }else if (ch.isDigit()){
//                digitCount++
//            }else{
//                specialCount++
//            }
//        }}
