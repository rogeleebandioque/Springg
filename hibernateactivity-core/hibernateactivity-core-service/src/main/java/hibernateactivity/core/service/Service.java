package hibernateactivity.core.service;

import  hibernateactivity.core.dao.PersonDaoImpl;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import java.util.List;

public class Service {

    public List<Person> getPersons() {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.getPeople();
    }

    public String deletePersons(int idNum) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.deletePeople(idNum);
    }

    public boolean searchPersons(int idNum) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.inRecord(idNum);
    }

    public String addPersons(Person person) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.addPeople(person);
    }

    public String updatePersons(Person person) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.updatePeople(person);
    }

    public Person getPersons(int idNum) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.getPeople(idNum);
    }
}
