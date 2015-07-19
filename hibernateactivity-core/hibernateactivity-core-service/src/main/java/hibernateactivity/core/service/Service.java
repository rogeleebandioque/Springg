package hibernateactivity.core.service;

import hibernateactivity.core.dao.PersonDaoImpl;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Roles;
import java.util.List;

public class Service {

    //PersonDaoImpl personDaoImpl = new PersonDaoImpl();
/*
    public void setPersonDaoImpl(PersonDaoImpl personDaoImpl){
        this.personDaoImpl = personDaoImpl;
    }
*/
    public List<Person> getPersons() {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.getPeople();
    }

    public String deletePersons(int idNum) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.deletePeople(idNum);
    }

    public boolean searchPersons(int idNum) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.inRecord(idNum);
    }

    public String addPersons(Person person) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.addPeople(person);
    }

    public String updatePersons(Person person) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.updatePeople(person);
    }

    public Person getPersons(int idNum) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.getPeople(idNum);
    }

    public Roles getByRole(Integer category) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.getRole(category);
    }

    public List<Person> searchPerson(String searchQ, String listBy, String order) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        return personDaoImpl.searchPeople(searchQ, listBy, order);
    }
}
