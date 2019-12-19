
public class ServiceRequest {
private String customerUserName;
private String Room;
private String Request;

public ServiceRequest(String customerUserName, String Room, String Request){
	this.customerUserName=customerUserName;
	this.Room=Room;
	this.Request=Request;
}
public String getCustomerUserName(){
	return this.customerUserName;
}
public String getRoom(){
	return this.Room;
}
public String getRequest(){
	return this.Request;
}
}
