package Question3;

//WAP to produce NoClassDefFoundError and ClassNotFoundException exception.

public class Ques3a {

    public static void main(String[] args) {

        try {
            Class.forName("Anupam1");

        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
