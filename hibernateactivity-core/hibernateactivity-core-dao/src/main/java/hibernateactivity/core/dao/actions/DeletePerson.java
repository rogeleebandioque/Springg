package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import hibernateactivity.core.model.Person;
import org.hibernate.Session;

public class DeletePerson implements Command {

    private Session session;
    private Integer id;

    public DeletePerson(Integer id) {
        this.id = id;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Object execute() {
        Person person = (Person) session.get(Person.class, id);
        session.delete(person);
        
        return new Object();
    }
}
