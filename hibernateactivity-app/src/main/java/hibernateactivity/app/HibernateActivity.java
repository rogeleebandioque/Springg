package hibernateactivity.app;

import java.util.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
public class HibernateActivity 
{
    public static void main( String[] args )
    {
        Service service = new Service();
    	boolean choice = true;
			while(choice)
			{
				try
				{
					int input = Integer.parseInt(userInput("Enter Choice: \n [1] List [2] Add [3] Delete [4] Edit [5] Exit"));
					switch (input){
						case 1: 
							List<Person> person = service.getPersons();
                            displayPerson(person);
							break;
						case 2: 
	                        
							break;
						case 3: 
		                    int idNum = delPerson();
                            String mes = service.deletePersons(idNum);
                            System.out.print(mes);
							break;
						case 4: 
                            System.out.println(service.searchPersons(Integer.parseInt(userInput("Enter Id: "))));
							break;
						case 5: 
							choice = false;	
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
                  System.out.print("ID: " + persons.getId());
	              System.out.println(" Name: " + persons.getName());
            }
    }
    public static void addPerson(){
       // System.out.println("Add Person: ");
    }
    public static int delPerson(){
        boolean i = true;   
         int idNo = 0;//try  lang  
        while(i){
            try{
               idNo = Integer.parseInt(userInput("Insert Person ID Number: ")); 
                i = false;
            }catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }
        return idNo;
    }
    public static String userInput(String message){
        System.out.println(message);        
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        return input;
    }
}
