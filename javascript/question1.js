function ques_1(){ 

    var amount = prompt("Enter Amount");
    var interest = prompt("Enter Interest Rate");
    var yrs = prompt("Enter Number Of Years");

    var total = (amount * interest * yrs)/100;
    window.alert("Simple Interest = " + total);
}