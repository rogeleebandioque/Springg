package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import hibernateactivity.core.model.Person;
import org.hibernate.Session;

public class Save implements Command {

    private Session session;
    private Person person;

    public Save(Person person) {
        this.person = person;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Object execute() {
        session.save(person);
        return session.createQuery("FROM Person WHERE id =" + person.getId()).list().isEmpty();
    }
}
