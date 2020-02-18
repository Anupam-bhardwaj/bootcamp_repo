package IntroToJava1;

//Write a program to print your Firstname,LastName & age using static block,static method & static variable respectively

public class question7 {
    static{
        System.out.println("Static block called");
        System.out.println("anupam");
        System.out.println("bhardwaj");
        System.out.println(20);
    }


    static String fname = "anupam";
    static  String lname = "bhardwaj";
    static int age = 20 ;

    public static void main(String[] args) {

        System.out.println(question7.fname);
        System.out.println(question7.lname);
        System.out.println(question7.age);
        myfun();
    }

    static void myfun(){
        System.out.println("static function");
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(age);
    }
}

