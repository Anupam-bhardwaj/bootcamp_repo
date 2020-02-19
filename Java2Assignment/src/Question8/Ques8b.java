package Question8;

//WAP to read words from the keyboard until the word done is entered. For each word except done, report whether its first character is equal   to  its last character. For the required loop, use a
//b)do-while statement.

import java.util.Scanner;

public class Ques8b {
        public static void main(String[] args) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("enter word");
            String word = s1.nextLine();
            do{

                if (word.charAt(0) == word.charAt(word.length()-1)){
                    System.out.println("First char = Last char");
                }
                else if(word.equals("done")){
                    break;

                }
                else{

                    System.out.println("first not equal to last");
                }

                word =s1.nextLine();
            }while(!word.equals("done"));

        }
}

