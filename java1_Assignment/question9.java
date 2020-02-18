package IntroToJava1;

//Write a program to display values of enums using a constructor & getPrice() method (Example display house & their prices)

enum Level{
    BEGINNER(0),AMATURE(1), NORMAL(2), PROFESSIONAL(3);
    private int lvl;
    Level(int x){
        lvl = x;
    }
    int getPrice(){
        return lvl;
    }

}

public class question9 {
    public static void main(String[] args){
        System.out.println("Default value of beginner is "+Level.BEGINNER.getPrice());
        System.out.println("Default value of amature is "+Level.AMATURE.getPrice());
        System.out.println("Default value of normal is "+Level.NORMAL.getPrice());
        System.out.println("Default value of pro is "+Level.PROFESSIONAL.getPrice());
    }

}
