import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * This database is a flat-file database 
 * that does not perform any relational 
 * operations. That is, every instance or attribute
 * may be modified however no queries can be 
 * performed.
 * 
 */
public class DataBaseWriter {
	//Class variables
private ArrayList<Customer> CUSTOMERS = new ArrayList();
private ArrayList<Employee> EMPLOYEES = new ArrayList();
private ArrayList<Room> ROOMS = new ArrayList();
private ArrayList<Reservation> RESERVATIONS = new ArrayList();
private ArrayList<Account> ACCOUNTS = new ArrayList();
private ArrayList<MealRequest> MEALREQUESTS = new ArrayList();
private ArrayList<ServiceRequest> SERVICEREQUESTS = new ArrayList();
//CHANGE THE DIRECTORIES TO WHERE EVER THE TEXT FILES ARE LOCATED 
//ON THE MACHINE
private String customerFile = "/Users/macpro/Desktop/HMS/CUSTOMER";
private String employeeFile ="/Users/macpro/Desktop/HMS/EMPLOYEE";
private String roomFile = "/Users/macpro/Desktop/HMS/ROOM";
private String accountsFile = "/Users/macpro/Desktop/HMS/ACCOUNTS";
private String reservationsFile = "/Users/macpro/Desktop/HMS/RESERVATIONS";
private String mealRequestsFile = "/Users/macpro/Desktop/HMS/MEALREQUESTS";
private String serviceRequestsFile = "/Users/macpro/Desktop/HMS/SERVICEREQUESTS";
private File f = new File(employeeFile);
public DataBaseWriter() throws IOException{
	//CONFIGURATION OF FILES AND LISTS FOR TEMP DATA
	ArrayList<Customer> CUSTOMER = new ArrayList();
	ArrayList<Employee> EMPLOYEE = new ArrayList();
	ArrayList<Room> ROOM = new ArrayList();
	ArrayList<Reservation> RESERVATION = new ArrayList();
	ArrayList<Account> ACCOUNT = new ArrayList();
	ArrayList<MealRequest> MEALREQUEST = new ArrayList();
	ArrayList<ServiceRequest> SERVICEREQUESTS = new ArrayList();
	String customerFile = "/Users/macpro/Desktop/HMS/CUSTOMER";
	String employeeFile ="/Users/macpro/Desktop/HMS/EMPLOYEE";
	String roomFile = "/Users/macpro/Desktop/HMS/ROOM";
	String accountsFile = "/Users/macpro/Desktop/HMS/ACCOUNTS";
	String reservationsFile = "/Users/macpro/Desktop/HMS/RESERVATIONS";
	String mealRequestsFile = "/Users/macpro/Desktop/HMS/MEALREQUESTS";
	String serviceRequestsFile = "/Users/macpro/Desktop/HMS/SERVICEREQUESTS";
	File ef = new File(employeeFile);
	File cf = new File(customerFile);
	File rf = new File(roomFile);
	File resf = new File(reservationsFile);
	File af = new File(accountsFile);
	File mrf = new File(mealRequestsFile);
	File srf = new File(serviceRequestsFile);
	FileReader frEMP = new FileReader(employeeFile);
	FileReader frCUST = new FileReader(customerFile);
	FileReader frROOM = new FileReader(roomFile);
	FileReader frACCT = new FileReader(accountsFile);
	FileReader frRESV = new FileReader(reservationsFile);
	FileReader frMRF = new FileReader(mealRequestsFile);
	FileReader frSRF = new FileReader(serviceRequestsFile);
	BufferedReader brACCT = new BufferedReader(frACCT);
	BufferedReader brRESV = new BufferedReader(frRESV);
	BufferedReader brEMP = new BufferedReader(frEMP);
	BufferedReader brCUST = new BufferedReader(frCUST);
	BufferedReader brROOM = new BufferedReader(frROOM);
	BufferedReader brMRF = new BufferedReader(frMRF);
	BufferedReader brSRF = new BufferedReader(frSRF);
	String line = brEMP.readLine();
	//If no employees exist yet.
	if(line==null||line.toCharArray().length==0){
		//System.out.println("No Employees currently in db");
		brEMP.close();
	}
	else{
		while(line!=null){
			String tokens[] = line.split("\t");
			Employee e = new Employee(tokens[0],tokens[1], tokens[2],tokens[3]);
			EMPLOYEES.add(e);
			line=brEMP.readLine();
			
			
		}
		brEMP.close();
	
	}
		
		for(int i=0;i<200;i++){
			String room = brROOM.readLine();
			String[] tokens = room.split("\t");
			Room r = new Room(tokens[0],tokens[1],tokens[2],
					tokens[3],tokens[4],tokens[5]);
			ROOMS.add(r);
			//System.out.println(room);
		}
		System.out.println(ROOMS.size()+" Rooms");
		String reservationLine = null;
		while((reservationLine=brRESV.readLine())!=null){
			String[] ReservationTokens = reservationLine.split("\t");
			Reservation r = new Reservation(ReservationTokens[0], 
					ReservationTokens[1], ReservationTokens[2]);
					RESERVATIONS.add(r);
		}
		String accountsLine=null;
		while((accountsLine=brACCT.readLine())!=null){
			String[] AccountTokens = accountsLine.split("\t");
			Account account = new Account(AccountTokens[0], AccountTokens[1], AccountTokens[2], 
					AccountTokens[3], AccountTokens[4], AccountTokens[5]);
			ACCOUNTS.add(account);
		}
		String srLine = null;
		while((srLine=brSRF.readLine())!=null){
			String[] serviceTokens = srLine.split("\t");
			ServiceRequest s = new ServiceRequest(serviceTokens[0], serviceTokens[1], serviceTokens[2]);
			SERVICEREQUESTS.add(s);
		}
		String customerLine = null;
		while((customerLine=brCUST.readLine())!=null){
			String[] customerTokens = customerLine.split("\t");
			double tempCharge = Double.parseDouble(customerTokens[7]);
			Customer cust = new Customer(customerTokens[0], customerTokens[1], customerTokens[2],
					customerTokens[3], customerTokens[4],
					customerTokens[5], customerTokens[6], tempCharge);
			CUSTOMERS.add(cust);
		}
		String mealRequestLine=null;
		while((mealRequestLine=brMRF.readLine())!=null){
			String[] mealRequestTokens = mealRequestLine.split("\t");
			MealRequest mr = new MealRequest(mealRequestTokens[0], mealRequestTokens[1], mealRequestTokens[2]);
			MEALREQUESTS.add(mr);
		}
	
}
		

/*
 * This method creates a new employee object, and appends to the db file
 * a new instance of an employee
 * 
 */
public void addCustomer(String FNAME, String LNAME, String PHONENUM, String CARDNUM, String UNAME, String ADDRESS,
		String CSZ) throws IOException{
	Customer c = new Customer(FNAME, LNAME, PHONENUM, CARDNUM, UNAME, ADDRESS, CSZ);
	FileWriter fw = new FileWriter(customerFile, true);
	PrintWriter pw = new PrintWriter(fw);
	pw.println(c.fname+"\t"+c.lname+"\t"+c.phoneNumber+"\t"+c.cardnumber
			+"\t"+c.username+"\t"+c.Address+"\t"+c.CityStateZip+"\t"+c.balance);
	pw.close();
}
public void addEMP(String fname, String lname, String ssn, String payrate) throws IOException{
	Employee e = new Employee(fname,lname,ssn, payrate);
	EMPLOYEES.add(e);
	String temp = null;
	BufferedReader br = new BufferedReader(new FileReader(employeeFile));
	FileWriter fw = new FileWriter(employeeFile,true);
	PrintWriter pw = new PrintWriter(fw);
	Scanner s = new Scanner(System.in);
	s = new Scanner(f);


		pw.println(e.firstName+"\t"+e.lastName+"\t"+e.ssn+"\t"+e.payRate);
		pw.close();
	
}
public void chargeCustomer(String userName, double price) throws FileNotFoundException{
	
	for(int i=0;i<CUSTOMERS.size();i++){
		if(CUSTOMERS.get(i).username.equals(userName)){
			CUSTOMERS.get(i).balance+=price;
		}
	}
	PrintWriter p = new PrintWriter(customerFile);
	for(int i=0;i<CUSTOMERS.size();i++){
		p.println(CUSTOMERS.get(i).fname+"\t"+CUSTOMERS.get(i).lname+"\t"
	+CUSTOMERS.get(i).phoneNumber+"\t"+CUSTOMERS.get(i).cardnumber+"\t"
	+CUSTOMERS.get(i).username+"\t"+CUSTOMERS.get(i).Address+"\t"+CUSTOMERS.get(i).CityStateZip
	+"\t"+CUSTOMERS.get(i).balance);
		
	}
	p.close();
}

public void changeBalance(String Phone, double newBalance) throws FileNotFoundException{
	for(int i=0;i<CUSTOMERS.size();i++){
		if(CUSTOMERS.get(i).phoneNumber.equals(Phone)){
			CUSTOMERS.get(i).balance=newBalance;
		}
	}
	PrintWriter p = new PrintWriter(customerFile);
	for(int i=0;i<CUSTOMERS.size();i++){
		p.println(CUSTOMERS.get(i).fname+"\t"+CUSTOMERS.get(i).lname+"\t"
	+CUSTOMERS.get(i).phoneNumber+"\t"+CUSTOMERS.get(i).cardnumber+"\t"
	+CUSTOMERS.get(i).username+"\t"+CUSTOMERS.get(i).Address+"\t"+CUSTOMERS.get(i).CityStateZip
	+"\t"+CUSTOMERS.get(i).balance);
		
	}
	p.close();
	
}
public void deleteEMP(String SSN) throws FileNotFoundException{

	for(int i=0;i<EMPLOYEES.size();i++){
		if(EMPLOYEES.get(i).ssn.equals(SSN)){
			EMPLOYEES.remove(i);
		}
		
	}
	PrintWriter p = new PrintWriter(employeeFile);
	for(int i=0;i<EMPLOYEES.size();i++){
		p.print(EMPLOYEES.get(i).firstName+"\t"+EMPLOYEES.get(i).lastName+"\t"+
				EMPLOYEES.get(i).ssn+"\t"+EMPLOYEES.get(i).payRate);
		p.println();
	}
	p.close();
	
}
/*
 * This method will change the status of a room from "A"(Available) to "R"(Reserved).
 * It will also write to the text file, the current occupant's name and reservation period.
 */
public void checkIn(String roomNum,String rperiod ,String USERNAME) throws IOException{
	for(int i=0;i<ROOMS.size();i++){
		if(ROOMS.get(i).roomNumber.equals(roomNum)
				&&ROOMS.get(i).status.equals("A")){
			ROOMS.get(i).status="R";
			ROOMS.get(i).resPeriod=rperiod;
			ROOMS.get(i).occupant=USERNAME;
			break;
		
		}
		else {
			if(ROOMS.get(i).getRoomNumber().equals(roomNum)
					&&ROOMS.get(i).getStatus().equals("R")){
				System.out.println("Room Not Available");
				break;
			}
			//System.out.println("Room Not Available");
				//notify through panel, that room is already occupied
			//	also be sure to use the "/" as demliter to tokenize user input.
		}
	}
	PrintWriter p = new PrintWriter(roomFile);
	for(int i=0;i<ROOMS.size();i++){
		p.println(ROOMS.get(i).roomNumber+"\t"+ROOMS.get(i).floor+"\t"+ROOMS.get(i).rate+"\t"
				+ROOMS.get(i).status+"\t"+ROOMS.get(i).resPeriod+"\t"+ROOMS.get(i).occupant);
		
	}
	p.close();
	
}
public boolean isCheckedIn(String userid){
	for(int i=0;i<ROOMS.size();i++){
		if(ROOMS.get(i).occupant.equals(userid)){
			return true;
		}
	}
	return false;
}
public void checkOut(String roomNum) throws IOException{
	for(int i=0;i<ROOMS.size();i++){
		if(ROOMS.get(i).roomNumber.equals(roomNum)){
		if(ROOMS.get(i).status.equals("R")){
			ROOMS.get(i).status="A";
			ROOMS.get(i).resPeriod="n/a";
			ROOMS.get(i).occupant="none";
		}}
		else {
		
				//notify through panel, that room is already occupied
			//	also be sure to use the "/" as demliter to tokenize user input.
		}
	}
	PrintWriter p = new PrintWriter(roomFile);
	for(int i=0;i<ROOMS.size();i++){
		p.println(ROOMS.get(i).roomNumber+"\t"+ROOMS.get(i).floor+"\t"+ROOMS.get(i).rate+"\t"
				+ROOMS.get(i).status+"\t"+ROOMS.get(i).resPeriod+"\t"+ROOMS.get(i).occupant);
		
	}
	p.close();
	
}

public void ReserveRoom(String roomNum,String rperiod ,String USERNAME) throws IOException{
	for(int i=0;i<RESERVATIONS.size();i++){
		if(RESERVATIONS.get(i).RoomNum.equals(roomNum)
				&&RESERVATIONS.get(i).ResPeriod.equals(rperiod)){
		
			System.out.println("room not available, try again");
		
	}
		else {
			Reservation r = new Reservation(roomNum, rperiod, USERNAME);
			RESERVATIONS.add(r);
		}
		
	}
	PrintWriter p = new PrintWriter(roomFile);
	for(int i=0;i<RESERVATIONS.size();i++){
		p.println(RESERVATIONS.get(i).RoomNum+"\t"+RESERVATIONS.get(i).ResPeriod+"\t"+RESERVATIONS.get(i).CustName);
		
	}
	p.close();
	FileWriter fw = new FileWriter(reservationsFile, true);
	PrintWriter p2 = new PrintWriter(fw);
	p2.println(roomNum+"\t"+rperiod+"\t"+USERNAME);
	p2.close();
	
}
public void RemoveReservation(String roomNum,String rperiod ,String CUSTNAME) throws FileNotFoundException{

	for(int i=0;i<RESERVATIONS.size();i++){
		if(RESERVATIONS.get(i).RoomNum.equals(roomNum)
			&&RESERVATIONS.get(i).ResPeriod.equals(rperiod)
			&&RESERVATIONS.get(i).CustName.equals(CUSTNAME)){
			RESERVATIONS.remove(i);
			System.out.println("1 reservations removed");
		}
	}
	PrintWriter p = new PrintWriter(reservationsFile);
	for(int i=0;i<RESERVATIONS.size();i++){
		p.print(RESERVATIONS.get(i).RoomNum+"\t"+RESERVATIONS.get(i).ResPeriod+"\t"+
				RESERVATIONS.get(i).CustName);
		p.println();
	}
	p.close();


}
public void AddAccount(String fname,String lname ,String username, String password, String TYPE, String SSN) throws IOException{
	//add the tuple to file
	Account a = new Account(fname,lname,username, password, TYPE, SSN);
	ACCOUNTS.add(a);
	String temp = null;
	BufferedReader br = new BufferedReader(new FileReader(accountsFile));
	FileWriter fw = new FileWriter(accountsFile,true);
	PrintWriter pw = new PrintWriter(fw);
	Scanner s = new Scanner(System.in);
	s = new Scanner(f);


		pw.println(a.FirstName+"\t"+a.LastName+"\t"+a.UserName+"\t"+a.Password+"\t"+a.Type+"\t"+a.SSN);
		pw.close();
	
	}
public void RemoveAccount(String fname,String lname ,String username, String password, String TYPE, String SSN) throws FileNotFoundException{
	//remove the tuple from file
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).SSN.equals(SSN)){
			ACCOUNTS.remove(i);
			System.out.println("Account removed");
		}
	}
	PrintWriter p = new PrintWriter(reservationsFile);
	for(int i=0;i<ACCOUNTS.size();i++){
		p.print(ACCOUNTS.get(i).FirstName+"\t"+ACCOUNTS.get(i).LastName+"\t"+ACCOUNTS.get(i).UserName
				+"\t"+ACCOUNTS.get(i).Password+"\t"+ACCOUNTS.get(i).Type+"\t"+ACCOUNTS.get(i).SSN);
		p.println();
	}
	p.close();
	}
public void ChangeAccountPassword(String username,String SSN, String newPassword) throws FileNotFoundException{
		for(int i=0;i<ACCOUNTS.size();i++){
			if(ACCOUNTS.get(i).SSN.equals(SSN)){
				ACCOUNTS.get(i).Password=newPassword;
				System.out.println("Password changed at index: "+i);
			}
		}
		PrintWriter p = new PrintWriter(accountsFile);
		for(int i=0;i<ACCOUNTS.size();i++){
			p.print(ACCOUNTS.get(i).FirstName+"\t"+ACCOUNTS.get(i).LastName+"\t"+ACCOUNTS.get(i).UserName
					+"\t"+ACCOUNTS.get(i).Password+"\t"+ACCOUNTS.get(i).Type+"\t"+ACCOUNTS.get(i).SSN);
			p.println();
		}
		p.close();
		
	}
public void AddServiceRequest(String username,String roomnum ,String REQUEST) throws IOException{
	//add new instance/tuple of service request
	ServiceRequest sr = new ServiceRequest(username, roomnum, REQUEST);
	SERVICEREQUESTS.add(sr);
	FileWriter fw = new FileWriter(serviceRequestsFile,true);
	PrintWriter pw = new PrintWriter(fw);
	pw.println(username+"\t"+roomnum+"\t"+REQUEST);
	pw.close();
	}
public void AddMealRequest(String username,String roomnum ,String COMMENTS) throws IOException{
	//add tuple to db
	MealRequest mr = new MealRequest(username, roomnum, COMMENTS);
	MEALREQUESTS.add(mr);
	FileWriter fw = new FileWriter(mealRequestsFile,true);
	PrintWriter pw = new PrintWriter(fw);
	pw.println(username+"\t"+roomnum+"\t"+COMMENTS);
	pw.close();
	}
public void RemoveMealRequest(String username,String roomnum ,String COMMENTS) throws FileNotFoundException{
	for(int i=0;i<MEALREQUESTS.size();i++){
		if(MEALREQUESTS.get(i).getCustomerUserName().equals(username)
				&&MEALREQUESTS.get(i).getMealRequestComments().equals(COMMENTS)){
			MEALREQUESTS.remove(i);
		}
	}
	PrintWriter p = new PrintWriter(mealRequestsFile);
	for(int i=0;i<MEALREQUESTS.size();i++){
		p.print(MEALREQUESTS.get(i).getCustomerUserName()+"\t"+MEALREQUESTS.get(i).getRoom()+"\t"+MEALREQUESTS.get(i).getMealRequestComments());
		p.println();
	}
	p.close();
}
public Room getUserRoom(String userid){
	for(int i=0;i<ROOMS.size();i++){
		if(ROOMS.get(i).occupant.equals(userid)){
			return ROOMS.get(i);
		}
	}
	return null;
}
	

public boolean validateAccount(String username, String password){
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).UserName.equals(username)
				&&ACCOUNTS.get(i).Password.equals(password)){
			return true;
		}
	}
	return false;
}
public boolean isAdmin(String username, String password){
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).UserName.equals(username)
				&&ACCOUNTS.get(i).Password.equals(password)
				&&ACCOUNTS.get(i).Type.equals("ADMIN")){
			return true;
		}
	}
	return false;
}
public boolean isEmployee(String username, String password){
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).UserName.equals(username)
				&&ACCOUNTS.get(i).Password.equals(password)
				&&ACCOUNTS.get(i).Type.equals("EMPLOYEE")){
			return true;
		}
	}
	return false;
	
}
public boolean isCustomer(String username, String password){
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).UserName.equals(username)
				&&ACCOUNTS.get(i).Password.equals(password)
				&&ACCOUNTS.get(i).Type.equals("CUSTOMER")){
			return true;
		}
	}
	return false;
}
public boolean isAccountDuplicate(String username){
	for(int i=0;i<ACCOUNTS.size();i++){
		if(ACCOUNTS.get(i).UserName.equals(username)){
			return true;
		}
	}
	return false;
}
public Customer getCustomer(String userid){
	for(int i=0;i<CUSTOMERS.size();i++){
		if(CUSTOMERS.get(i).username.equals(userid)){
			return CUSTOMERS.get(i);
		}
	}
	return null;
}

public ArrayList<Room> getRooms(){
	return this.ROOMS;
}
public ArrayList<Account> getAccounts(){
	return this.ACCOUNTS;
}
public ArrayList<Customer> getCustomers(){
	return this.CUSTOMERS;
}
public ArrayList<MealRequest> getMealRequests(){
	return this.MEALREQUESTS;
}
public ArrayList<Employee> getAllEmployees(){
	return this.EMPLOYEES;
}
public ArrayList<Reservation> getAllReservations(){
	return this.RESERVATIONS;
}
public ArrayList<ServiceRequest> getAllServiceRequests(){
	return this.SERVICEREQUESTS;
}
public int getCustomerCount(){
	return this.CUSTOMERS.size();
}
public int getAccountCount(){
	return this.ACCOUNTS.size();
}
public int getMealRequestCount(){
	return this.MEALREQUESTS.size();
}
public int getEmployeeCount(){
	return this.EMPLOYEES.size();
}
public int getReservationCount(){
	return this.RESERVATIONS.size();
}
public int getServiceRequestCount(){
	return this.SERVICEREQUESTS.size();
}

}
