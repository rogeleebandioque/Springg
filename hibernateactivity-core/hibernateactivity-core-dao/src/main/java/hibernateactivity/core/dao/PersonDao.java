package hibernateactivity.core.dao;
import java.util.*;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;

interface PersonDao{
    public List<Person> getPeople(String listBy, String orderBy);
    public String deletePeople(int idNum);
    public boolean inRecord(int idNum);
    public String addPeople(Person person);
    public String updatePeople(Person person);
    public Person getPeople(int idNum);

}
