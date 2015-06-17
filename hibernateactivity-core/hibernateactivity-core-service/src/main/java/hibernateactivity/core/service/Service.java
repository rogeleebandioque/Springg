package hibernateactivity.core.service;

import  hibernateactivity.core.dao.Dao;
import hibernateactivity.core.model.Person;
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
}
