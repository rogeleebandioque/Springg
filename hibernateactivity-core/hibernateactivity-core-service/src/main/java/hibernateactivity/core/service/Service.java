package hibernateactivity.core.service;

import  hibernateactivity.core.dao.Dao;
import hibernateactivity.core.model.*;
import java.util.*;

public class Service 
{
    public List<Person> getPersons(){
        Dao dao = new Dao();
        return dao.getPeople();
    }
    public String deletePersons(int idNum){
        Dao dao = new Dao();
        return dao.deletePeople(idNum);
    }

    public boolean searchPersons(int idNum){
        Dao dao = new Dao();
        return dao.inRecord(idNum);
    }
    public String addPersons(Person person){
        Dao dao = new Dao();
        return dao.addPeople(person);
    }
    public String updatePersons(Person person){
        Dao dao = new Dao();
        return dao.updatePeople(person);
    }
    public Person getPersons(int idNum){
        Dao dao = new Dao();
        return dao.getPeople(idNum);
    }
}
