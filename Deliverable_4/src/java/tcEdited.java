package ser322;

import java.io.Console;
import java.io.File;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

public class tcEdited 
{

	private static String user;
	private static String[] menu = {
	    "1. Select race info\n", //kyle
	    "2. Select horse info\n", //sierra
	    "3. Select jockey info\n", //sierra
	    "4. Add a new horse\n", //abigail
	    "5. Add a new jockey\n", //abigail
	    "6. Delete a horse\n", //adam
	    "7. Delete a jockey\n", //adam
	    "8. Join Horse and Jockey\n", //kyle
	    "9. Join Horse and Winner\n"}; //sierra
	/**
	* Display a menu to the user
	*/
	private static void printMenu() {
		String out = "Hello " + user + ", you've connected to ";
		out += "the database successfully.\n";
		out += "Your options are below.\n";

		System.out.println("\n---------------------------\n" + out);

		for(String str : menu) {
		System.out.print(str);
		}

		System.out.println("\n---------------------------\n");
		System.out.print("Please enter a selection(1-9): ");
	}

	public static void main(String[] args) {

		Connection connect = null;
		Statement stmnt = null;
		PreparedStatement pStmnt=null;
		ResultSet rs = null;

		if (args.length < 2) {
			System.out.print("USAGE: java ser322.TripleCrownDatabase");
			System.out.println("<url> <driver>");
			System.exit(0);
		}

		// Database information
		String url = args[0];
		String driver = args[1];

		try
		{	   
			// Get login info
			Console cons = System.console();       
			System.out.print("Please enter your login username: ");
			user = cons.readLine();
			char[] passArr = cons.readPassword("Enter your password: ");
			String pass = new String(passArr);
			System.out.println();

			// Connect to database
			Class.forName(driver);
			System.out.println("Connecting to database...");
			connect = DriverManager.getConnection(url, user, pass);

			// Create a blank statement
			System.out.println("Creating statement...");
			stmnt = connect.createStatement();      

			// Show the menu && get a choice
			printMenu();
			Scanner scan = new Scanner(System.in);
			int choice  = scan.nextInt();

			while(choice < 1  || choice > 7)
			{
				System.out.println("Your choice has to be between 1-7");
				System.out.print("Please enter a selection(1-7): ");
				choice = scan.nextInt();
			}

			System.out.println("You entered: " + menu[(choice-1)]);

			//View race info query
			//Option 1: Select race info
			if(choice == 1)	
			{
				String sql = "SELECT * FROM race";

				rs = stmnt.executeQuery(sql);

				System.out.printf("%10s, %20s, %20s, %20s, %10s, %20s", "RaceID", "Track", 
						"Distance", "Prizewon", "Gates", "Participants\n");
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) );

					System.out.println();
				}
        
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();
			}
			//Option 2: Select horse info
			if(choice==2)
			{
				String sql = "SELECT * FROM horse";

				rs = stmnt.executeQuery(sql);
        
				System.out.printf("%10s, %20s, %20s, %20s, %10s, %20s, %20s, %20s, %10s, %20s", "horseID", "_name", 
						"height", "weight", "_value", "age", "breed", "ownerID", "jockeyID", "trainerID\n");
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s, %50s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),  rs.getString(7),  
							  rs.getString(8),  rs.getString(9),  rs.getString(10) );

					System.out.println();
				}
        
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();
			}
			//Option 3: Select jockey info
			if(choice == 3)	
			{
				String sql = "SELECT * FROM jockey";

				rs = stmnt.executeQuery(sql);
        
				System.out.printf("%10s, %20s, %20s, %20s, %10s, %20s, %20s", "jockeyID", "_name", 
						"colors", "height", "weight", "_rank", "earnings\n");
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
							 rs.getString(7));

					System.out.println();
				}

				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();
			}
			
			//Option 4: Add a new horse
			if(choice == 4){
				//declare temp variables 
				int HORSEid, HEIGHT, WEIGHT, VALUE, AGE, OWNERid, JOCKEYid, TRAINERid;
				String NAME, BREED;
				Scanner scan = new Scanner(System.in);
				//collect data about the horse that is being added to the database
				System.out.println("You have selected the option to add a horse.");
				System.out.println("The required fields are Horse ID.");
				System.out.println("If any non-required fields are not applicable to your horse please enter NULL.");
				System.out.println("Please enter an number that represents the horse ID:");
				HORSEid = scan.nextInt();
				System.out.println("Please enter the name of the horse:");
				NAME = scan.nextLine();
				System.out.println("Please enter the height of the horse:");
				HEIGHT = scan.nextInt();
				System.out.println("Please enter the weight of the horse:");
				WEIGHT = scan.nextInt();
				System.out.println("Please enter the value (net worth) of the horse:");
				VALUE = scan.nextInt();
				System.out.println("Please enter the age of the horse:");
				AGE = scan.nextInt();
				System.out.println("Please enter the breed of the horse:");
				BREED = scan.nextLine();
				System.out.println("Please enter the Owner ID number that corresponds to the horse:");
				OWNERid = scan.nextInt();
				System.out.println("Please enter the Jockey ID number that corresponds to the horse:");
				JOCKEYid = scan.nextInt();
				System.out.println("Please enter the Trainer ID number that corresponds to the horse:");
				TRAINERid = scan.nextInt();
				
				//create the prepared statement 
				String query = " insert into horse (horseID, _name, height, weight, _value, age, breed, ownerID, jockeyID, trainerID)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pStmnt = connect.prepareStatement(query);
				pStmnt.setInt(1, HORSEid);
				pStmnt.setString(2, NAME);
				pStmnt.setInt(3, HEIGHT);
				pStmnt.setInt(4, WEIGHT);
				pStmnt.setInt(5, VALUE);
				pStmnt.setInt(6, AGE);
				pStmnt.setString(7, BREED);
				pStmnt.setInt(8, OWNERid);
				pStmnt.setInt(9, JOCKEYid);
				pStmnt.setInt(10, TRAINERid);
				
				// execute the preparedstatement
      				pStmnt.execute();
				Sytem.out.println("The horse has been successfully inserted into the database!");
				
				/*
				//print horse table (with the new horse included)
				String sql = "SELECT * FROM horse";
				rs = stmnt.executeQuery(sql);
				System.out.printf("%10s, %20s, %20s, %20s, %10s, %20s, %20s, %20s, %10s, %20s", "horseID", "_name", 
						"height", "weight", "_value", "age", "breed", "ownerID", "jockeyID", "trainerID\n");
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();
				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s, %50s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),  rs.getString(7),  
							  rs.getString(8),  rs.getString(9),  rs.getString(10) );
					System.out.println();
				}
        
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();*/
			}
			//Option 5: Add a new jockey
			if(choice == 5){//declare temp variables 
				int JOCKEYid, HEIGHT, WEIGHT, RANK, EARNINGS;
				String NAME, COLORS;
				Scanner scan = new Scanner(System.in);
				//collect data about the jockey that is being added to the database
				System.out.println("You have selected the option to add a jockey.");
				System.out.println("The required fields are Jockey ID.");
				System.out.println("If any non-required fields are not applicable to your jockey please enter NULL.");
				System.out.println("Please enter an number that represents the jockey ID:");
				JOCKEYid = scan.nextInt();
				System.out.println("Please enter the name of the jockey:");
				NAME = scan.nextLine();
				System.out.println("Please enter the colors worn by the jockey:");
				COLORS = scan.nextLine();
				System.out.println("Please enter the height of the jockey:");
				HEIGHT = scan.nextInt();
				System.out.println("Please enter the weight of the jockey:");
				WEIGHT = scan.nextInt();
				System.out.println("Please enter the rank of the jockey:");
				RANK = scan.nextInt();
				System.out.println("Please enter the earnings accrued by the jockey:");
				EARNINGS = scan.nextInt();
				
				//create the prepared statement 
				String query = " insert into jockey (jockeyID, _name, colors, height, weight, _rank, earnings)"+ " values (?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pStmnt = connect.prepareStatement(query);
				pStmnt.setInt(1, JOCKEYid);
				pStmnt.setString(2, NAME);
				pStmnt.setString(3, COLORS);
				pStmnt.setInt(4, HEIGHT);
				pStmnt.setInt(5, WEIGHT);
				pStmnt.setInt(6, RANK);
				pStmnt.setInt(7, EARNINGS);
				
				// execute the preparedstatement
      				pStmnt.execute();
				Sytem.out.println("The jockey has been successfully inserted into the database!");
				/*
				//print jockey table (with the new jockey included)
				String sql = "SELECT * FROM jockey";

				rs = stmnt.executeQuery(sql);
        
				System.out.printf("%10s, %20s, %20s, %20s, %10s, %20s, %20s", "jockeyID", "_name", 
						"colors", "height", "weight", "_rank", "earnings\n");
				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
							 rs.getString(7));

					System.out.println();
				}

				for(int i = 0; i < 150; i++) { System.out.print("-"); }
				System.out.println();*/
			}
			//Option 6: Delete a horse
			if(choice == 6) {
			    String sql = "DELETE FROM horse where horseID = ?;";
			    pStmnt = connect.prepareStatement(sql);
			    System.out.print("Enter a horseID for the horse to remove: ");
			    int horseID = scan.nextInt();
			    pStmnt.setInt(1, horseID);
			    if (pStmnt.executeUpdate() > 0) {
				System.out.println("Horse with horseID: " + horseID + " deleted successfully");
			    }
			}
			//Option 7: Delete a jockey
			if(choice == 7){
			    String sql = "DELETE FROM jockey where jockeyID = ?;";
			    pStmnt = connect.prepareStatement(sql);
			    System.out.print("Enter a jockeyID for the jockey to remove: ");
			    int jockeyID = scan.nextInt();
			    pStmnt.setInt(1, jockeyID);
			    if (pStmnt.executeUpdate() > 0) {
				System.out.println("Jockey with jockeyID: " + jockeyID + " deleted successfully");
			    }
			}
			//Option 8: Join Horse and Jockey
			if(choice == 8){}
			//Option 9: Join Horse and Winner
			if(choice == 9)
			{
			
				String sql = "SELECT * FROM horse JOIN winner ON horse.horseID=winner.horseID";

				rs = stmnt.executeQuery(sql);

				System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s", "horseID", "_name", 
						"height", "weight", "_value", "age", "breed", "ownerID", "jockeyID", "trainerID", "raceID",
						 "completiontime", "prizewon");
				for(int i = 0; i < 220; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("%20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s, %50s, %30s, %50s, %20s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							  rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));

					System.out.println();
				}

				for(int i = 0; i < 220; i++) { System.out.print("-"); }
				System.out.println();
			}
		
		} catch(Exception e) {
			System.out.println("Exception executing statement on database");
			e.printStackTrace();
		} 
		finally {

			try {
				if (rs != null)
				   rs.close();
				if (stmnt != null)
				   stmnt.close();
				if (pStmnt != null)
				   pStmnt.close();
				if (connect != null)
				   connect.close();
			} 
			catch(SQLException se){
				System.out.println("Exception closing resources");
				se.printStackTrace();
			}
		}
		
		System.out.println("disconnected..goodbye");

	} //end main()

} //end .java
