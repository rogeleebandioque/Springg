package hibernateactivity.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
                    sql = "FROM Person ORDER BY names.last_name ASC";             
                } else {
                    sql = "FROM Person ORDER BY names.last_name DESC";          
                }
                persons  = session.createQuery(sql).list();
            } else {
                sql = "Select p.id as id, p.names as names ,p.date_hired as date_hired, p.grade as grade from Person as p";    
                persons  = session.createQuery(sql).setResultTransformer(Transformers.aliasToBean(Person.class)).list();
                //sql = "From Person";
                //persons  = session.createQuery(sql).list();
                for(Person pc:persons){
        
                    sql = "from Contacts where person_id = :person_id";
                    Set<Contacts> setsContact = new HashSet();   
                    List<Contacts> cont = session.createQuery(sql).setParameter("person_id", pc.getId()).list();         
                    for(Contacts eachCont:cont){
                        setsContact.add(eachCont);
                    }
                    pc.setContact(setsContact);                
                }   
               /* Criteria criteria = session.createCriteria(Person.class);
                          criteria.setProjection(Projections.projectionList()
                                            .add(Projections.property("id"), "id")
                                            .add(Projections.property("names"), "names")
                                            .add(Projections.property("address"), "address")
                                            .add(Projections.property("grade"), "grade")
                                            .add(Projections.property("date_hired"), "date_hired")
                                            .add(Projections.property("bday"), "bday")
                                            .add(Projections.property("currently_employed"), "currently_employed")
                                            .add(Projections.property("contact"), "contact"));
                                            
                persons  = criteria.setResultTransformer(Transformers.aliasToBean(Person.class)).list();*/
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
