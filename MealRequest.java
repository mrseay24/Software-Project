
public class MealRequest {
	private String customerUserName;
	private String Room;
	private String Comments;

	public MealRequest(String customerUserName, String Room, String Comments){
		this.customerUserName=customerUserName;
		this.Room=Room;
		this.Comments=Comments;
	}
	public String getCustomerUserName(){
		return this.customerUserName;
	}
	public String getRoom(){
		return this.Room;
	}
	public String getMealRequestComments(){
		return this.Comments;
	}
}
