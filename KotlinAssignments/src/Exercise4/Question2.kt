package Exercise4

//Create a list of Employee which will have name and age as properties. print the name of all employees age >30.

fun main(args: Array<String>) {

    var employeeMap: HashMap<String, Int> = HashMap()
    employeeMap.put("Anupam", 21)
    employeeMap.put("Ashutosh", 31)
    employeeMap.put("Lakshya", 22)
    employeeMap.put("Subarno", 40)
    employeeMap.put("Utkarsh", 20)

    println("Employees with age > 30: ")
    for (keys in employeeMap.keys) {
        if (employeeMap[keys]!! >= 30){
            println(keys)
        }
    }


}