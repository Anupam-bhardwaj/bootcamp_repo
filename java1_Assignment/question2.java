package IntroToJava1;

//Write a program to find the number of occurrences of the duplicate words in a string and print them ?

public class question2 {
    public static void main(String[] args) {
        String string = "I scream, you scream, we all scream for ice cream";
        int duplicate;

        string = string.toLowerCase();
        String word[] = string.split(" ");

        System.out.println("Duplicate word: ");
        for (int i = 0; i < word.length; i++) {
            duplicate = 1;
            for (int j = i + 1; j < word.length; j++) {
                if (word[i].equals(word[j])) {
                    duplicate++;
                    word[j] = "0";
                }
            }
            if (duplicate > 1 && word[i] != "0")
                System.out.println(word[i]);
        }
    }
}