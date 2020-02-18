package IntroToJava1;

//Write a single program for following operation using overloading
//  A) Adding 2 integer number
//  B) Adding 2 double
//  C) multiplying 2 float
//  D) multiplying 2 int
//  E) concate 2 string
//  F) Concate 3 String

import javax.swing.plaf.SplitPaneUI;

public class question10 {

    public int sum(int a, int b){
        int c = a+b;
        return c;
    }

    public double sum(double a, double b){
        double c = a+b;
        return c;
    }

    public float product(float a, float b){
        float c = a*b;
        return c;
    }

    public int product(int a, int b){
        int c =a*b;
        return c;
    }

    public String concat(String a, String b){
        String c = a+b;
        return c;
    }

    public String concat(String a, String b, String c){
        String d = a+b+c;
        return d;
    }

    public static void main(String[] args) {
        question10 question10 = new question10();
        System.out.println("Sum of 12 & 15: "+question10.sum(12, 15));
        System.out.println("Sum of 15.0f & 12.8f: "+question10.sum(15.0f, 12.8f));
        System.out.println("Product of 8 & 19: "+question10.product(8, 19));
        System.out.println("Product of 7.52 & 8.68: "+question10.product(7.52f,  8.68f));
        System.out.println("Concatination of two string hello & world: "+ question10.concat("hello", "world"));
        System.out.println("Concatination of three strings how, are & you: "+question10.concat("how", "are", "you"));



    }
}
