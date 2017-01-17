//	JDBC Test Program - Project 1 Environment Setup

import java.sql.*;			//Enables SQL Processing
import java.sql.Date;
import java.util.*;			//For Scanner Class


public class JDBC {
	
	public static void printMenu() {
		System.out.println("Option 1: Find Movies for a Star");
		System.out.println("Option 2: Insert a new Star");
		System.out.println("Option 3: Insert a new Customer");
		System.out.println("Option 4: Delete a Customer");
		System.out.println("Option 5: Print MetaData");
		System.out.println("Option 6: Enter a SQL Query");
		System.out.println("Option 7: Exit to log in");
		System.out.println("Option 8: Exit");
		
	}
	
	public void printMovie() {
		System.out.print("Enter star name or ID: ");
	}
	
	public static void insertStar(Connection connection, Scanner s) {
		System.out.print("Insert star's last name or only name (required): ");
		String last_name = s.nextLine();
		System.out.print("Insert star's first name (optional): ");
		String first_name = s.nextLine();
		System.out.print("Insert star's date of birth yyyy-mm-dd (optional): ");
		String date = s.nextLine();
		Date dob = null;
		if (!date.equals("")) {
			dob = Date.valueOf(s.nextLine());
		}
		System.out.print("Insert star's photo url (optional): ");
		String photo_url = s.nextLine();
		if (photo_url.equals("")) {
			photo_url = null;
		}
		try {
			Statement insert = connection.createStatement();
			String sqlQuery = "INSERT INTO stars (first_name, last_name, dob, photo_url) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);
			preparedStmt.setString(1, first_name);
			preparedStmt.setString(2, last_name);
			preparedStmt.setDate(3, dob);
			preparedStmt.setString(4,photo_url);
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg) throws Exception {
		
		Scanner s = new Scanner(System.in);
		boolean active = true;
		String username;
		String password;
		int command;
		
		System.out.println("Welcome to JDBC! Please enter your credentials");
		
		while (active) {
			
			System.out.print("Username: ");
			username = s.nextLine();
			System.out.print("Password: "); 			//NOTE: password visible in console
			password = s.nextLine();
			
			//Incorporate MySQL Driver
			try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//Connect to Database
			System.out.println("Connecting to Database...");
			Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", username, password); 
			System.out.println("Connected!");
			System.out.println();
			System.out.println("Please select an option:");
			printMenu();
			System.out.print("Option: ");
			command = s.nextInt();
			s.nextLine();					//clear buffer
			
			switch (command) {
			case 1:				//print movies for a given star
				
				break;
			case 2:				//insert new star 
				insertStar(connection, s);
				break;
			case 3:				//insert new customer
				break;
			case 4:				//delete customer
				break;
			case 5:				//print metadata
				break;
			case 6:				//enter valid command
				break;
			case 7:				//exit to log in
				break;
			case 8:				//exit program
				return;
			default:
					
			}
			
			
			//Create and execute an SQL statement to select all the movies for a given star
			Statement select = connection.createStatement();
			ResultSet result = select.executeQuery("select * from movies m, stars s, stars_in_movies i where first_name = 'Ben' and last_name = 'Stiller'");
			
		
			} catch (Exception e){
				System.out.println("Connection Failed! Incorrect Username or Password.");
				System.out.println(e.toString());
				System.out.println("Try Again.");
			}
		}
		s.close();
	}

}
