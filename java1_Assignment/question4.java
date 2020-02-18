package IntroToJava1;

//Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String

public class question4 {
    public static void main(String[] args) {
        int uCount = 0;
        int lCount = 0;
        int digitCount = 0;
        int specialCount = 0;

        String str = "HHHHHH World !! 122244";
        for(int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                uCount++;
            }
            else if (Character.isLowerCase(ch)){
                lCount++;
            }
            else if (Character.isDigit(ch)){
                digitCount++;
            }
            else{
                specialCount++;
            }
        }

        //Calculating percentage.
        double length = str.length();
        double upperPercent = (uCount/length)*100;
        double lowerPercent = (lCount/length)*100;
        double otherPercent = (digitCount/length)*100;
        double specialPercent = (specialCount/length)*100;

        //Printing count and percentage.
        System.out.println("upperCase count = "+ uCount);
        System.out.println("lowerCase count = "+ lCount);
        System.out.println("other character count = "+ digitCount);
        System.out.println("special character count = "+ specialCount);
        System.out.println("upperCase percentage = " + upperPercent);
        System.out.println("lowerCase percentage = " + lowerPercent);
        System.out.println("other percentage = " + otherPercent);
        System.out.println("special percentage = " + specialPercent);

    }
}
