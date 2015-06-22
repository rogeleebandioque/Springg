package hibernateactivity.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernateactivity.core.model.Person;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private static SessionFactory factory;

    public PersonDaoImpl() {
        try {
            factory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();       
        } catch(Throwable ex) {
            ex.printStackTrace();
            System.err.println("Failed to create sessionFactory object" + ex);
            //throw new ExceptionInInitializeError(ex);
         }
    }

    public List<Person> getPeople() {
        Session session = factory.openSession();
        Transaction tx = null;      
        List persons = null;  

        try {
            tx = session.beginTransaction();
            persons = session.createQuery("FROM Person").list();
            tx.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return persons;
    }

    public String deletePeople(int idNum) {
        Session session = factory.openSession();
        Transaction tx = null; 

        try {
	        tx = session.beginTransaction();
	        Person person =(Person)session.get(Person.class, idNum);
	        session.delete(person);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
        return "Person Deleted";
    }

    public boolean inRecord(int idNum) {
        Session session = factory.openSession();
        Transaction tx = null; 
        List persons = null;
        boolean y = true;
            
        try {
            tx = session.beginTransaction();
            persons = session.createQuery("FROM Person WHERE id ="+idNum).list();
            tx.commit();
        } catch(HibernateException e) {
           	if (tx!=null) tx.rollback();
	        e.printStackTrace();
        } finally {
            session.close();
        }

        if (persons.isEmpty()) {
            y = false;
        }
        return y;    
    }

    public String addPeople(Person person) {
        Session session = factory.openSession();
        Transaction tx = null;  

        try {
	        tx = session.beginTransaction();
	        session.save(person);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
        return "Added!";
    }

    public String updatePeople(Person person) {
        Session session = factory.openSession();
        Transaction tx = null;
        String mes = null;

        try {
	        tx = session.beginTransaction();
	        session.update(person);
	        tx.commit();
            mes = "Updated!";
	    } catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
        return mes;
    }

    public Person getPeople(int idNum) {
        Session session = factory.openSession();
        Transaction tx = null;
        Person people = null;        

        try {
            tx = session.beginTransaction();
            people = (Person)session.get(Person.class, idNum);
            tx.commit();
        } catch(HibernateException e) {
            
        } finally {
            session.close();
        }
        return people;
    }    
    
}
