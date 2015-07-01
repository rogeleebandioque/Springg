package hibernateactivity.app;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Roles; //Additional
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Name;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;


public class HibernateActivity {

    public static void main( String[] args ) {
        Service service = new Service();
        boolean choice = true;    	
        while (choice) {
    	    try {
                int input = Integer.parseInt(userInput("\nEnter Choice: \n [1] List [2] Add [3] Delete [4] Edit"));
                switch (input) {
                    case 1: 
                        List<Person> person = null;
                        String listBy = stringValid(userInput("Order by [Grade] [Date_Hired] [Last_Name]"),"Input: ").toLowerCase();
                        while(!(listBy.equals("grade") || listBy.equals("date_hired") || listBy.equals("last_name"))) {
                            listBy = stringValid(userInput("Order by [Grade] [Date_Hired] [Last_Name]"),"Input: ").toLowerCase();
                        }
                        String orderBy = stringValid(userInput("[ASC]Ascending [DESC]Descending"),"Input: ").toLowerCase();
                        while(!(orderBy.equals("asc") || orderBy.equals("desc"))) {
                            orderBy = stringValid(userInput("[ASC]Ascending [DESC]Descending"),"Input: ").toLowerCase();
                        } 
                        if(listBy.equals("grade")) {
                            person = service.getPersons(listBy, orderBy);
                            if(orderBy.equals("desc")){
                                Collections.sort(person, Collections.reverseOrder());
                            }else{
                                Collections.sort(person);
                            }
                        } else if(listBy.equals("last_name")) {
                            person = service.getPersons(listBy, orderBy);
                        } else { 
                            person = service.getPersons(listBy, orderBy);
                        }
                        displayPerson(person);
						break;

                    case 2: 
                        Person addPer = addPerson();
                        System.out.println(service.addPersons(addPer));	 
                        break;

                    case 3: 
                        int idNum = integerValid(userInput("Enter ID# to be deleted"),"ID#");
                        boolean exist = service.searchPersons(idNum);
                           
                        if (!exist) {
                            String mes = service.deletePersons(idNum);
                            System.out.println(mes);
                        } else {
                            System.out.println("ID does not exist!");
                        }
					    break;

                    case 4: 
                        int idNo = integerValid(userInput("Enter ID to update: "),"ID to update");
                        boolean existUp = service.searchPersons(idNo);                        

                        if(!existUp) {
                            Person people = service.getPersons(idNo);
                            Name n = people.getNames();
                            String toEdit = stringValid(userInput("<Person: "+n.getFirst_name()+">\n"+"Edit What? \n[name] " + 
                                                       "[address] [age] \n[contacts] [bday] [employment status] \n[grade] " +
                                                       "[date hired] [gender] [role]"),"Category to Edit");                              
                            people = editPerson(toEdit, people);                                
                            String message = service.updatePersons(people);
                            System.out.println(message);                     
                        }
                        break;

        			default:
    					System.out.println("Not in Choices!");
                    break;
                }
            } catch (NumberFormatException e) {
                 System.out.println("Invalid Input");
            }
        }
    }

    public static void displayPerson(List<Person> person) {
        for(Person persons:person){
            Name name = persons.getNames();
            System.out.println("\nName: " + name.getFirst_name()+ " "+ name.getLast_name());      
            System.out.println("Date Hired: "+ persons.getDate_hired() + "\nGrade: "+ persons.getGrade());
            for(Contacts contact:persons.getContact()){
                System.out.println(">"+ contact.getType() + ": " + contact.getContact());
            }
        }
    }

    public static Person editPerson(String edit, Person person) {
        edit = edit.toLowerCase().trim();
        boolean ed = true;

        loop: while (ed) {
            switch (edit) {
                case "name":
                    Name eName = person.getNames();
                    eName.setFirst_name(stringValid(userInput("Enter New First Name:"),"First Name"));
                    eName.setLast_name(stringValid(userInput("Enter New Last Name:"),"Last Name"));   
                    break loop;

                case "address":
                    person.setAddress(stringValid(userInput("Enter New Address"),"Address"));
                    break loop;

                case "age":
                    person.setAge(integerValid(userInput("Enter New Age"),"Age"));
                    break loop;

                case "contacts":
                    Set toUpCon = person.getContact();
                    toUpCon = upContacts(toUpCon);
                    person.setContact(toUpCon);
                    break loop;

                case "bday":
                    person.setBday(dateValid(userInput("Enter New Bday (MM/dd/yyyy) :"), "Bday (MM/dd/yyyy) :"));
                    break loop;

                case "employment status":
                    person.setCurrently_employed(employmentValid(userInput("Currently Employed? [yes] [no]")));
                    break loop;

                case "gender":
                    person.setGender(genderValid(integerValid(userInput("Enter Person's Gender [1] Male [2] Female: "),"Answer: "),
                                                                        "Gender [1] Male [2] Female: "));
                    break loop;

                case "hired date":
                    person.setDate_hired(dateValid(userInput("Enter New Hired Date (MM/dd/yyyy) :"), "Hired Date (MM/dd/yyyy) :"));
                    break loop;

                case "grade":
                    person.setGrade(integerValid(userInput("Enter Person's Grade: "),"Grade: "));
                    break loop;

                case "role":
                    Set<Roles> upRole = person.getRole();
                    person.setRole(upRoles(upRole));
                    break loop;

                default:
                    System.out.println("Not in category");
                    edit = stringValid(userInput("Enter valid category: "),"category to edit: ");
                    break;
            }
        }
        return person;
    }

    public static String userInput(String message) {
        System.out.println(message);        
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }
    public static Person addPerson() {
        System.out.println("Add Person: ");
        Person newPerson = new Person();       
        Name aName = new Name();

        aName.setFirst_name(stringValid(userInput("Enter Person's First Name:"),"First Name"));
        aName.setLast_name(stringValid(userInput("Enter Person's Last Name:"),"Last Name"));
        newPerson.setNames(aName);
        newPerson.setAddress(stringValid(userInput("Enter Person's Address: "),"Name: "));
        newPerson.setContact(contactDetails());
        newPerson.setAge(integerValid(userInput("Enter Person's Age: "),"Age: "));
        newPerson.setGender(genderValid(integerValid(userInput("Enter Person's Gender [1] Male [2] Female: "),"Answer: "),
                                        "Gender [1] Male [2] Female: "));
        newPerson.setBday(dateValid(userInput("Enter Person's Birth date (MM/dd/yyyy): "), "Birth date (MM/dd/yyyy): "));
        newPerson.setGrade(integerValid(userInput("Enter Person's Grade: "),"Grade: "));
        newPerson.setDate_hired(dateValid(userInput("Enter Person's Date Hired (MM/dd/yyyy):  "),"Date Hired (MM/dd/yyyy): : "));
        newPerson.setCurrently_employed(employmentValid(userInput("Currently Employed? [yes] [no]"))); 
        newPerson.setRole(addRole());   
       
       return newPerson;    
    }

    public static String stringValid(String check, String categ) {
        while(check.equals("")) {
            check = userInput("Enter Valid "+ categ);
        }
        return check; 
    }

    public static int integerValid(String in, String categ) {
        boolean a = true;
        int input = 0;
        
        while(a) {
            try {
                input = Integer.parseInt(in);
                a = false;
            } catch(NumberFormatException e) {
                in = userInput("Enter valid "+categ);
            }
        }
        return input;
    }

    public static Date dateValid(String date, String categ) {
        boolean d=true;
        DateValidator dateVal = DateValidator.getInstance();
        Date dt = null;

        while(d) {                      
            dt = dateVal.validate(date, "MM/dd/yyyy");
            if(dt == null) {
                date = userInput("Enter valid "+categ);
            } else {
                d = false;
            }
        }
        return dt; 
    }
    
    public static String genderValid(int gender,String categ) {
        String genderV = "";     
        boolean gv = true;           

        while(gv) {
            if(gender ==1 || gender ==2) {
                break; 
            }
            gender = integerValid(userInput("Enter valid " + categ), categ);        
        }
        if(gender == 1){
            genderV = "male";
        }else if(gender == 2){
            genderV = "female";
        }
        return genderV;    
    }

    public static String employmentValid(String status) {
        status = status.toLowerCase().trim();
        boolean ev = true;

        while (ev) {
            if(status.equals("yes") || status.equals("no")) {
                break;
            }
            status = stringValid(userInput("Enter valid answer: "), "Enter valid answer: ");
        }
        return status; 
    }

    public static String emailValid(String email, String categ) {
        EmailValidator valid = EmailValidator.getInstance();        
        boolean emv = true;

        while(emv) {
            if(valid.isValid(email)) {
                break;
            } else {
                email = userInput("Enter Valid "+ categ);
            }
        }
        return email;
    }

    public static Set contactDetails() {
        Set cD = new HashSet();
        boolean cnd = true;

        while (cnd) {
            int cN = integerValid(userInput("Enter Contact details [1]E-mail [2]Cellphone# [3]Telephone#"), 
                                 "details [1]E-mail [2]Cellphone# [3]Telephone#");
            switch(cN) {
                case 1:
                    String email = emailValid(userInput("Enter E-Mail: "), "Email: ");
                    cD.add(new Contacts(email,"e-mail"));
                    break;

                case 2:
                    String cell = patternMatch(stringValid(userInput("Enter Cellphone#: "), "Cellphone#: "));
                    cD.add(new Contacts(cell,"cellphone"));
                    break;

                case 3:
                    String phone = patternMatch(stringValid(userInput("Enter Telephone#: "), "Telephone#: "));
                    cD.add(new Contacts(phone,"telephone"));                
                    break;

                default:
                    System.out.println("Enter valid contact details");
                    break;
            }
            int cont = integerValid(userInput("Add another contact? [1]Yes [2]No"),"answer [1]Yes [2]No: ");

            while(cont>2) {
                System.out.println("Not in the choices");
                cont = integerValid(userInput("Add another contact? [1]Yes [2]No"),"answer [1]Yes [2]No: ");         
            }
            if(cont == 2) {
                break;
            }
        }
        return cD;
    }

    public static Set<Contacts> upContacts(Set<Contacts> toUpCon) {

        for(Iterator iterator = toUpCon.iterator(); iterator.hasNext();) {
            Contacts cont = (Contacts) iterator.next();
            String up = stringValid(userInput(" Edit: " + cont.getContact() + "? [Yes][No][Delete]: "),"Answer[Yes][No][Delete]: ").toLowerCase();

            switch (up) {
                case "yes":
                    if(cont.getType().equals("e-mail")) {
                        cont.setContact(emailValid(userInput("Enter New Email: "),"New Email:"));
                    } else if(cont.getType().equals("cellphone")) {
                        cont.setContact(patternMatch(stringValid(userInput("Enter Cellphone#: "), "Cellphone#: ")));
                    } else if(cont.getType().equals("telephone")) {
                        cont.setContact(patternMatch(stringValid(userInput("Enter Telephone#: "), "Telephone#: ")));
                    } 
                    break;

                case "no":                    
                    break;

                case "delete":
                    String check = stringValid(userInput("Are you sure [Yes][No]?"),"Answer [Yes][No]").toLowerCase();

                    inner: while(true) {
                        if(check.equals("yes")||check.equals("no")){
                            break inner;
                        }                        
                        check = stringValid(userInput("Are you sure [Yes][No]?"),"Answer [Yes][No]").toLowerCase();
                    }
                    if(check.equals("yes")) {
                        iterator.remove();
                        System.out.println("Contact removed!");
                    }
                    break;    

                default:
                    System.out.println("Not in choices! ");
                    break;         
            }
        }
        return toUpCon;    
    }

    public static String patternMatch(String num) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(num);
        boolean matches = matcher.matches();

        while(matches == false) {
            num = stringValid(userInput("Enter valid input: "),"input: ");
            matcher = pattern.matcher(num);
            matches = matcher.matches();
        }
    return num;
    }
    
    public static Set<Roles> addRole() {
        Set aRole = new HashSet(); 
        aRole = listRoles(aRole);       

        return aRole;
    }

    public static Set<Roles> upRoles(Set<Roles> updRole) {
        String ansRole = stringValid(userInput("Update Roles: [Add] [Delete]"),"Answer [Add] [Delete]: ");

        while(!(ansRole.equalsIgnoreCase("Add") || ansRole.equalsIgnoreCase("Delete"))) {
            ansRole = stringValid(userInput("Update Roles: [Add] [Delete]"),"Answer [Add] [Delete]: ");
        }
        if (ansRole.equalsIgnoreCase("Add")) {
            updRole = listRoles(updRole);
        } else {
            for(Roles uRoles: updRole) {
                String delRole = stringValid(userInput("Delete: " + uRoles.getRoleName() + " [Yes][No] "),"Answer: ");
                if(delRole.equalsIgnoreCase("yes")){
                    updRole.remove(uRoles);
                }
            }
        }
        return updRole;        
    }

    public static Set<Roles> listRoles(Set<Roles> r) {
        boolean rol = true;
        while(rol){
            int rolNo = integerValid(userInput("Choose Role [1]Police [2]Politician [3]Soldier [4]Celebrity [5]Worker"),"Choice");
            switch(rolNo) {
                case 1: 
                    r.add(new Roles(1,"Police"));
                    break;
                case 2:
                    r.add(new Roles(2,"Politician"));
                    break;
                case 3:
                    r.add(new Roles(3,"Soldier"));
                    break;
                case 4:
                    r.add(new Roles(4,"Celebrity"));
                    break;
                case 5:
                    r.add(new Roles(5,"Worker"));
                    break;
            }
            int anotherRole = integerValid(userInput("Add another Role? [1]Yes [2]No "),"Answer [1]Yes [2]No: ");
            while(anotherRole>2) {
                System.out.println("Not in the choices");
                anotherRole = integerValid(userInput("Add another Role? [1]Yes [2]No"),"Answer [1]Yes [2]No: ");         
            }
            if(anotherRole == 2) {
                rol = false;
            }
        }
        return r; 
    }

}
