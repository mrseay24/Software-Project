
public class Employee {
	String payRate;
	String ssn;
	String firstName;
	String lastName;
	public Employee(String firstName, String lastName, String ssn, String payRate){
		this.payRate=payRate;
		this.ssn=ssn;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public Employee(){
		this.payRate="8.25";
		this.ssn="123456789";
		this.firstName="default";
		this.lastName="default";
		
	}
	
	public String getPayRate(){
		return this.payRate;
	}
	public String getSSN(){
		return this.ssn;
	}
	public String getFname(){
		return this.firstName;
	}
	public String getLname(){
		return this.lastName;
	}
	public void setPayRate(String payrate){
		 this.payRate=payrate;
	}
	public void setSSN(String ssn){
		 this.ssn=ssn;
	}
	public void setFname(String Fname){
		this.firstName=Fname;
	}
	public void setLname(String Lname){
			this.lastName=Lname;
	}

}
