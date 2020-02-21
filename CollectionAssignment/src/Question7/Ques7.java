package Question7;

//Print the elements of an array in the decreasing frequency if 2 numbers have same frequency then print the one which came first.

import java.util.*;

public class Ques7 {
    private static void sortedArray(int[] inputArray)
    {
        Map<Integer, Integer> map = new LinkedHashMap<>();


        for (int i = 0; i < inputArray.length; i++)
        {
            if (map.containsKey(inputArray[i]))
            {

                map.put(inputArray[i], map.get(inputArray[i])+1);
            }
            else
            {

                map.put(inputArray[i], 1);
            }
        }


        ArrayList<Integer> sortedElements = new ArrayList<>();


        map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    for(int i = 1; i <= entry.getValue(); i++)
                        sortedElements.add(entry.getKey());
                });

        System.out.println("Unsorted Array Elements: ");

        System.out.println("Input Array :"+ Arrays.toString(inputArray) +"\n");

        System.out.println("Sorted Array Elements In Descending Order Of their Frequency: ");

        System.out.println(sortedElements);
    }

    public static void main(String[] args)
    {
        sortedArray(new int[] {7, 1, 3, 4, 7, 1, 7, 1, 4, 5, 1, 9, 3, });
    }

}
