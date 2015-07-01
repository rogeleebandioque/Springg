package hibernateactivity.core.dao.actions;

import hibernateactivity.core.dao.Command;
import org.hibernate.Session;

/**
 * Created by ddevera on 6/29/15.
 */
public class Exists implements Command {

    private Session session;
    private Integer id;

    public Exists(Integer id) {
        this.id = id;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Object execute() {
        return session.createQuery("FROM Person WHERE contact.id =" + id).list().isEmpty();
    }
}
