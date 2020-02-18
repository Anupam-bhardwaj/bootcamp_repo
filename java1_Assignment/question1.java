package IntroToJava1;

//Write a program to replace a substring inside a string with other string ?

public class question1 {
    public static void main(String[] args) {
        String s1 = "How You";
        String s2 = "Trainee";
        System.out.println(s1.replace(" ", " " + s2.substring(1, 2) + " ") );
    }


}
