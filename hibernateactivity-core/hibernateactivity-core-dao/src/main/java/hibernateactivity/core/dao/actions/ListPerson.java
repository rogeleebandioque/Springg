package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import hibernateactivity.core.model.Person;
import org.hibernate.Session;
import java.util.*;
import org.hibernate.*;
import org.hibernate.sql.JoinType;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;

public class ListPerson implements Command {

    private Session session;

    public ListPerson() {
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Object execute() {
        List<Person> persons = null;  
        Criteria cr = session.createCriteria(Person.class);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        persons = cr.list();

        /*Criteria criteria = session.createCriteria(Person.class);
        criteria.setProjection(Projections.projectionList()
        .add(Projections.property("id"), "id")
        .add(Projections.property("names"), "names")
        .add(Projections.property("grade"), "grade")
        .add(Projections.property("date_hired"), "date_hired")
        .add(Projections.property("contact"), "contact"));
        persons = criteria.setResultTransformer(Transformers.aliasToBean(Person.class)).list();
        */

        return persons;
    }
}
