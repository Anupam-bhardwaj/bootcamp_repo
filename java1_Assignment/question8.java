package IntroToJava1;

//Write a program to reverse a string and remove character from index 4 to index 9 from the reversed string using String Buffer

public class question8 {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("anupam bhardwaj");
        //reverse
        s = s.reverse();
        System.out.println(s);
        s.replace(3,9,"");
        System.out.println(s);

    }
}
