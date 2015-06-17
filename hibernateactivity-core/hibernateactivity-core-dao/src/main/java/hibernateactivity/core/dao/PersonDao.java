package hibernateactivity.core.dao;
import java.util.*;
import hibernateactivity.core.model.Person;

interface PersonDao{
    public List<Person> getPeople();
    public String deletePeople(int idNum);
    public boolean inRecord(int idNum);
   
}
