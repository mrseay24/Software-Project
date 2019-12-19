
public class Room {
String floor;
String roomNumber;
String rate;
String status;
String resPeriod;
String occupant;
public Room(String roomNumber, String floor, String rate, String status, String res, String occupant){
	this.resPeriod=res;
	this.occupant=occupant;
	this.floor=floor;
	this.roomNumber=roomNumber;
	this.rate=rate;
	this.status=status;
}
public String getFloor(){
	return this.floor;
}
public String getRoomNumber(){
	return this.roomNumber;
}
public String getRate(){
	return this.rate;
}
public String getStatus(){
	return this.status;
}
}
