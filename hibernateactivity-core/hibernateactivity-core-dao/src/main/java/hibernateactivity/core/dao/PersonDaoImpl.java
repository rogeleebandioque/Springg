package hibernateactivity.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Name;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;

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
                    sql = "FROM Person ORDER BY last_name ASC";             
                } else {
                    sql = "FROM Person ORDER BY last_name DESC";          
                }
                persons  = session.createQuery(sql).list();
            } else {
                sql = "from Person"; 
                persons  = session.createQuery(sql).list();//setResultTransformer(Transformers.aliasToBean(Person.class)).list();
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
