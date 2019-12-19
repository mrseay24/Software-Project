
public class Account {
	//SUPERKEY(USERNAME, PASSWORD,SSN) 
String UserName;
String Password;
String FirstName;
String LastName;
String Type;
String SSN;
	public Account(String fname,String lname ,String username, String password, String TYPE, String SSN){
		this.UserName=username;
		this.Password=password;
		this.FirstName=fname;
		this.LastName=lname;
		this.Type=TYPE;
		//SSN for STAFF
		this.SSN=SSN;
		
	}
	public Account(String fname,String lname ,String username, String password, String TYPE){
		this.UserName=username;
		this.Password=password;
		this.FirstName=fname;
		this.LastName=lname;
		this.Type=TYPE;
		//SSN NOT NECESSARY FOR CUSTOMERS, STILL UNIQUELY IDENTIFIABLE 
		this.SSN="N/A";
	}
	public String getUserName(){
		return this.UserName;
	}
	public String getPassword(){
		return this.Password;
	}
	
	public String getType(){
		return this.Type;
	}
	public String getSSN(){
		return this.SSN;
	}

}
