package Exercise2.Question4

//Create Kotlin classes having suitable attributes
//for Library management system.Use OOPs concepts

class Students(bookId: Int, bookName: String): getDetails, Library(bookId, bookName) {

    var studentName: String = ""
    var rollNum = 0
    var deptID = 0
    var bookCount = 0
    var deptName: String = ""

    constructor(bookId: Int, bookName: String, studentName: String, rollNum: Int, deptID: Int, deptName: String)
            :this(bookId = bookId, bookName = bookName){
        this.studentName = studentName
        this.rollNum = rollNum
        this.deptID = deptID
        this.deptName = deptName
        this.bookID = bookId
        this.bookName = bookName
    }

    fun issueBook(bookId: Int, bookName: String){
        this.bookID
        this.bookName
        bookCount++
    }


    override fun Details() {
        println("Dept ID = "+this.deptID);
        println("Dept Name = "+this.deptName);
        println("Roll Number = " + this.rollNum);
        println("Student Name = " + this.studentName);
        println("book ID = " + this.bookID);
        println("book name = "+ this.bookName);
        println("book count = " + this.bookCount);
        println("\n")

    }
}

fun main(args: Array<String>) {
    var students = Students(8,"intro to java", "Anupam", 4001, 1 , "Computer Science")
    students.issueBook(8,"intro to java")
    students.Details()
    students.issueBook(9,"Intro to C++")
    students.Details()
}