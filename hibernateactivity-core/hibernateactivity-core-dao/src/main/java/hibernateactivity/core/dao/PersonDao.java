package hibernateactivity.core.dao;
import java.util.*;
import hibernateactivity.core.model.*;

interface PersonDao{
    public List<Person> getPeople();
    public String deletePeople(int idNum);
    public boolean inRecord(int idNum);
    public String addPeople(Person person);
    public String updatePeople(Person person);
    public Person getPeople(int idNum);
}
