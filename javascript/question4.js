function ques_4(){
    var employee={name : "Ashutosh", designation: "Trainee", competency: "Android"};
    console.log("Original object: ");
    console.log(employee);
    var objCopy=Object.assign({},employee);
    console.log("Copied object: ");
    console.log(objCopy);

}