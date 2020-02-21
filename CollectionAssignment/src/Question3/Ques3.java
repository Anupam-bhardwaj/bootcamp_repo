package Question3;

//Write a method that takes a string and print the number of occurrence of each character characters in the string.

import java.util.HashMap;
import java.util.Map;

public class Ques3 {
    public static void main(String[] args) {
        String str="pneumonoultramicroscopicsilicovolcanoconiosis";
        str=str.toLowerCase();
        System.out.println(str+"\n");
        Map<Character,Integer> map=new HashMap<>();
        for(char i:str.toCharArray()){
            if(map.keySet().contains(i))
                {
                    int x=map.get(i);
                    map.put(i,++x);
                }
                else {
                    map.put(i,1); }

            }
            for (char c:map.keySet())
            {
                System.out.println(c+" "+map.get(c));
            }
        }
}

