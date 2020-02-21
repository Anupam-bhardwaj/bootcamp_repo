package Question5;

//Write a program to sort Employee objects based on highest salary using Comparator.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Employee{

    String name;
    int salary;
    int age;

    public Employee(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

class SortBySalary implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.salary - o2.salary;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("Anupam", 200000, 21));
        list.add(new Employee("Lakshya", 100000, 21));
        list.add(new Employee("Subarno", 10, 21));
        list.add(new Employee("Ashutosh", 20, 21));

        System.out.println("Unsorted");
        for (int i=0; i<list.size(); i++)
            System.out.println(list.get(i));

        Collections.sort(list, new SortBySalary());

        System.out.println("\nSorted by rollno");
        for (int i=0; i<list.size(); i++)
            System.out.println(list.get(i));

    }

}
