package hibernateactivity.app;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;

public class HibernateActivity {

    public static void main( String[] args ) {
        Service service = new Service();
    	//boolean choice = true;
		
        while (true) {
    	    try {
			    int input = Integer.parseInt(userInput("\nEnter Choice: \n [1] List [2] Add [3] Delete [4] Edit"));
				switch (input) {
					case 1: 
						List<Person> person = service.getPersons();
                           displayPerson(person);
							break;

					case 2: 
                        Person addPer = addPerson();
                        System.out.println(service.addPersons(addPer));	 
    					break;

					case 3: 
	                    int idNum = integerValid(userInput("Enter ID# to be deleted"),"ID#");
                        boolean exist = service.searchPersons(idNum);
                           
                        if (exist) {
                            String mes = service.deletePersons(idNum);
                            System.out.println(mes);
                        } else {
                            System.out.println("ID does not exist!");
                        }
					    break;

					case 4: 
                        int idNo = integerValid(userInput("Enter ID to update: "),"ID to update");
                        boolean existUp = service.searchPersons(idNo);                          

                        if(existUp) {
                            Person people = service.getPersons(idNo);
                            String toEdit = stringValid(userInput("<Person: "+people.getFirst_name()+">\n"+"Edit What? \n[name] " + 
                                                       "[address] [age] \n[contacts] [bday] [employment status] \n[grade] " +
                                                       "[date hired] [gender] "),"Category to Edit");                              
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

        for(Iterator iterator = person.iterator(); iterator.hasNext();) {
            Person persons = (Person) iterator.next();
            System.out.println("\nName: " + persons.getFirst_name()+ " "+ persons.getLast_name());    
 
            Set<Contacts> c = persons.getContact(); 
            for (Iterator iterator2 = c.iterator(); iterator2.hasNext();) {
                Contacts contact = (Contacts) iterator2.next();
                System.out.println(">"+ contact.getType() + ": " + contact.getContact());   
            }
        }
    }

    public static Person editPerson(String edit, Person person) {
        edit = edit.toLowerCase().trim();
   
        loop: while (true) {
            switch (edit) {
                case "name":
                    person.setFirst_name(stringValid(userInput("Enter New First Name:"),"First Name"));            
                    person.setLast_name(stringValid(userInput("Enter New Last Name:"),"Last Name"));            
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
       
        newPerson.setFirst_name(stringValid(userInput("Enter Person's First Name: "),"First Name: "));
        newPerson.setLast_name(stringValid(userInput("Enter Person's Last Name: "),"Last Name: "));
        newPerson.setAddress(stringValid(userInput("Enter Person's Address: "),"Name: "));
        newPerson.setContact(contactDetails());
        newPerson.setAge(integerValid(userInput("Enter Person's Age: "),"Age: "));
        newPerson.setGender(genderValid(integerValid(userInput("Enter Person's Gender [1] Male [2] Female: "),"Answer: "),
                                        "Gender [1] Male [2] Female: "));
        newPerson.setBday(dateValid(userInput("Enter Person's Birth date (MM/dd/yyyy): "), "Birth date (MM/dd/yyyy): "));
        newPerson.setGrade(integerValid(userInput("Enter Person's Grade: "),"Grade: "));
        newPerson.setDate_hired(dateValid(userInput("Enter Person's Date Hired (MM/dd/yyyy):  "),"Date Hired (MM/dd/yyyy): : "));
        newPerson.setCurrently_employed(employmentValid(userInput("Currently Employed? [yes] [no]")));    
       
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

        while(true) {
            if(gender ==1 || gender ==2) {
                break; 
            }
            gender = integerValid(userInput("Enter valid " + categ), categ);        
        }
        if(gender == 1){
            genderV = "Male";
        }else if(gender == 2){
            genderV = "Female";
        }
        return genderV;    
    }

    public static String employmentValid(String status) {
        status = status.toLowerCase().trim();
        while (true) {
            if(status.equals("yes") || status.equals("no")) {
                break;
            }
            status = stringValid(userInput("Enter valid answer: "), "Enter valid answer: ");
        }
        return status; 
    }

    public static String emailValid(String email, String categ) {
        EmailValidator valid = EmailValidator.getInstance();        

        while(true) {
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

        while (true) {
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

    public static Set upContacts(Set toUpCon) {

        for(Iterator iterator = toUpCon.iterator(); iterator.hasNext();){
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

}
