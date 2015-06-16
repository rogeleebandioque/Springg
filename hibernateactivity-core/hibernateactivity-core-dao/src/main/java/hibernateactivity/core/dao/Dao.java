package hibernateactivity.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernateactivity.core.model.Person;
import java.util.*;

public class Dao implements PersonDao
{
    private static SessionFactory factory;
    public Dao(){
        try{
            factory =  new Configuration().configure().buildSessionFactory();       
        }catch(Throwable ex){
            ex.printStackTrace();
            System.err.println("Failed to create sessionFactory object" + ex);
            //throw new ExceptionInInitializeError(ex);
         }
    }

    public List<Person> getPeople(){
        Session session = factory.openSession();
        Transaction tx = null;      
        List persons = null;  
        try{
            tx = session.beginTransaction();
            persons = session.createQuery("FROM Person").list();
            tx.commit();
        }catch(HibernateException e){
            
        }finally{
            session.close();
        }
    return persons;
    }
    public String deletePeople(int idNum){
        Session session = factory.openSession();
        Transaction tx = null; 
         try{
	        tx = session.beginTransaction();
	        Person person =(Person)session.get(Person.class, idNum);
	        session.delete(person);
	        tx.commit();
	    }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace();
	    }finally {
	        session.close();
	    }
    return "Person Deleted";
    }
}
