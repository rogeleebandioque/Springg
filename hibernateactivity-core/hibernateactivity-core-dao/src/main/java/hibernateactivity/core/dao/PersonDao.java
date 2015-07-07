package hibernateactivity.core.dao;
import java.util.*;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Roles;
import hibernateactivity.core.model.Contacts;

interface PersonDao{
    public List<Person> getPeople(String listBy, String orderBy);
    public Roles getRole(Integer category);
    public String deletePeople(int idNum);
    public boolean inRecord(int idNum);
    public String addPeople(Person person);
    public String updatePeople(Person person);
    public Person getPeople(int idNum);
    public List<Person> searchPeople(String search);
}
