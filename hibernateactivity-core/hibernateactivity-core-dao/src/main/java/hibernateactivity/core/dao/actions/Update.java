package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import hibernateactivity.core.model.Person;
import org.hibernate.Session;

public class Update implements Command {

    private Session session;
    private Person person;

    public Update(Person person) {
        this.person = person;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Object execute() {
        session.update(person);
        return session.createQuery("FROM Person WHERE id =" + person.getId()).list().isEmpty();
    }
}
