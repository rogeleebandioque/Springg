package hibernateactivity.core.model;

public class Person 
{
    private int id;
	private String name;
	private String address;
	private String contact;
	private int age;
	private String gender;
	private String bday;
	private int grade;
	private String date_hired;
	private String currently_employed;

	public Person(){}

	public Person(String name, String address, String contact, int age, String gender, String bday, int grade, String date_hired, String currently_employed){
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.age = age;
		this.gender = gender;
		this.bday = bday;
		this.grade = grade;
		this.date_hired = date_hired;
		this.currently_employed = currently_employed;
	}

	public int getId(){
		return id;
	}
    public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}//1
	public void setAddress(String address){
		this.address = address;
	}//2
	public void setContact(String contact){
		this.contact = contact;
	}//3
	public void setAge(int age){
		this.age = age;
	}//4
	public void setGender(String gender){
		this.gender = gender;
	}//5
	public void setBday(String bday){
		this.bday = bday;
	}//6
	public void setGrade(int grade){
		this.grade = grade;
	}//7
	public void setDate_hired(String date_hired){
		this.date_hired = date_hired;
	}//8
	public void setCurrently_employed(String currently_employed){
		this.currently_employed = currently_employed;
	}//9

	public String getName(){
		return name;
	}//1
	public String getAddress(){
		return address;
	}//2
	public String getContact(){
		return contact;
	}//3
	public int getAge(){
		return age;
	}//4
	public String getGender(){
		return gender;
	}//5
	public String getBday(){
		return bday;
	}//6
	public int getGrade(){
		return grade;
	}//7
	public String getDate_hired(){
		return date_hired;
	}//8
	public String getCurrently_employed(){
		return currently_employed;
	}//9
}
