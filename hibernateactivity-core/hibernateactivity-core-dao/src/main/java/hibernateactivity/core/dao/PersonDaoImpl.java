package hibernateactivity.core.dao;

import hibernateactivity.core.dao.actions.DeletePerson;
import hibernateactivity.core.dao.actions.Exists;
import hibernateactivity.core.dao.actions.GetPerson;
import hibernateactivity.core.dao.actions.Save;
import hibernateactivity.core.dao.actions.Update;
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
import org.hibernate.*;
import org.hibernate.sql.JoinType;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;

public class PersonDaoImpl implements PersonDao {

    private static SessionFactory factory;

    public PersonDaoImpl() {
        try {
            factory =  new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();       
        } catch(Throwable ex) {
            ex.printStackTrace();
            System.err.println("Failed to create sessionFactory object" + ex);
            //throw new ExceptionInInitializeError(ex);
         }
    }

    public List<Person> getPeople(String listBy, String orderBy) {
        Session session = factory.openSession();
        Transaction tx = null;      
        List<Person> persons = null;  
        String sql = null;

        try {
            tx = session.beginTransaction();
            if(!(listBy.equals("grade") || listBy.equals("last_name"))) {
                Criteria cr = session.createCriteria(Person.class); 
                if(orderBy.equals("desc")) {
                    cr.addOrder(Order.desc(listBy));
                } else { 
                    cr.addOrder(Order.asc(listBy));    
                }
                
                persons = cr.list();
            } else if(listBy.equals("last_name")) {              
                if(orderBy.equals("asc")){
                    sql = "FROM Person ORDER BY names.last_name ASC";             
                } else {
                    sql = "FROM Person ORDER BY names.last_name DESC";          
                }
                persons  = session.createQuery(sql).list();
            } else {
                sql = "From Person";
                persons  = session.createQuery(sql).list();
            }
            tx.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return persons;
    }

    public String deletePeople(int idNum) {
        return HibernateUtil.perform(new DeletePerson(idNum), Boolean.class) ? "Person Deleted!" : "Person not Deleted!";
    }

    public boolean inRecord(int idNum) {
        return HibernateUtil.perform(new Exists(idNum), Boolean.class);
    }

    public String addPeople(Person person) {
        return HibernateUtil.perform(new Save(person), String.class) != null ? "Added!" : "Not Added!";
    }

    public String updatePeople(Person person) {
        person = HibernateUtil.perform(new Update(person), Person.class);
        return person != null ? "Updated!" : "Not Updated!";
    }

    public Person getPeople(int idNum) {
        return HibernateUtil.perform(new GetPerson(idNum), Person.class);
    }
    
}
