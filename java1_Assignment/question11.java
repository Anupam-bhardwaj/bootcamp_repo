package IntroToJava1;

//Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails which provide there specific details like rateofinterest etc,print details of every banks

public class question11 {
    int rateOfInterest;
    String bankName;
    String bankAddress;

    public static void main(String[] args) {
        SBI s1 = new SBI();
        s1.setDetails(7,"SBI","Noida");
        ICICI ic1 = new ICICI();
        ic1.setDetails(8,"ICICI","Delhi");
        BOI b1 = new BOI();
        b1.setDetails(9,"BOI","Greater Nodia");
        b1.getDetails();
        s1.getDetails();
        ic1.getDetails();
    }
}
class BOI extends question11{
    public void setDetails(int roi, String name, String add){
        this.rateOfInterest=roi;
        this.bankName = name;
        this.bankAddress = add;
    }

    public void getDetails(){
        System.out.println("rate of interest: " + this.rateOfInterest);
        System.out.println("Bank name: "+this.bankName);
        System.out.println("Bank address: " + this.bankAddress);
        System.out.println("\n");
    }
}

class ICICI extends question11{
    public void setDetails(int roi, String name, String add){
        this.rateOfInterest=roi;
        this.bankName = name;
        this.bankAddress = add;
    }

    public void getDetails(){
        System.out.println("rate of interest: " + this.rateOfInterest);
        System.out.println("Bank name: "+this.bankName);
        System.out.println("Bank address: " + this.bankAddress);
        System.out.println("\n");
    }

}

class SBI extends question11{
    public void setDetails(int roi, String name, String add){
        this.rateOfInterest=roi;
        this.bankName = name;
        this.bankAddress = add;
    }

    public void getDetails(){
        System.out.println("rate of interest: " + this.rateOfInterest);
        System.out.println("Bank name: "+this.bankName);
        System.out.println("Bank address: " + this.bankAddress);
        System.out.println("\n");
    }
    
}
