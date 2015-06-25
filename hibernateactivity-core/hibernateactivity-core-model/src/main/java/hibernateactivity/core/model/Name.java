package hibernateactivity.core.model;

public class Name {
    private String f_name;
    private String l_name;
    private int id;

    public Name() {}
    public Name(String f_name, String l_name) {
        this.f_name = f_name;
        this.l_name = l_name;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;        
    }

    public String getF_name() {
        return f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;        
    }

    public String getL_name() {
        return l_name;
    }

}
