function ListPerson(){
    
    document.getElementById("Contents").innerHTML = "";
    
    var f = document.createElement("form");
    f.setAttribute("action","Servlets");
    f.setAttribute("method","GET");
        
    var r1 = document.createElement("input");
    r1.setAttribute("type","radio");
    r1.setAttribute("name","display");
    r1.setAttribute("value","last_name");
    r1.setAttribute("id","r1");

    var r2 = document.createElement("input");
    r2.setAttribute("type","radio");
    r2.setAttribute("name","display");
    r2.setAttribute("value","grade");
    r2.setAttribute("id","r2");

    var r3 = document.createElement("input");
    r3.setAttribute("type","radio");
    r3.setAttribute("name","display");
    r3.setAttribute("value","date_hired");
    r3.setAttribute("id","r3");

    var b1 = document.createElement("input");
    b1.setAttribute("type","submit");
    b1.setAttribute("value","Submit");

    var objTextNode1 = document.createTextNode("Last Name");
    var objTextNode2 = document.createTextNode("Grade");
    var objTextNode3 = document.createTextNode("Date Hired");
    
    var objLabel = document.createElement("label");
    objLabel.htmlFor = r1.id;
    objLabel.appendChild(r1);
    objLabel.appendChild(objTextNode1);

    var objLabel2 = document.createElement("label");
    objLabel2.htmlFor = r2.id;
    objLabel2.appendChild(r2);
    objLabel2.appendChild(objTextNode2);

    var objLabel3 = document.createElement("label");
    objLabel3.htmlFor = r3.id;
    objLabel3.appendChild(r3);
    objLabel3.appendChild(objTextNode3);

    f.appendChild(objLabel);
    f.appendChild(objLabel2);
    f.appendChild(objLabel3);
    f.appendChild(b1);
    document.getElementById("Contents").appendChild(f);

}
function AddPerson(){

    var f = document.createElement("form");
    f.setAttribute("action","Servlets");
    f.setAttribute("method","GET");

    var categ = ["First Name: ","Last Name: ", "Grade: " , "Age: " , "Birthday: " , "Address: ", "Gender: ", "Date Hired: ", "Currently Employed: " ];
    document.getElementById("Contents").innerHTML = "";
    for (var i=0; i<categ.length; i++){
        var inp = document.createElement("input");
        inp.setAttribute("type","text");
        inp.setAttribute("name","categ"+i);
        inp.setAttribute("id","categ"+i);
        console.log("categ"+i);
        var objTextNode1 = document.createTextNode(categ[i]);
        var objLabel = document.createElement("label");
        objLabel3.htmlFor = inp.id;
        objLabel.appendChild(inp);
        objLabel.appendChild(objTextNode1);
        document.getElementById('MyFormElem').label.innerHTML = 'Look ma this works!';
        f.appendChild(objLabel);
    }
    document.getElementById("Contents").appendChild(f);
}
function DeletePerson(){

}
function EditPerson(){

}
