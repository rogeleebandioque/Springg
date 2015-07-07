package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import hibernateactivity.core.model.Person;
import org.hibernate.Session;
import java.util.*;
import org.hibernate.*;
import org.hibernate.sql.JoinType;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;


public class SearchPerson implements Command {

    private Session session;
    private String search;

    public SearchPerson(String search) {
        this.search = search;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Object execute() {
        List<Person> persons = null;  
        Criteria criteria = session.createCriteria(Person.class);
        criteria.add(Restrictions.like("names.last_name", search, MatchMode.ANYWHERE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        persons = criteria.list();
        return persons;
    }
}
