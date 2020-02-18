package IntroToJava1;

//Write a program to find the number of occurrences of a character in a string without using loop?

public class question3 {
    public static void main(String[] args) {
        String word = "Anupam Bhardwaj";

        String i = "a";
        System.out.println(word.length() - word.replace("a", "").length());
    }

}
