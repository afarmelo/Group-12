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
			if(choice == 4){}
			//Option 5: Add a new jockey
			if(choice == 5){}
			//Option 6:
			if(choice == 6){}
			//Option 7:
			if(choice == 7){}
			//Option 8:
			if(choice == 8){}
			//Option 9: 
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
