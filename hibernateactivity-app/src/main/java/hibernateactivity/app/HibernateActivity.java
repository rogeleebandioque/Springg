package hibernateactivity.app;
import org.apache.commons.validator.routines;
import java.util.*;
import java.text.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.*;

public class HibernateActivity 
{
    public static void main( String[] args )
    {
        Service service = new Service();
    	boolean choice = true;
			outer: while(choice)
			{
				try
				{
					int input = Integer.parseInt(userInput("Enter Choice: \n [1] List [2] Add [3] Delete [4] Edit"));
					switch (input){
						case 1: 
							List<Person> person = service.getPersons();
                            displayPerson(person);
							break;
						case 2: 
                            Person addPer = addPerson();
                            System.out.println(service.addPersons(addPer));	 
                            processContacts(contacts, addPer.getId());                       
							break;
						case 3: 
		                    int idNum = integerValid(userInput("Enter ID# to be deleted"),"ID#");
                            boolean exist = service.searchPersons(idNum);
                            if(exist){
                                String mes = service.deletePersons(idNum);
                                System.out.print(mes);
                            }else{
                                System.out.print("ID does not exist!");
                            }
							break;
						case 4: 
                            int idNo = integerValid(userInput("Enter ID to update: "),"ID to update");
                            boolean existUp = service.searchPersons(idNo);
                           // Person people = null;                            
                            if(existUp){
                                Person people = service.getPersons(idNo);
                                String toEdit = stringValid(userInput("Edit What? "),"Category to Edit");
                                people = editPerson(toEdit, people);
                                String message = service.updatePersons(people);
                                System.out.println(message);
                            }
							break;
						default:
							System.out.println("Not in Choices!");
							break;
					}
					
				}catch(NumberFormatException e){
					System.out.println("Invalid Input");
				}
			}
    }
    public static void displayPerson(List<Person> person){
        for(Iterator iterator = person.iterator(); iterator.hasNext();){
                  Person persons = (Person) iterator.next();
                  //System.out.print("ID: " + persons.getId());
	              System.out.println(" Name: " + persons.getName());
            }
    }
    public static Person editPerson(String edit, Person person){
       edit = edit.toLowerCase().trim();
        switch (edit){
            case "name":
                person.setName(stringValid(userInput("Enter New Name:"),"Name"));            
            break;
            case "address":
                person.setAddress(stringValid(userInput("Enter New Address"),"Address"));
            break;
            case "age":
                person.setAge(integerValid(userInput("Enter New Age"),"Age"));
            break;
            default:
                System.out.println("Not in category");
            break;
        }
        return person;
    }
    public static String userInput(String message){
        System.out.println(message);        
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }
    public static Person addPerson(){
        Person newPerson = new Person();
       
        newPerson.setName(stringValid(userInput("Enter Person's Name: "),"Name: "));
        newPerson.setAddress(stringValid(userInput("Enter Person's Address: "),"Name: "));
        newPerson.setContact(contactDetails());
        newPerson.setAge(integerValid(userInput("Enter Person's Age: "),"Age: "));
        newPerson.setGender(genderValid(integerValid(userInput("Enter Person's Gender [1] Male [2] Female: "),"Answer: "),"Gender [1] Male [2] Female: "));
        newPerson.setBday(dateValid(userInput("Enter Person's Birth date (MM/dd/yyyy): "), "Birth date (MM/dd/yyyy): "));
        newPerson.setGrade(integerValid(userInput("Enter Person's Grade: "),"Grade: "));
        newPerson.setDate_hired(dateValid(userInput("Enter Person's Date Hired: "),"Date Hired: "));
        newPerson.setCurrently_employed(employmentValid(userInput("Currently Employed? [yes] [no]")));    
       
       return newPerson;    
    }
    public static String stringValid(String check, String categ){
        while(check.equals("")){
            check = userInput("Enter Valid "+ categ);
        }
        return check; 
       
    }
    public static int integerValid(String in, String categ){
        boolean a = true;
        int input = 0;
        while(a){
            try{
                input = Integer.parseInt(in);
                a = false;
            }catch(NumberFormatException e){
                in = userInput("Enter valid "+categ);
            }
        }
        return input;
    }
    public static Date dateValid(String date, String categ){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
        Date dates = null;
        boolean d=true;
        while(d){   
            try{                    
               dates = formatter.parse(date);
               d = false;
            }catch(ParseException e){
              date = userInput("Enter valid "+categ);
            }
        }
    return dates; 
    }
    public static String genderValid(int gender,String categ){
                
        while(true){
            if(gender ==1 || gender ==2){
            break; 
            }
            gender = integerValid(userInput("Enter valid " + categ), categ);        
        }

        String genderV = "";
        if(gender == 1){
            genderV = "Male";
        }else if(gender == 2){
            genderV = "Female";
        }
        return genderV;    
    }
    public static String employmentValid(String status){
        status = status.toLowerCase().trim();
        while (true){
            if(!status.equals("yes") || !status.equals("no")){
                break;
            }
            status = stringValid(userInput("Enter valid answer: "), "Enter valid answer: ");
        }
        return status; 
    }
    public static String emailValid(String email, categ){
        while(true){
            if(EmailValidator.isValid(email) == true){
                break;
            }else{
                email = userInput("Enter Valid "+ categ);
            }
        }
        return email;
    }
    public static List<String> contactDetails(){
        List<String> cD = null;
        while (true){
            int cN = integerValid(userInput("Enter Contact details [1]E-mail [2]Cellphone# [3]Telephone#"), "details [1]E-mail [2]Cellphone# [3]Telephone#");
            switch(cN){
                case 1:
                    String email = emailValid(userInput("Enter E-Mail: "), "Email: ");
                    cD.add(email);
                break;
                case 2:
                    String cell = userInput("Enter Cellphone#");
                    cD.add(cell);
                break;
                    String phone = userInput("Enter Telephone#");
                    cD.add(phone);
                case 3:
                break;
                default:
                    System.out.println("Enter valid contact details");
                break;
            }
            des: while(true){
                int cont = integerValid(userInput("Add another contact? [1]Yes [2]No"),"answer [1]Yes [2]No: ");
                if(cont == 1){
                    break des;
                }else if(cont == 2){
                    break;
                }            
            }
        }
        return cD;
    }
}
