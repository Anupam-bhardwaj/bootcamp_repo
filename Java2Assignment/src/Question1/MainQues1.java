package Question1;

//Create Java classes having suitable attributes for Library management system.Use OOPs concepts in your design.Also try to use interfaces and abstract classes.

public class MainQues1 {
    public static void main(String[] args) {
        Student s1 = new Student("Anupam", 4001, 1 , "Computer Science");
        s1.issueBook(8,"intro to java");
        s1.getDetails();
        s1.issueBook(9,"Intro to C++");
        s1.getDetails();
    }
}
