package hibernateactivity.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import org.apache.commons.validator.routines.DateValidator;


public class Operations {
    
    public Set contactDetails(String[] cDetails, String[] cType) {
        Set cD = new HashSet();
        for (int i=0; i<cDetails.length; i++) {
            Contacts c = new Contacts(cDetails[i],cType[i].toLowerCase());
            cD.add(c);
        }
        return cD;
    }
    
    public Set<Roles> addRole(String[] roles) {
        Set r = new HashSet();       
        for(int i=0; i<roles.length;i++) {
            switch(roles[i].toLowerCase()) {
                case "police": 
                    r.add(new Roles(1,"Police"));
                    break;
                case "politician":
                    r.add(new Roles(2,"Politician"));
                    break;
                case "soldier":
                    r.add(new Roles(3,"Soldier"));
                    break;
                case "celebrity":
                    r.add(new Roles(4,"Celebrity"));
                    break;
                case "worker":
                    r.add(new Roles(5,"Worker"));
                    break;
            }
        }
        return r;
    }

    public Date dateValid(String date) {
        DateValidator dateVal = DateValidator.getInstance();
        Date dt = null;                    
        
        dt = dateVal.validate(date, "yyyy-MM-dd");
        if(dt == null) {
            date = "0001-01-01";
            dt = dateVal.validate(date, "yyyy-MM-dd");
        } 
        return dt; 
    }

    public int integerValid(String in) {
        boolean a = true;
        int input = 0;
        
            try {
                input = Integer.parseInt(in);
            } catch(NumberFormatException e) {
                input = 0;
            }
        return input;
    }
    
    public String editForm(Person person) {
        Name n = person.getNames();
        String eForm = "<form action=\"ServletsEdit\" method=\"POST\" name=\"forms\"onSubmit=\"return checkInput()\"><table align=\"center\">" +
                        "<tr><td>First Name: </td><td><input type=\"text\" name=\"first_name\"value=\"" + n.getFirst_name() + "\"/></td></tr>" +
                        "<tr><td>Last Name: </td><td><input type=\"text\" name=\"last_name\"value=\"" + n.getLast_name() + "\"/></td></tr>" +
                        "<tr><td>Address: </td><td><input type=\"text\" name=\"address\"value=\"" + person.getAddress() + "\"/></td></tr>" +
                        "<tr><td>Birthday: </td><td><input type=\"date\" name=\"bday\"value=\"" + person.getBday() + "\"/></td></tr>" +
                        "<tr><td>Age: </td><td><input type=\"number\" name=\"age\"value=\"" + person.getAge() + "\"/></td></tr>"+
                        "<tr><td>Contacts: </td><td id=\"contactNumber\"><select id=\"contact\">" +
                        "<option value=\"email\" onclick=\"createField('E-mail')\">E-mail</option>" +
                        "<option value=\"cellphone\" onclick=\"createField('Cellphone')\">Cellphone#</option>" +
                        "<option value=\"telephone\"onclick=\"createField('Telephone')\">Telephone#</option>" +
                        "</select>";
        Set<Contacts> c = person.getContact();
        for(Contacts cont: c) {
            eForm = eForm +"<div id=\""+ cont.getContact() +"\">"+ cont.getType() + ": "+
                    "<input type=\"text\" name=\"contactDetail\" size=\"10\" value=\""+ cont.getContact() +"\"/> "+
                    "<input type=\"button\" value=\"Remove\"onClick=\"removeDiv('"+ cont.getContact() +"')\">" + 
                    "<input type=\"hidden\" name=\"contactType\" value=\""+cont.getType() +"\"/><br/></div>";
        }
        eForm = eForm + "</td></tr>";
        eForm = eForm + "<tr><td>Gender: </td><td><input type=\"radio\" name=\"gender\" value=\"male\"/>Male" +
                        "<input type=\"radio\" name=\"gender\" value=\"female\"/>Female</td></tr>" +    
                        "<tr><td>Grade: </td><td><input type=\"number\" name=\"grade\" value=\""+ person.getGrade() +"\"/></td></tr>" +
                        "<tr><td>Date hired: </td><td><input type=\"date\" name=\"date_hired\"value=\""+ person.getDate_hired()+"\"/></td></tr>" +
                        "<tr><td>Currently Employed: </td><td>" +
                                    "<input type=\"radio\" name=\"currently_employed\" value=\"yes\"/>Yes" +
                                    "<input type=\"radio\" name=\"currently_employed\" value=\"no\"/>No" +
                        "</td></tr>" +
                        "<tr><td>Roles: </td><td><input type=\"checkbox\" name=\"roles\" value=\"police\"/>Police <br/>" +
                                    "<input type=\"checkbox\" name=\"roles\" value=\"soldier\"/>Soldier <br/>" +
                                    "<input type=\"checkbox\" name=\"roles\" value=\"politician\"/>Politician<br/>" +
                                    "<input type=\"checkbox\" name=\"roles\" value=\"celebrity\"/>Celebrity <br/>" +
                                    "<input type=\"checkbox\" name=\"roles\" value=\"worker\"/>Worker</td></tr>" +
                        "<tr><td></td><td><input type=\"submit\" value=\"Update Details\">" +
                        "<input type=\"button\" value=\"Cancel\" onClick=\"location.href='Servlets'\"/>" +
                        "<input type=\"hidden\" name=\"todo\" value=\"edit\"/></br>" +
                        "<input type=\"hidden\" name=\"person\" value=\""+ person.getId() +"\"/></br>";
                      
       return eForm;                 
    }

    public String displaySearch(List<Person> person){
    String words = "<table border=\"1\"align=\"center\"><tr><th colspan=\"6\">"+ "Searched Person " + 
                    "</th></tr><tr><th>ID</th><th><spring:message code=\"label.name\"/></th>" + 
                    "<th><spring:message code=\"label.grade\"/></th>"+
                    "<th><spring:message code=\"label.datehired\"/></th>"+
                    "<th><spring:message code=\"label.contact\"/></th>"+
                    "<th><spring:message code=\"label.action\"/></th></tr>";
        if(person.isEmpty()){
            words = "<h2>No Person Found</h2>";
        } else {    
            for(Person persons: person){
                Name name = persons.getNames();
                 words = words +"<tr><td>"+ persons.getId() +"</td>" + 
                           "<td>"+ name.getFirst_name() + " " + name.getLast_name()+"</td>" + 
                           "<td>"+ persons.getGrade() +"</td>" +
                           "<td>"+ persons.getDate_hired() +"</td> <td>";

                for(Contacts contact:persons.getContact()){
                    words = words + contact.getType() + ": " + contact.getContact() + " \n" ;
                }     
                words = words + "</td><td><input type=\"button\" value=\"Edit\" size=\"10\"/>" +
                    "<br/><input type=\"button\" value=\"Delete\" size=\"10\"/></td></tr>";
            }
            words = words + "</table><br/><br/>";
        }
        return words;
    }

    public String displayPerson(List<Person> person, String by) {
        String words = "";
           
        for(Person persons: person){
            Name name = persons.getNames();
            words = words +"<tr><td>"+ persons.getId() +"</td>" + 
                           "<td>"+ name.getFirst_name() + " " + name.getLast_name()+"</td>" + 
                           "<td>"+ persons.getGrade() +"</td>" +
                           "<td>"+ persons.getDate_hired() +"</td> <td>";

            for(Contacts contact:persons.getContact()){
                words = words + contact.getType() + ": " + contact.getContact() + " \n" ;
            }     
            words = words + "</td><td><button onclick=\"location.href='${personEdit}'\">Edit</button>" +
                    "<br/><button onclick=\"location.href='${personDelete}'\">Delete</button></td></tr>";
        }

        return words;
    }  

    public String displayRole(Roles role, int by) {
        String[] rol = {"Police","Politician","Soldier","Celebrity","Worker"};
        String words = "<table border=\"1\"align=\"center\"><tr><th colspan=\"5\">"+ "LIST: ROLE by " + rol[by-1] + 
                        "</th></tr><tr><th>ID</th><th>Name</th><th>Grade</th><th>Date Hired</th><th>Contact Number</th></tr>";
        Set<Person> person = role.getPersonRole();   
        if(person.isEmpty()){
            words = "<h2>No Role " + rol[by-1] +" Found</h2>";
        }else{
            for(Person persons: person){
                Name names = persons.getNames();
                words = words +"<tr><td>"+ persons.getId() +"</td>" + 
                           "<td>"+ names.getFirst_name() + " " + names.getLast_name()+"</td>" + 
                           "<td>"+ persons.getGrade() +"</td>" +
                           "<td>"+ persons.getDate_hired() +"</td> <td>";

                for(Contacts contact:persons.getContact()){
                    words = words + contact.getType() + ": " + contact.getContact() + " \n" ;
                }     
                words = words + "</td></tr>";
            }
        }
        words = words + "</table>";
        return words;
    }

    public String createFields(){
        String fields = "</br><form>" + 
                        "<input type=\"button\" value=\"Add Person\" onClick=\"location.href='AddPerson'\"> &nbsp;&nbsp;" +
                        "</form></br>";

        return fields;
    }  
    public String listBy(){
        String order = "</br>VIEW:<form><input type=\"radio\" name=\"view\" onclick=\"listTo('person')\"/> Person &nbsp;&nbsp" +
                       "<input type=\"radio\" name=\"view\" onclick=\"listTo('role')\"/> Roles </form>"+
                       "<form action=\"Persons\" method=\"POST\" id=\"displist\"onSubmit=\"return checkList()\">" +
                       " </form>" ; 
        return order;
    }
    
    public String searchBox(){
        String search = "<form>" +
                        "<input type=\"text\" name=\"search\"placeHolder=\"Search Person Name\"/>" +
                        "<input type=\"submit\" value=\"Search\"/></form> ";
        return search;
    }

}
