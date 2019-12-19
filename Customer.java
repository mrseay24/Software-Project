
public class Customer {
	String fname;
	String lname;
	String phoneNumber;
	String cardnumber;
	String username;
	String Address;
	String CityStateZip;
	double balance;
	
	public Customer(String firstname, String lastname, String phonenumber, String cardnumber,
			String username, String Address, String CityStateZip){
		this.fname=firstname;
		this.lname=lastname;
		this.phoneNumber=phonenumber;
		this.cardnumber=cardnumber;
		this.username=username;
		this.Address=Address;
		this.CityStateZip=CityStateZip;
		this.balance=0.00;
	}
	public Customer(String firstname, String lastname, String phonenumber, String cardnumber,
			String username, String Address, String CityStateZip, double balance){
		this.fname=firstname;
		this.lname=lastname;
		this.phoneNumber=phonenumber;
		this.cardnumber=cardnumber;
		this.username=username;
		this.Address=Address;
		this.CityStateZip=CityStateZip;
		this.balance=balance;
	}
	public String getFirstName(){
		return this.fname;
	}
	public String getLastName(){
		return this.lname;
	}
	public void setName(String fname, String lname){
		this.fname=fname;
		this.lname=lname;
		
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public String getCardNumber(){
		return this.cardnumber;
	}
	public double getBalance(){
		return this.balance;
	}
	public void increaseBalance(double charge){
		 this.balance+=charge;
	}
	public void decreaseBalance(double discount){
		this.balance-=discount;
	}

}
