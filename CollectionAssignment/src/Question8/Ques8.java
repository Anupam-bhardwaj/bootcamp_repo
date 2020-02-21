package Question8;

import java.util.Stack;

class SpecialStack{
    Stack<Integer> stack;
    Integer minElement;

    public SpecialStack() {
        stack = new Stack<Integer>();
    }

    void getMin(){
        if(stack.isEmpty()){
            System.out.println("Stack is Empty.");
        }else {
            System.out.println("Minimum Element in the stack is: "+minElement);
        }
    }

    void peek(){
        if(stack.isEmpty()){
            System.out.println("Stack is Empty.");
            return;
        }

        Integer top = stack.peek();
        System.out.println("Top most element of the stack is: ");

        if (top<minElement){
            System.out.println(minElement);
        }else {
            System.out.println(top);
        }
    }

    void pop(){
        if(stack.isEmpty()){
            System.out.println("Stack is Empty.");
            return;
        }

        System.out.println("Top element removed: ");
        Integer top = stack.pop();

        if (top<minElement){
            System.out.println(minElement);
            minElement = 2*minElement-top;
        }
        else
            System.out.println(top);
    }
    void push(Integer element){
        if (stack.isEmpty())
        {
            minElement = element;
            stack.push(element);
            System.out.println("Number Inserted: " + element);
            return;
        }

        // If new number is less than original minEle
        if (element < minElement)
        {
            stack.push(2*element - minElement);
            minElement = element;
        }

        else
            stack.push(element);

        System.out.println("Number Inserted: " + element);
    }

}

public class Ques8 extends SpecialStack {
    public static void main(String[] args) {
        SpecialStack specialStack = new SpecialStack();

        specialStack.push(9);
        specialStack.push(5);
        specialStack.push(10);
        specialStack.pop();
        specialStack.getMin();
        specialStack.push(1);
        specialStack.push(6);
        specialStack.getMin();
        specialStack.pop();
    }
}
