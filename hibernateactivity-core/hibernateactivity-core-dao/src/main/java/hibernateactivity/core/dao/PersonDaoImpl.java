package hibernateactivity.core.dao;

import hibernateactivity.core.dao.actions.DeletePerson;
import hibernateactivity.core.dao.actions.Exists;
import hibernateactivity.core.dao.actions.GetPerson;
import hibernateactivity.core.dao.actions.Save;
import hibernateactivity.core.dao.actions.Update;
import hibernateactivity.core.dao.actions.ListPerson;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Contacts;
import java.util.*;


public class PersonDaoImpl implements PersonDao {

    public List<Person> getPeople(String listBy, String orderBy) {
        return HibernateUtil.perform(new ListPerson(listBy,orderBy), List.class);         
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
    
}
