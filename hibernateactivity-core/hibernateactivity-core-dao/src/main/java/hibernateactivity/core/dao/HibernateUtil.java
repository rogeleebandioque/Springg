package hibernateactivity.core.dao;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;
    private static Session session;
    private static Transaction transaction;
    
    public static void init() {
        try {
            factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
        } catch (Throwable e) {
            System.err.println("Failed to create session factory object.");
            e.printStackTrace();
        }
    }
    
    public static <T> T perform(Command command, Class<T> returnClass) {
        if(session == null) {
            init();
        }
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        Object returnObject = null;
        
        try {
            command.setSession(session);
            returnObject = command.execute();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Unable to perform transaction.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return returnClass.cast(returnObject);
    }


}
