package Question5;

//WAP to show object cloning in java using cloneable interface.

public class Ques5Cloneable implements Cloneable {
    int rollno;
    String name;

    Ques5Cloneable(int rollno, String name){
        this.rollno=rollno;
        this.name=name;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    public static void main(String args[]){
        try{
            Ques5Cloneable s1=new Ques5Cloneable(101,"Anupam");

            Ques5Cloneable s2=(Ques5Cloneable)s1.clone();

            System.out.println(s1.rollno+" "+s1.name);
            System.out.println(s2.rollno+" "+s2.name);

        }catch(CloneNotSupportedException c){}

    }
}  

