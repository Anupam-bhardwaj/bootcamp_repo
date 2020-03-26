package Exercise2

//Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails
// which provide there specific details like rateofinterest etc,print details of every bank.

open class Question3 {
        var rateOfInterest: Int = 0
        var bankName: String = ""
        var bankAddress: String = ""


}

fun main(args: Array<String>) {

    var sbi = SBI()
    var boi = BOI()
    var icici = ICICI()

    sbi.setDetails(7,"SBI","Noida")
    boi.setDetails(9,"BOI","Greater Nodia")
    icici.setDetails(8,"ICICI","Delhi")

    sbi.getDetails()
    boi.getDetails()
    icici.getDetails()

}

class BOI: Question3() {

    open fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    open fun getDetails(){
        println("Bank name: "+this.bankName);
        println("Bank address: " + this.bankAddress);
        println("rate of interest: " + this.rateOfInterest);
        println("\n");
    }
}

class ICICI: Question3(){

    open fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    open fun getDetails(){
        println("Bank name: "+this.bankName);
        println("Bank address: " + this.bankAddress);
        println("rate of interest: " + this.rateOfInterest);
        println("\n");
    }
}

class SBI: Question3(){

    open fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    open fun getDetails(){
        println("Bank name: "+this.bankName);
        println("Bank address: " + this.bankAddress);
        println("rate of interest: " + this.rateOfInterest);
        println("\n");
    }
}