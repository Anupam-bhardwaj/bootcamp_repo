package Question6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student{
    String Name;
    int Score;
    int age;

    public Student(String name, int score, int age) {
        Name = name;
        Score = score;
        this.age = age;
    }



    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Score=" + Score +
                ", age=" + age +
                '}';
    }
}

class Sort implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.Score == o2.Score){
            return o1.Name.compareTo(o2.Name);
        }else
            return o1.Score - o2.Score;
    }
}

public class Main {

    public static ArrayList<Student> list = new ArrayList<Student>();

    public static void main(String[] args) {


        list.add(new Student("Anupam", 99, 21));
        list.add(new Student("Lakshya", 99, 21));
        list.add(new Student("Ashutosh", 99, 21));
        list.add(new Student("Subarno", 92, 21));
        list.add(new Student("Chirag", 90, 21));


        System.out.println("Student List: ");
        for (int i=0; i<list.size(); i++)
            {System.out.println(list.get(i));}

        System.out.println("\n");
        Collections.sort(list, new Sort());

        for (int i=0; i<list.size();i++){
            System.out.println(list.get(i));
        }

    }


}
