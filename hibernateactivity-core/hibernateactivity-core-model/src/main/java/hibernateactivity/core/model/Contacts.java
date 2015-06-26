package hibernateactivity.core.model;

public class Contacts {

    private int id;
    private int person_id;
    private String type;
    private String contact;

    public Contacts() {}

    public Contacts(String contact, String type) {
        this.contact = contact;
        this.type = type;    
    }
    
    public int getId() {
        return this.id;    
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getPerson_id() {
        return this.person_id;    
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getContact() {
        return this.contact;    
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        
        Contacts obj2 = (Contacts)obj;

        if((this.id == obj2.getId()) && (this.contact.equals(obj2.getContact()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( id + contact ).hashCode();
        return tmp;
    }   


}
