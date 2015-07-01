package hibernateactivity.core.model;
import javax.persistence.*;

public class Contacts {

    private int contact_id;
    private String type;
    private String contact;

    public Contacts() {}

    public Contacts(String contact, String type) {
        this.contact = contact;
        this.type = type;    
    }
    
    public int getContact_id() {
        return this.contact_id;    
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
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

        if((this.contact_id == obj2.getContact_id()) && (this.contact.equals(obj2.getContact()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( contact_id + contact ).hashCode();
        return tmp;
    }   


}
