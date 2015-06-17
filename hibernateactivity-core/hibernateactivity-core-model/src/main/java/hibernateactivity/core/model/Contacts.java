public class Contacts{
    private int id;
    private String contact;
    public Contacts(){}

    public Contacts(String contact){
        this.contact = contact;
    }
    
    public int getId(){
        return id;    
    }
    public String getContact(){
        return contact;    
    }
    public void setId(int id){
        this.id = id;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
}
