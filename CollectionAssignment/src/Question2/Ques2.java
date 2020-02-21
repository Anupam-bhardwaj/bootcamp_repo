package Question2;

//Write a method that takes a string and returns the number of unique characters in the string.

import java.util.*;

public class Ques2 {

    public static void main(String[] args) {

        boolean flag = false;
        String test = "Alive is awesome";
        System.out.println(test);
        test=test.toLowerCase();
        List<Character> uniqueList = new LinkedList<>();
        List<Character> repeatedList = new LinkedList<>();
        HashSet<Character> unique = new HashSet<>();
        for (int i=0; i<test.length();i++){
            flag = unique.add(test.charAt(i));
            if (flag==true) {
                uniqueList.add(test.charAt(i));
            }
            else
                repeatedList.add(test.charAt(i));
        }
        for (int i=0;i<uniqueList.size();i++){
            for (int j=0;j<repeatedList.size();j++){
                if (uniqueList.get(i).equals(repeatedList.get(j))){
                    uniqueList.remove(i);
                }
            }
        }

        System.out.println(uniqueList.size());
        Iterator<Character> uniqueChars= uniqueList.iterator();
        while (uniqueChars.hasNext()){
            System.out.print(uniqueChars.next()+", ");
        }
    }
}