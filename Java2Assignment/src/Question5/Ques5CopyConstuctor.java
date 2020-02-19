package Question5;

//WAP to show object cloning in java using Copy Constructor

class CopyConstructor {

    private double re, im;

    public CopyConstructor(double re, double im) {
        this.re = re;
        this.im = im;
    }

    CopyConstructor(CopyConstructor c) {
        System.out.println("Copy constructor called");
        re = c.re;
        im = c.im;
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}

public class Ques5CopyConstuctor {
    
    public static void main(String[] args) {
        CopyConstructor c1 = new CopyConstructor(10, 15);

        CopyConstructor c2 = new CopyConstructor(c1);

        CopyConstructor c3 = c2;

        System.out.println(c2);
    }
}
