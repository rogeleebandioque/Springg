package hibernateactivity.core.service;

import  hibernateactivity.core.dao.PersonDaoImpl;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Roles;
import java.util.List;

public class Service {

    public List<Person> getPersons(String listBy, String orderBy) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.getPeople(listBy, orderBy);
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

    public Roles getByRole(Integer category) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.getRole(category);
    }

    public List<Person> searchPerson(String searchQ) {
        PersonDaoImpl dao = new PersonDaoImpl();
        return dao.searchPeople(searchQ);
    }
}
