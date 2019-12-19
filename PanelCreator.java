import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class PanelCreator {
	
	
		// the frame that the panels will be added to to display
		public static JFrame mainFrame;
		// the form that will be added to to send to the db
		public static String form;
		// guest, manager, employee = 1,2,3 used to determine what they will see
		public static String userType;
		// unique id of the user used to get their info from the db
		public static String userID;
		// last displayed panel, can be used to return to the previous page
		public static JPanel lastViewedPanel;
		// home panel, used when user cancels out of a multi step process
		public static JPanel homePanel;

		public PanelCreator() {
			mainFrame = new JFrame();
			mainFrame.setSize(1920, 1080);
			createStartPanel();
		}

		// Format of the create panel methods is
		// initialize all buttons, textfields, etc
		// add action listeners to buttons
		// --actionlisteners should remove the current panel,
		// --add the input to the form, validate the form,
		// --call the next method, and set the jframe to visible
		// add everything to the panel
		// add the panel to the frame
		public void createStartPanel() {
			JPanel startPanel = new JPanel();

			JButton loginButton = new JButton("Log in to existing account.");
			JButton registerButton = new JButton("Register a new account.");
			loginButton.addActionListener(new ActionListener() {
				//use db writer object to validate a login instance
				public void actionPerformed(ActionEvent e) {
					
					mainFrame.remove(startPanel);
					createLoginPanel();
					mainFrame.setVisible(true);
				}
			});

			registerButton.addActionListener(new ActionListener() {
				//Write to db, a new account instance
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(startPanel);
					createRegistrationPanel();
					mainFrame.setVisible(true);
					try {
						DataBaseWriter db = new DataBaseWriter();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			startPanel.add(loginButton);
			startPanel.add(registerButton);

			mainFrame.add(startPanel);
			mainFrame.setVisible(true);
		}

		public void createLoginPanel() {
			JPanel loginPanel = new JPanel();

			JTextField username = new JTextField(20);
			JTextField password = new JTextField(20);

			JLabel usernameLabel = new JLabel("Username");
			JLabel passwordLabel = new JLabel("Password");

			JButton loginButton = new JButton("Log in");
			JButton resetButton = new JButton("Reset");
			JButton goBackButton = new JButton("Go Back");

			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					form = username.getText() + ";/" + password.getText();
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.validateAccount(username.getText(), password.getText())){
								userID=username.getText();
							if(db.isAdmin(username.getText(), password.getText())){
								//load admin screen.
								userType="ADMIN";
								createManagerHomePanel();
								System.out.println("ADMIN");
							}
							else if(db.isCustomer(username.getText(), password.getText())){
								userType = "CUSTOMER";
								createGuestHomePanel();
								System.out.println("GUEST");
								//load customer screen
							}
							else if(db.isEmployee(username.getText(), password.getText())){
								userType="EMPLOYEE";
								createEmployeeHomePanel();
								System.out.println("EMPLOYEE");
								//load employee screen
							}
							mainFrame.remove(loginPanel);
							mainFrame.setVisible(true);
						}
						
					}catch(IOException e2){
						e2.printStackTrace();
					}
				
				
				}
			});

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(loginPanel);
					createStartPanel();
					mainFrame.setVisible(true);
				}
			});

			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(loginPanel);
					createLoginPanel();
					mainFrame.setVisible(true);
				}
			});
		
			loginPanel.add(usernameLabel);
			loginPanel.add(username);
			loginPanel.add(passwordLabel);
			loginPanel.add(password);
			loginPanel.add(loginButton);
			loginPanel.add(goBackButton);
			loginPanel.add(resetButton);

			mainFrame.add(loginPanel);
		}


		public void createRegistrationPanel() {
			JPanel registrationPanel = new JPanel();

			JTextField username = new JTextField(20);
			JTextField firstName = new JTextField(20);
			JTextField lastName = new JTextField(20);
			JTextField phoneNumber = new JTextField("ex:7705555555");
			JTextField creditCardNumber = new JTextField(16);
			JTextField streetAddress = new JTextField(50);
			JTextField password = new JTextField(50);
			JTextField cityStateZip = new JTextField(30);

			JLabel phoneNumberLabel = new JLabel("Phone #");
			JLabel passwordLabel = new JLabel("Password: ");
			JLabel usernameLabel = new JLabel("Desired Username: ");
			JLabel firstNameLabel = new JLabel("First Name: ");
			JLabel lastNameLabel = new JLabel("Last Name: ");
			JLabel creditCardNumberLabel = new JLabel("Credit Card Number: ");
			JLabel streetAddressLabel = new JLabel("Street Address: ");
			JLabel cityStateZipLabel = new JLabel("City, State, Zip-code: ");

			JButton registerButton = new JButton("Register");
			JButton resetButton = new JButton("Reset");
			JButton goBackButton = new JButton("Go Back");

			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						DataBaseWriter db = new DataBaseWriter();
						if(!db.isAccountDuplicate(username.getText())){
						db.AddAccount(firstName.getText(), lastName.getText(), username.getText(), 
								password.getText(), "CUSTOMER", "000000000");
						db.addCustomer(firstName.getText(), lastName.getText(), phoneNumber.getText(), creditCardNumber.getText(), username.getText(), streetAddress.getText(), cityStateZip.getText());
						}
						else{
							System.out.println("Account with that username already exitsts");
							//Notify user that account username already exists.
						}
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mainFrame.remove(registrationPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(registrationPanel);
					createStartPanel();
					mainFrame.setVisible(true);
					JFrame jf = new JFrame("ERROR");
				
				}
			});

			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(registrationPanel);
					createRegistrationPanel();
					mainFrame.setVisible(true);
				}
			});

			registrationPanel.add(usernameLabel);
			registrationPanel.add(username);
			registrationPanel.add(firstNameLabel);
			registrationPanel.add(firstName);
			registrationPanel.add(lastNameLabel);
			registrationPanel.add(lastName);
			registrationPanel.add(creditCardNumberLabel);
			registrationPanel.add(creditCardNumber);
			registrationPanel.add(streetAddressLabel);
			registrationPanel.add(streetAddress);
			registrationPanel.add(cityStateZipLabel);
			registrationPanel.add(cityStateZip);
			registrationPanel.add(passwordLabel);
			registrationPanel.add(password);
			registrationPanel.add(registerButton);
			registrationPanel.add(goBackButton);
			registrationPanel.add(resetButton);

			mainFrame.add(registrationPanel);
		}

		public void createGuestHomePanel() {
			JPanel guestHomePanel = new JPanel();

			JLabel header = new JLabel("Hello. Is there anything you would like assistance with?");
			JLabel empty = new JLabel("\n");
			JButton reserveButton = new JButton("Reserve a room.");
			JButton wakeUpButton = new JButton("Request a wake up call.");
			JButton balanceButton = new JButton("Check your balance.");
			JButton mealOrderButton = new JButton("Order Meal");
			JButton roomServiceButton = new JButton("Request room service.");
			JButton adjustStayButton = new JButton("Adjust your current stay.");
			JButton payBalanceButton = new JButton("Pay your balance.");
			JButton contactUsButton = new JButton("Contact us.");
			
			mealOrderButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					createMealSelectorPanel();
					mainFrame.setVisible(true);
				}
			});
			reserveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					createReserveRoomPanel();
					mainFrame.setVisible(true);
				}
			});

			wakeUpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					createWakeUpCallPanel();
					mainFrame.setVisible(true);
				}
			});

			balanceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//mainFrame.remove(guestHomePanel);
					try {
						mainFrame.remove(guestHomePanel);
						createCheckBalancePanel(userID);
						mainFrame.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//mainFrame.setVisible(true);
				}
			});

			roomServiceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
				//createPayBalancePanel();
					mainFrame.setVisible(true);
				}
			});

			adjustStayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					createAdjustStayPanel();
					mainFrame.setVisible(true);
				}
			});

			payBalanceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					try {
						createPayBalancePanel();
						mainFrame.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//mainFrame.setVisible(true);
				}
			});

			contactUsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(guestHomePanel);
					createContactUsPanel();
					mainFrame.setVisible(true);
				}
			});

			guestHomePanel.add(header);
			guestHomePanel.add(empty);
			guestHomePanel.add(reserveButton);
			guestHomePanel.add(wakeUpButton);
			guestHomePanel.add(balanceButton);
			guestHomePanel.add(roomServiceButton);
			guestHomePanel.add(mealOrderButton);
			guestHomePanel.add(adjustStayButton);
			guestHomePanel.add(payBalanceButton);
			guestHomePanel.add(contactUsButton);

			mainFrame.add(guestHomePanel);
		}
		public void createMealSelectorPanel(){
			JPanel mealSelectorPanel = new JPanel();
			JLabel header = new JLabel("Select a meal from our menu: ");
			JButton item1 = new JButton("Large Pizza");
			JButton item2 = new JButton("Medium Pizza");
			JButton item3 = new JButton("Small Pizza");
			JButton item4 = new JButton("Sub Sandwhich");
			JButton item5 = new JButton("Spaghetti");
			JButton item6 = new JButton("Tacos");
			JButton item7 = new JButton("Western Salad");
			JButton item8 = new JButton("Orange Chicken");
			JButton home = new JButton("Home");
			mealSelectorPanel.add(header);
			mealSelectorPanel.add(item1);
			mealSelectorPanel.add(item2);
			mealSelectorPanel.add(item3);
			mealSelectorPanel.add(item4);
			mealSelectorPanel.add(item5);
			mealSelectorPanel.add(item6);
			mealSelectorPanel.add(item6);
			mealSelectorPanel.add(item7);
			mealSelectorPanel.add(item8);
			mealSelectorPanel.add(home);
			item1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
					DataBaseWriter db = new DataBaseWriter();
					if(db.isCheckedIn(userID)){
					db.chargeCustomer(userID, 12.99 );
					String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
					System.out.println(feedback);
					db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
					else{
						JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
					}
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			});
			item2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 10.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 8.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 8.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 10.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 7.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item7.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 6.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			item8.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						if(db.isCheckedIn(userID)){
						db.chargeCustomer(userID, 8.99 );
						String feedback = JOptionPane.showInputDialog("Item Added! Please tell us your preferences, allergies, etc");
						System.out.println(feedback);
						db.AddMealRequest(userID, db.getUserRoom(userID).getRoomNumber(), feedback);
					}
						else{
							JOptionPane.showMessageDialog(mealSelectorPanel, "Your not currently checked in!");
						}
					}catch(IOException e){
							e.printStackTrace();
						}
				}
			});
			home.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					
				}
			});
			mainFrame.add(mealSelectorPanel);
			mainFrame.setVisible(true);
		}

		public void createReserveRoomPanel() {
			JPanel reserveRoomPanel = new JPanel();

			JLabel header = new JLabel("What days would you like to stay with us?");
			JLabel startDate = new JLabel("Select a start date for your stay: ");
			JLabel endDate = new JLabel("Select a start date for your stay: ");

			JSpinner startSpinner = new JSpinner();
			JSpinner endSpinner = new JSpinner();

			// need a for loop here that will add all of the dates to the
			// spinner
			// will need to get the dates available from the db
			// db should check against the current date and return all dates
			// after it
			// don't return dates that it isn't open (if any)
			// the end date spinner should start the day after the current date

			JButton cancelButton = new JButton("Cancel reservation.");
			JButton nextButton = new JButton("Continue to next step.");

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(reserveRoomPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(reserveRoomPanel);
					createRoomFilterPanel();
					mainFrame.setVisible(true);
				}
			});

			reserveRoomPanel.add(header);
			reserveRoomPanel.add(startDate);
			reserveRoomPanel.add(startSpinner);
			reserveRoomPanel.add(endDate);
			reserveRoomPanel.add(endSpinner);
			reserveRoomPanel.add(cancelButton);
			reserveRoomPanel.add(nextButton);

			mainFrame.add(reserveRoomPanel);
			mainFrame.setVisible(true);
		}

		public void createWakeUpCallPanel() {
			JPanel wakeUpCallPanel = new JPanel();

			JLabel setTimeLabel = new JLabel("Set wake up call time.");
			JSpinner setTimeSpinner = new JSpinner();
			JButton cancelButton = new JButton("Cancel");
			JButton setWakeUpCallButton = new JButton("Set wake up call.");

			// for loop to add times to spinner
			// can do without the db
			// can do with a spinner for hour, min, and am/pm
			// will implement later

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(wakeUpCallPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			setWakeUpCallButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// call method to set the wake up call in the db
					// should be under a list of things that get checked
					// to see whether they should be executed or not every 5 min
					// or so

					mainFrame.remove(wakeUpCallPanel);
					createRegistrationPanel();
					mainFrame.setVisible(true);
				}
			});

			wakeUpCallPanel.add(setTimeLabel);
			wakeUpCallPanel.add(setTimeSpinner);
			wakeUpCallPanel.add(cancelButton);
			wakeUpCallPanel.add(setWakeUpCallButton);

			mainFrame.add(wakeUpCallPanel);
			mainFrame.setVisible(true);
		}

		public void createCheckBalancePanel(String userid) throws IOException {
			DataBaseWriter db = new DataBaseWriter();
			double balanceAmount=0.00;
			for(int i=0;i<db.getCustomerCount();i++){
				if(db.getCustomers().get(i).username.equals(userid)){
					balanceAmount+=db.getCustomers().get(i).getBalance();
				}
			}
			JPanel checkBalancePanel = new JPanel();
			JButton goBackButton = new JButton("Log in to existing account.");
			JLabel balanceLabel = new JLabel("Todays Balance: "+balanceAmount);
			// should show all things charged to the account this stay, or the
			// things that haven't been paid off
			// for now, will just want to be able to get and show the balabnce
			// from the db

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(checkBalancePanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			checkBalancePanel.add(balanceLabel);
			checkBalancePanel.add(goBackButton);

			mainFrame.add(checkBalancePanel);
			mainFrame.setVisible(true);
		}

		public void createPayBalancePanel() throws IOException {
			DataBaseWriter db = new DataBaseWriter();
			
			double balance = db.getCustomer(userID).getBalance();
			String phoneNumber = db.getCustomer(userID).getPhoneNumber();
			JPanel payBalancePanel = new JPanel();
			
			JLabel payBalanceLabel = new JLabel("When you pay your balance, we will use the credit card number you registered with.\n Pay your existing balance, or pay a portion. :)");
			JLabel currentBalanceLabel = new JLabel("Current Balance: "+balance);
			JButton goBackButton = new JButton("Go back to Home page.");
			JButton payButton = new JButton("Pay Entire Balance");
			JLabel portion = new JLabel("Pay A Portion");
			JTextField portionValue = new JTextField("Enter Ammount");
			JButton payThisPortionButton = new JButton("Pay This Portion");
			// add text to cc number from the db

			payButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// need to send info to db that it has been paid
					// update the balance associated with this user if validate
					// successed
					
					try {
						
						DataBaseWriter	db = new DataBaseWriter();
						db.changeBalance(phoneNumber, 0.00);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
	
			});
			payThisPortionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// need to send info to db that it has been paid
					// update the balance associated with this user if validate
					// successed
					DataBaseWriter db;
					double payment = Double.parseDouble(portionValue.getText());
					try {
						db = new DataBaseWriter();
						db.changeBalance(phoneNumber, balance-payment);
						System.out.println("Payment Successful");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				}
			});
			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(payBalancePanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			payBalancePanel.add(payBalanceLabel);
			payBalancePanel.add(currentBalanceLabel);
			payBalancePanel.add(portion);
			payBalancePanel.add(portionValue);
			payBalancePanel.add(goBackButton);
			payBalancePanel.add(payButton);

			mainFrame.add(payBalancePanel);
			mainFrame.setVisible(true);
		}

		public void createAdjustStayPanel() {
			JPanel adjustStayPanel = new JPanel();

			JLabel adjustStayLabel = new JLabel("How would you like to adjust your stay?");
			JSpinner endDateSpinner = new JSpinner();
			JButton goBackButton = new JButton("Go back to Home page.");
			JButton acceptAdjustmentButton = new JButton("Accept stay adjustment.");

			// need to set the end spinner to only display days between the
			// current day up

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(adjustStayPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			acceptAdjustmentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// need to send to the db that the stay has been adjusted
					// and update it
					mainFrame.remove(adjustStayPanel);
					createRegistrationPanel();
					mainFrame.setVisible(true);
				}
			});

			adjustStayPanel.add(adjustStayLabel);
			adjustStayPanel.add(endDateSpinner);
			adjustStayPanel.add(goBackButton);
			adjustStayPanel.add(acceptAdjustmentButton);

			mainFrame.add(adjustStayPanel);
			mainFrame.setVisible(true);
		}

		public void createContactUsPanel() {
			JPanel contactUsPanel = new JPanel();

			JLabel contactUsLabel = new JLabel("Contact us.");
			JLabel infoLabel = new JLabel("1-800-555-555 or email us at email@email.com.");
			JButton goBackButton = new JButton("Go back to Home page.");

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(contactUsPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			contactUsPanel.add(contactUsLabel);
			contactUsPanel.add(infoLabel);
			contactUsPanel.add(goBackButton);

			mainFrame.add(contactUsPanel);
			mainFrame.setVisible(true);
		}

		public void createRoomFilterPanel() {
			JPanel roomFilterPanel = new JPanel();

			JLabel contactUsLabel = new JLabel("Contact us.");
			JLabel infoLabel = new JLabel("1-800-555-555 or email us at email@email.com.");
			JButton goBackButton = new JButton("Go back to Home page.");

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(roomFilterPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			roomFilterPanel.add(contactUsLabel);
			roomFilterPanel.add(infoLabel);
			roomFilterPanel.add(goBackButton);

			mainFrame.add(roomFilterPanel);
			mainFrame.setVisible(true);
		}

		public void createAvailableRoomsPanel() {
			JPanel availableRoomsPanel = new JPanel();

			JLabel availableRoomsLabel = new JLabel("Available rooms.");
			JLabel resultsLabel = new JLabel();
			JSpinner selectRoomSpinner = new JSpinner();
			JButton selectRoomButton = new JButton("Reserve this room.");
			JButton goBackButton = new JButton("Go back to Home page.");

			// need a for loop that will go through all results from the db and
			// add them to the resultsLabel

			selectRoomButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// send info to db that this room will be taken
					// need to associate the room with the user
					mainFrame.remove(availableRoomsPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});
			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(availableRoomsPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			availableRoomsPanel.add(availableRoomsLabel);
			availableRoomsPanel.add(resultsLabel);
			availableRoomsPanel.add(selectRoomSpinner);
			availableRoomsPanel.add(selectRoomButton);
			availableRoomsPanel.add(goBackButton);

			mainFrame.add(availableRoomsPanel);
			mainFrame.setVisible(true);
		}

		// for the manager view version of things
		// on the panels that we need for both guest and manager, like adjust
		// stay
		// need to have the create panel method for that panel check the user's
		// type
		// if they are a manager, then it should provide extra options

		public void createManagerHomePanel() {
			JPanel managerHomePanel = new JPanel();

			JLabel managerHomeLabel = new JLabel("Welcome, Manager.");
			JButton guestViewButton = new JButton("Open guest view.");
			JButton employeeViewButton = new JButton("Open employee view.");
			JLabel whatWouldYouLikeToDoButton = new JLabel("What would you like to do?");
			JButton adjustPriceButton = new JButton("Adjust price for guest.");
			JButton setServiceButton = new JButton("Set service for employee to perform.");

			guestViewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(managerHomePanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});

			employeeViewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(managerHomePanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});

			adjustPriceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(managerHomePanel);
					createAdjustPricePanel();
					mainFrame.setVisible(true);
				}
			});

			setServiceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(managerHomePanel);
					createSetServicePanel();
					mainFrame.setVisible(true);
				}
			});

			managerHomePanel.add(managerHomeLabel);
			managerHomePanel.add(guestViewButton);
			managerHomePanel.add(employeeViewButton);
			managerHomePanel.add(whatWouldYouLikeToDoButton);
			managerHomePanel.add(adjustPriceButton);
			managerHomePanel.add(setServiceButton);

			mainFrame.add(managerHomePanel);
			mainFrame.setVisible(true);
		}

		public void createAdjustPricePanel() {
			JPanel adjustPricePanel = new JPanel();
			
			JLabel question = new JLabel("What would you like to change?");
			JButton roomPrice = new JButton("Change Room Price");
			JButton menuPrice = new JButton("Change The Price Of A Meal");
			JButton roomStatus = new JButton("Change Room Status To Vacant");
			JButton roomStatus2 = new JButton("Change Room Status To Occupied");
			JButton guestBalance = new JButton("Change Guest Balance");
			JButton goBack = new JButton("Go Back");
			adjustPricePanel.add(question);
			adjustPricePanel.add(roomPrice);
			adjustPricePanel.add(menuPrice);
			adjustPricePanel.add(roomStatus);
			adjustPricePanel.add(roomStatus2);
			adjustPricePanel.add(guestBalance);
			adjustPricePanel.add(goBack);
			
			roomPrice.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(adjustPricePanel);
					createChangeRoomPricePanel();
					mainFrame.setVisible(true);
				}
			});
			guestBalance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(adjustPricePanel);
					createChangeGuestBalancePanel();
					mainFrame.setVisible(true);
				}
			});
			menuPrice.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(adjustPricePanel);
					createChangeMealPricePanel();
					mainFrame.setVisible(true);
				}
			});
			roomStatus.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(adjustPricePanel);
					try {
						createChangeRoomToVacantPanel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mainFrame.setVisible(true);
				}
			});
			roomStatus2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainFrame.remove(adjustPricePanel);
					createChangeRoomToOccupiedPanel();
					mainFrame.setVisible(true);
				}
			});
			/*JPanel adjustPricePanel = new JPanel();
			
			JLabel adjustPriceLabel = new JLabel("How would you like to adjust a price?");
			JLabel pickRoomLabel = new JLabel("What room would you like to adjust a price for?");
			JTextField pickRoomTextField = new JTextField();
			JButton pickRoomButton = new JButton("Pick this room.");
			JLabel resultsLabel = new JLabel("Set service for employee to perform.");
			JSpinner listingNumberSpinner = new JSpinner();
			JTextField adjustedPrice = new JTextField();
			JButton setPriceButton = new JButton("Set price.");
			
			pickRoomButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// return all the charges from the db, then add them to the
					// results panel
					// all listing returned should have a listing number which
					// is added to the spinner

					mainFrame.remove(adjustPricePanel);
					adjustPricePanel.add(resultsLabel);
					adjustPricePanel.add(listingNumberSpinner);
					adjustPricePanel.add(adjustedPrice);
					adjustPricePanel.add(setPriceButton);
					mainFrame.add(adjustPricePanel);
					mainFrame.setVisible(true);
				}
			});

			setPriceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// send to db that this listing has had it's price updated
					// will need to update the total balance too
					mainFrame.remove(adjustPricePanel);
					createManagerHomePanel();
					mainFrame.setVisible(true);
				}
			});

			adjustPricePanel.add(adjustPriceLabel);
			adjustPricePanel.add(pickRoomLabel);
			adjustPricePanel.add(pickRoomTextField);
			adjustPricePanel.add(pickRoomButton);

			mainFrame.add(adjustPricePanel);
			mainFrame.setVisible(true);
			*/
			mainFrame.add(adjustPricePanel);
			mainFrame.setVisible(true);
		}
		public void createChangeRoomPricePanel(){
			JPanel p1 = new JPanel();
			JLabel whichRoom = new JLabel("Select Room: ");
			SpinnerModel sm = new SpinnerNumberModel(1,1,200,1);
			JSpinner roomNum = new JSpinner(sm);
			JLabel newPrice = new JLabel("New Rate: ");
			JTextField value = new JTextField("enter new value");
			JButton select = new JButton("Update");
			JButton home = new JButton("Home");
			p1.add(whichRoom);
			p1.add(roomNum);
			p1.add(newPrice);
			p1.add(value);
			p1.add(select);
			p1.add(home);
			mainFrame.add(p1);
			mainFrame.setVisible(true);
		}
		public void createChangeRoomToVacantPanel() throws IOException{
			JPanel p2 = new JPanel();
			JLabel whichRoom = new JLabel("Select Room: ");
			SpinnerModel sm = new SpinnerNumberModel(1,1,200,1);
			JSpinner roomNumSelector = new JSpinner(sm);
			JButton select = new JButton("Update");
			JButton goHome = new JButton("Home");
			//DataBaseWriter db = new DataBaseWriter();
			//db.checkOut(roomNumSelector.getValue().toString());
			select.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try {
						DataBaseWriter db = new DataBaseWriter();
						db.checkOut(roomNumSelector.getValue().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			p2.add(whichRoom);
			p2.add(roomNumSelector);
			p2.add(select);
			p2.add(goHome);
			mainFrame.add(p2);
			mainFrame.setVisible(true);
		}
		public void createChangeRoomToOccupiedPanel(){
			JPanel p3 = new JPanel();
			JLabel whichRoom = new JLabel("Select Room: ");
			SpinnerModel sm = new SpinnerNumberModel(1,1,200,1);
			JSpinner roomNum = new JSpinner(sm);
			JButton select = new JButton("Update");
			JButton goHome = new JButton("Home");
			select.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					
				}
			});
			p3.add(whichRoom);
			p3.add(roomNum);
			p3.add(select);
			p3.add(goHome);
			mainFrame.add(p3);
			mainFrame.setVisible(true);
		}
		public void createChangeGuestBalancePanel(){
			JPanel p4 = new JPanel();
			JLabel phonenum = new JLabel("Guest Phone Number: ");
			JTextField pn = new JTextField("ex:7731111111");
			JLabel newBalance = new JLabel("Enter New Balance");
			JTextField nb = new JTextField("ex:39.95");
			JButton change = new JButton("Change");
			JButton cancel = new JButton("Cancel");
			change.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
						DataBaseWriter db = new DataBaseWriter();
						int count=0;
						for(int i=0;i<db.getCustomerCount();i++){
							if(db.getCustomers().get(i).phoneNumber.equals(pn.getText())){
								count++;
								db.changeBalance(pn.getText(), Double.parseDouble(nb.getText()));
								System.out.println("Balance succefully Changed");
								break;
							}
				
						}
						if(count==0){
							System.out.println("ERROR");
						}
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			});
			p4.add(phonenum);
			p4.add(pn);
			p4.add(newBalance);
			p4.add(nb);
			p4.add(change);
			p4.add(cancel);
			mainFrame.add(p4);
			mainFrame.setVisible(true);
			
		}
		public void createReceiptPanel(String PhoneNumber) throws IOException{
			String CustName="";
			String CustCard="";
			String CustLocation="";
			String CustPhone = "";
			DataBaseWriter db = new DataBaseWriter();
			for(int i=0;i<db.getCustomerCount();i++){
				if(db.getCustomers().get(i).getPhoneNumber().equals(PhoneNumber)){
					Customer C = db.getCustomers().get(i);
					CustName+=C.getFirstName()+" "+ C.getLastName();
					CustCard+=C.getCardNumber().substring(5, 8);
					CustLocation+=C.Address+" "+C.CityStateZip;
					CustPhone+=C.phoneNumber;
				}
			}
		
			JPanel receiptPanel = new JPanel();
			JLabel CN = new JLabel("Customer: "+CustName);
			JLabel CC = new JLabel("Card Used: XXXXX"+CustCard);
			JLabel CL = new JLabel("Mailing Address: "+CustLocation);
			JLabel CP = new JLabel("Contact Number: "+CustPhone);
			JButton download = new JButton("Download");
			JButton home = new JButton("Home");
			receiptPanel.add(CN);
			receiptPanel.add(CC);
			receiptPanel.add(CL);
			receiptPanel.add(CP);
			receiptPanel.add(download);
			receiptPanel.add(home);
			
			home.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					mainFrame.remove(receiptPanel);
					createGuestHomePanel();
					mainFrame.setVisible(true);
				}
			});
			mainFrame.add(receiptPanel);
			mainFrame.setVisible(true);
			
		}
		public void createChangeMealPricePanel(){
			JPanel p5 = new JPanel();
		
		}
		

		public void createSetServicePanel() {
			JPanel setServicePanel = new JPanel();

			JLabel setSericeLabel = new JLabel("What service needs to be done?");
			JLabel pickRoomLabel = new JLabel("What room would you like to set a service for?");
			JTextField pickRoomTextField = new JTextField();
			JSpinner serviceSpinner = new JSpinner();
			JButton setServiceButton = new JButton("Set service.");

			// need to fill the spinner with whatever services can be performed

			setServiceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// validate room number and service - check to make sure it
					// isn't already scheduled, then on to db
					// send to db that a service has been set
					// will also need to have the services set automatically
					// depending on whether someone has checked in, checked out,
					// will set automatically typical services like turn over
					mainFrame.remove(setServicePanel);
					createManagerHomePanel();
					mainFrame.setVisible(true);
				}
			});

			setServicePanel.add(setSericeLabel);
			setServicePanel.add(pickRoomLabel);
			setServicePanel.add(pickRoomTextField);
			setServicePanel.add(serviceSpinner);
			setServicePanel.add(setServiceButton);

			mainFrame.add(setServicePanel);
			mainFrame.setVisible(true);
		}

		public void createEmployeeHomePanel() {
			JPanel employeeHomePanel = new JPanel();

			JLabel employeeHomeLabel = new JLabel("Welcome, employee.");
			JButton seeServiceRequired = new JButton("See services required.");
			JButton viewHoursLoggedButton = new JButton("View hours logged this week.");
			JButton viewTasksCompletedButton = new JButton("View tasks completed this week.");

			seeServiceRequired.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(employeeHomePanel);
					createServicesRequiredPanel();
					mainFrame.setVisible(true);
				}
			});

			viewHoursLoggedButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(employeeHomePanel);
					createViewHoursLoggedPanel();
					mainFrame.setVisible(true);
				}
			});

			viewTasksCompletedButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(employeeHomePanel);
					createViewTasksCompletedPanel();
					mainFrame.setVisible(true);
				}
			});
			
			employeeHomePanel.add(employeeHomeLabel);
			employeeHomePanel.add(seeServiceRequired);
			employeeHomePanel.add(viewHoursLoggedButton);
			employeeHomePanel.add(viewTasksCompletedButton);

			mainFrame.add(employeeHomePanel);
			mainFrame.setVisible(true);
		}

		public void createServicesRequiredPanel() {
			JPanel servicesRequiredPanel = new JPanel();

			JLabel servicesLabel = new JLabel("Todays required services.");
			JSpinner selectServiceSpinner = new JSpinner();
			JButton setServiceComplete = new JButton("Set service to complete.");
			JButton goBackButton = new JButton("Go back.");

			// need to get all of the things that need to be done today
			// throw them up on the panel along with service number

			setServiceComplete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// get the item selected in the spinner and send to db as
					// completed
					// log who set it to complete
					mainFrame.remove(servicesRequiredPanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(servicesRequiredPanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});

			servicesRequiredPanel.add(servicesLabel);
			servicesRequiredPanel.add(selectServiceSpinner);
			servicesRequiredPanel.add(setServiceComplete);
			servicesRequiredPanel.add(goBackButton);

			mainFrame.add(servicesRequiredPanel);
			mainFrame.setVisible(true);
		}

		public void createViewHoursLoggedPanel() {
			JPanel viewHoursLoggedPanel = new JPanel();

			JLabel viewHoursLoggedLabel = new JLabel("Hours that you have logged.");
			JLabel hoursLabel = new JLabel();
			JButton goBackButton = new JButton("Go back.");

			// use the userID to get the hours in the db for this user and
			// display them for each day

			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(viewHoursLoggedPanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});

			viewHoursLoggedPanel.add(viewHoursLoggedLabel);
			viewHoursLoggedPanel.add(hoursLabel);
			viewHoursLoggedPanel.add(goBackButton);

			mainFrame.add(viewHoursLoggedPanel);
			mainFrame.setVisible(true);
		}

		public void createViewTasksCompletedPanel() {
			JPanel viewTasksCompletedPanel = new JPanel();

			JLabel viewHoursLoggedLabel = new JLabel("Hours that you have logged.");
			JLabel hoursLabel = new JLabel();
			JSpinner selectServiceSpinner = new JSpinner();
			JButton setServiceIncomplete = new JButton("Set service to incomplete.");
			JButton goBackButton = new JButton("Go back.");

			// use the userID to get the tasks completed in the db for this user and
			// display them grouped by the day
			// fill the spinner with the task IDs for each task displayed
			// will be able to set it to incomplete so that they can fix any accidental completes

			setServiceIncomplete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// get the item selected in the spinner and send to db as
					// incompleted
					mainFrame.remove(viewTasksCompletedPanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});
			
			
			goBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.remove(viewTasksCompletedPanel);
					createEmployeeHomePanel();
					mainFrame.setVisible(true);
				}
			});

			viewTasksCompletedPanel.add(viewHoursLoggedLabel);
			viewTasksCompletedPanel.add(hoursLabel);
			viewTasksCompletedPanel.add(selectServiceSpinner);
			viewTasksCompletedPanel.add(setServiceIncomplete);
			viewTasksCompletedPanel.add(goBackButton);

			mainFrame.add(viewTasksCompletedPanel);
			mainFrame.setVisible(true);
		}
		
		
		
		
		
		
		
		
		
		
		

}




