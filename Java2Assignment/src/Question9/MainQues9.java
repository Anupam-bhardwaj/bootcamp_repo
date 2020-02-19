package Question9;

// Design classes having attributes for furniture where there are wooden chairs and tables, metal chairs and tables. There are stress and fire tests for each products.

import java.util.Scanner;

public class MainQues9 {

    public static void main(String[] args) {
        System.out.println("Enter Table Type");
        Table table = null;
        Scanner input =  new Scanner(System.in);
        String TabletypeInput = input.next();
        if(TabletypeInput.equals("wooden")){
            table = new WoodenTable();
        }
        else if (TabletypeInput.equals("metal")){
            table = new MetalTable();
        }

        System.out.println(table.TableType());
        table.stressTest();
        table.fireTest();

        System.out.println("\n");
        System.out.println("Enter Chair Type");
        Chair chair = null;
        Scanner input1 =  new Scanner(System.in);
        String ChairtypeInput = input.next();
        if(ChairtypeInput.equals("wooden")){
            chair = new WoodenChair();

        }
        else if (ChairtypeInput.equals("metal")){
            chair = new MetalChair();
        }

        System.out.println(chair.Chairtype());
        chair.stressTest();
        chair.fireTest();

    }
}
