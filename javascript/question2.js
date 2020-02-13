function ques_2(){
    var word = prompt("Enter Your String");
    var flag = 0 ;
    for(var i = 0; i < word.length/2; i++){
        if(word[i] != word[word.length - 1 - i ]){
            flag = 1;
        }
    }
    if(flag == 1){
        window.alert("NOT PALINDROME");
    }
    else{
        window.alert("PALINDROME")
    }
}
