package hibernateactivity.core.dao;

import hibernateactivity.core.dao.actions.DeletePerson;
import hibernateactivity.core.dao.actions.Exists;
import hibernateactivity.core.dao.actions.GetPerson;
import hibernateactivity.core.dao.actions.Save;
import hibernateactivity.core.dao.actions.Update;
import hibernateactivity.core.dao.actions.ListPerson;
import hibernateactivity.core.dao.actions.ListRoles;
import hibernateactivity.core.dao.actions.SearchPerson;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Roles;
import java.util.*;



public class PersonDaoImpl implements PersonDao {

    public List<Person> getPeople() {
        return HibernateUtil.perform(new ListPerson(), List.class);         
    }

    public String deletePeople(int idNum) {
        return HibernateUtil.perform(new DeletePerson(idNum), Boolean.class) ? "Deleted!" : "Unable to Delete!";
    }

    public boolean inRecord(int idNum) {
        return HibernateUtil.perform(new Exists(idNum), Boolean.class);
    }

    public String addPeople(Person person) {
        return HibernateUtil.perform(new Save(person), Boolean.class) ? "Unable to Add" : "Added";
    }

    public String updatePeople(Person person) {
        return HibernateUtil.perform(new Update(person), Boolean.class) ? "Unable to Update!" : "Updated!";
    }

    public Person getPeople(int idNum) {
        return HibernateUtil.perform(new GetPerson(idNum), Person.class);
    }

    public Roles getRole(Integer category) {
        return HibernateUtil.perform(new ListRoles(category), Roles.class);
    }
   
    public List<Person> searchPeople(String search, String listBy, String order) {
        return HibernateUtil.perform(new SearchPerson(search,listBy,order), List.class);
    }
}
