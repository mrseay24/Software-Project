
public class Reservation {
String RoomNum;
String ResPeriod;
String CustName;
	
	public Reservation(String RoomNum, String ResPeriod, String CustName){
		this.RoomNum=RoomNum;
		this.ResPeriod=ResPeriod;
		this.CustName=CustName;
		
		
	}
	public String getRoomNum(){
		return this.RoomNum;
	}
	public String getResPeriod(){
		return this.ResPeriod;
	}
	public String getCustName(){
		return this.getCustName();
	}

}
