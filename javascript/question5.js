function ques_5(){
    console.log("Create a list of objects of Employee with info as follow : Name, age, salary ,DOB");
    var list = [
        {name : 'Ashutosh' , age: '20' , salary: 15000,dob: '10-03-99'},
        {name : 'Anupam' , age: '21' , salary: 100000,dob: '10-04-98'},
        {name : 'Subarno' , age: '22' , salary: 782,dob: '12-03-97'},
        {name : 'Lakshay' , age: '20' , salary: 200,dob: '12-04-98'},
        {name : 'aayush' , age: '20' , salary: 7500,dob: '17-07-99'},
        {name : 'chirag' , age: '20' , salary: 6000,dob: '21-03-95'},
        {name : 'ashish' , age: '20' , salary: 20001,dob: '10-05-99'}
    ];
    for (var i = 0 ;i<list.length;i++){
            console.log(list[i]);
    }
    
    console.log("Filter all employees with salary greater than 5000");
    for (var i = 0 ;i<list.length;i++){
        if(list[i].salary>5000){
            console.log(list[i]);
        }
    }

    console.log("Fetch employees with salary less than 1000 and age greater than 20. Then give them an increment 5 times their salary.")
    for(var i = 0 ;i<list.length;i++){
        if(list[i].salary > 20 && list[i].salary < 5000) {
            console.log(list[i]);
            list[i].salary = list[i].salary+list[i].salary*5; 
            console.log("Incrementing salary");
            console.log(list[i].salary);
        }
    }
}
