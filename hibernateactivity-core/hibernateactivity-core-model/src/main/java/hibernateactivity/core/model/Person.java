package hibernateactivity.core.model;

import java.util.*;

public class Person implements Comparable<Person> {

    private int id;    
    private Name names;
    private String address;
    private Set<Contacts> contact;
    private int age;
    private String gender;
    private Date bday;
    private int grade;
    private Date date_hired;
    private String currently_employed;
    private Set<Roles> role;

    public Person() {}
    public Person(Name names, String address, int age, String gender, 
                  Date bday, int grade, Date date_hired, String currently_employed) {
        this.names = names;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.bday = bday;
        this.grade = grade;
        this.date_hired = date_hired;
        this.currently_employed = currently_employed;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }    

    public void setNames(Name names){
        this.names = names;
    }

    public Name getNames(){
        return names;
    }

    public void setAddress(String address){
        this.address = address;
    }//2

    public String getAddress(){
        return address;
    }//2

    public void setContact(Set<Contacts> contact){
        this.contact = contact;
    }//3

    public Set<Contacts> getContact(){
        return contact;
    }//3

    public void setAge(int age){
        this.age = age;
    }//4

    public int getAge(){
        return age;
    }//4

    public void setGender(String gender){
       this.gender = gender;
    }//5

    public String getGender(){
        return gender;
    }//5

    public void setBday(Date bday){
        this.bday = bday;
    }//6
    
    public Date getBday(){
        return bday;
    }//6

    public void setGrade(int grade){
        this.grade = grade;
    }//7

    public int getGrade(){
        return grade;
    }//7

    public void setDate_hired(Date date_hired){
        this.date_hired = date_hired;
    }//8

    public Date getDate_hired(){
        return date_hired;
    }//8

    public void setCurrently_employed(String currently_employed){
        this.currently_employed = currently_employed;
    }//9

    public String getCurrently_employed(){
        return currently_employed;
    }//9
    
    public void setRole(Set<Roles> role){
        this.role = role;
    }

    public Set<Roles> getRole(){
        return role;
    }

    public int getComparison(){
        return grade;
    }

    public int compareTo(Person person){
        //return (this.getComparison().compareTo(person.getComparison());
  	    return (this.grade < person.grade ) ? -1: (this.grade > person.grade ) ? 1:0 ;        
    }
}
