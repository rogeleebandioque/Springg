
var k;
var i=0;
function createField(_type){
    i = i + 1;
    var divi = "div" + i;
    var div = document.createElement("div");
    div.setAttribute("id", divi);
    var inp = document.createElement("input");
    if(_type == "E-mail"){
     inp.setAttribute("type","email");
    } else {
    inp.setAttribute("type","text");
    }
    inp.setAttribute("id",i);
    inp.setAttribute("name","contactDetail"); 
    inp.setAttribute("size", "10");
    var itemLabel = document.createElement("Label");
    itemLabel.setAttribute("for", i);
    itemLabel.setAttribute("name", "contactType");    
    itemLabel.innerHTML = _type + ": ";
    var inv = document.createElement("input");
    inv.setAttribute("type", "hidden");
    inv.setAttribute("name", "contactType"); 
    inv.setAttribute("value", _type);    
    div.appendChild(itemLabel);
    div.appendChild(inp);
    div.appendChild(inv);
    
    var rm = document.createElement("input");
    rm.setAttribute("type","button");
    rm.setAttribute("value","Remove");
    rm.setAttribute("onClick","removeDiv('"+ divi +"')");    
    div.appendChild(rm);
    document.getElementById("contactNumber").appendChild(div);
}

function confirm_delete() {
  return confirm('Are you sure?');
}

function breakLine(_div){
    var br = document.createElement("br");
    document.getElementById(_div).appendChild(br);
}

function label(_type,_inp,_d){
    var itemLabel = document.createElement("Label");
    itemLabel.setAttribute("for", i);
    itemLabel.setAttribute("name", "contactType");    
    itemLabel.innerHTML = _type + ": ";
    var inv = document.createElement("input");
    inv.setAttribute("id", "todo");
    inv.setAttribute("name", "contactType"); 
    inv.setAttribute("value", _type);     
    _d.appendChild(inv);    
    _d.insertBefore(itemLabel,_inp );
}

function listTo(_by){
    document.getElementById("displist").innerHTML = "";
    var form = document.createElement("form");
    form.setAttribute("method","GET");
  
    var inp = document.createElement("input");
    inp.setAttribute("type","hidden");
    inp.setAttribute("name","todo");    
    inp.setAttribute("value",_by);  
    form.appendChild(inp);


    if(_by == "person"){
        form.setAttribute("action","SearchPerson");
        
        var sbox = document.createElement("input");
        sbox.setAttribute("type","text");
        sbox.setAttribute("name","search");
        sbox.setAttribute("placeHolder","Search Person");
        form.appendChild(sbox);  

        var br = document.createElement("br");  
        form.appendChild(br);     

        var listBy = ["Last Name","Grade","Date Hired"];
        for(var categ in listBy){

            var cb = document.createElement("input");
            cb.setAttribute("type","radio");
            cb.setAttribute("name","listBy");
            cb.setAttribute("value",listBy[categ]);        
            form.appendChild(cb);
            var itemLabel = document.createElement("Label");
            itemLabel.innerHTML =  listBy[categ];
            form.appendChild(itemLabel,cb);
       }
        var br = document.createElement("br");  
        form.appendChild(br);
       
        var cb = document.createElement("input");
        cb.setAttribute("type","radio");
        cb.setAttribute("name","order");
        cb.setAttribute("value","asc");        
        form.appendChild(cb);
        var itemLabel = document.createElement("Label");
        itemLabel.innerHTML =  "Ascending";
        form.appendChild(itemLabel,cb);

        var cb = document.createElement("input");
        cb.setAttribute("type","radio");
        cb.setAttribute("name","order");
        cb.setAttribute("value","desc");        
        form.appendChild(cb);
        var itemLabel = document.createElement("Label");
        itemLabel.innerHTML =  "Descending";
        form.appendChild(itemLabel,cb);
 

    } else {
        form.setAttribute("action","SearchRole");

        var lroles = ["Police", "Politician","Soldier","Celebrity","Worker"];
        var x = 1;
    
        for(var categ in lroles){

            var cb = document.createElement("input");
            cb.setAttribute("type","radio");
            cb.setAttribute("name","listBy");
            cb.setAttribute("value",x);        
            form.appendChild(cb);
            var itemLabel = document.createElement("Label");
            itemLabel.innerHTML =  lroles[categ];
            form.appendChild(itemLabel,cb);
        
            x++;
       }       
    }
    var br = document.createElement("br");  
    form.appendChild(br);
    var b = document.createElement("input");
    b.setAttribute("type","submit");
    b.setAttribute("value","Search");
    form.appendChild(b);

    document.getElementById("displist").appendChild(form);
}

function checkList(){
    var r = document.getElementsByName("listBy");
    var c = -1;
    
    for(var i=0; i < r.length; i++){
       if(r[i].checked) {
          c = i; 
       }
    }
    var o = document.getElementsByName("order");  
    if(!o.length == 0){
        return checkOrder(o);
    } 
    
    if (c == -1 ){
	    return false;
    }
    return true;
}

function checkOrder(_o){
     
    var or = -1;
    
    for(var i=0; i < _o.length; i++){
       if(_o[i].checked) {
          or = i; 
       }
    }
    if(or == -1){
        return false;
    } else{
        return true;
    }
}

function checkInput(){
    var fname = document.forms.first_name;
    var lname = document.forms.last_name;
    var address = document.forms.address;
    var bday = document.forms.bday;
    var age = document.forms.age;
    var gender = document.getElementsByName("gender");
    var grade = document.forms.grade;
    var datehired = document.forms.date_hired;
    var employed = document.getElementsByName("currently_employed");
    var contacts = document.getElementsByName("contactDetail");
    var roles = document.getElementsByName("roles");

    if(fname == "" ||
        lname == "" ||
        address == "" ||
        bday== ""||
        age ==""||
        grade =="" ||
        datehired =="" ||
        employed == "" ||
        contacts.length == 0||
        roles.length == 0){
            alert("All fields are required!");
            return false;
        }
        var or = -1;
        var g = -1;
        var em = -1;
        var c = -1;
        for(var i=0; i < roles.length; i++){
           if(roles[i].checked) {
              or = i; 
           }
        }
        for(var i=0; i < employed.length; i++){
           if(employed[i].checked) {
              em = i; 
           }
        }
        for(var i=0; i < gender.length; i++){
           if(gender[i].checked) {
              g = i; 
           }
        }
        for(var i=0; i < contacts.length; i++){
            var word = document.getElementsByName("contactDetail")[i].value; 
            console.log(word);          
            if(!word == "") {
              c = i; 
           }
        }        
        if(or == -1 || g == -1 || em == -1 || g == -1 || c == -1){
            alert("All fields are required!");
            return false;
        }
        return true;
}

function removeDiv(_id){
    console.log("aa");
    document.getElementById("contactNumber").removeChild(document.getElementById(_id));
}

function removeAlert(){
    document.getElementById("alertUser").innerHTML="";
}
function openWindow() {
    var i, l, options = [{
       value: 'first',
       text: 'First'
    }, {
       value: 'second',
       text: 'Second'
    }],
    newWindow = window.open("", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");  

    newWindow.document.write("<select onchange='window.opener.setValue(this.value);'>");
    for(i=0,l=options.length; i<l; i++) {
        newWindow.document.write("<option value='"+options[i].value+"'>");  
        newWindow.document.write(options[i].text);  
        newWindow.document.write("</option>");
    }
    newWindow.document.write("</select>");
}

function setValue(value) {
    document.getElementById('value').value = value;
}



