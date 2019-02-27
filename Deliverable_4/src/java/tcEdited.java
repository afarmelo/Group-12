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
	    "1. Select race info\n",
	    "2. Select horse info\n",
	    "3. Select jockey info\n",
	    "4. Add a new horse\n",
	    "5. Add a new jockey\n",
	    "6. Delete a horse\n",
	    "7. Delete a jockey\n",
	    "8. Join Horse and Jockey\n",
	    "9. Join Horse and Winner\n",
	    "10. Update horse info\n",
	    "11. Update jockey info\n"};
    
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
		System.out.print("Please enter a selection(1-11): ");
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

			while(choice < 1 || choice > 11)
			{
				System.out.println("Your choice has to be between 1 - 11"); 
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

				System.out.printf("|%9s|%15s|%9s|%17s|%9s|%9s|", "RaceID", "Track", 
						"Distance", "Prizewon", "Gates", "Participants\n");
				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("|%9s|%15s|%9s|%17s|%9s|%9s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) );

					System.out.println();
				}
        
				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();
			}
			//Option 2: Select horse info
			else if(choice==2)
			{
				String sql = "SELECT * FROM horse";

				rs = stmnt.executeQuery(sql);
        
				System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|", "horseID", "_name", 
						"height", "weight", "_value", "age", "breed", "ownerID", "jockeyID", "trainerID\n");
				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),  rs.getString(7),  
							  rs.getString(8),  rs.getString(9),  rs.getString(10) );
					System.out.println();
				}
        
				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();
			}
			//Option 3: Select jockey info
			else if(choice == 3)	
			{
				String sql = "SELECT * FROM jockey";

				rs = stmnt.executeQuery(sql);
        
				System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|", "jockeyID", "_name", 
						"colors", "height", "weight", "_rank", "earnings\n");
				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|", rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
							 rs.getString(7));

					System.out.println();
				}

				for(int i = 0; i < 120; i++) { System.out.print("-"); }
				System.out.println();
			}
			
			//Option 4: Add a new horse
			else if(choice == 4){
				//declare temp variables 
				int HORSEid, H, W, V, A, OWNERid, JOCKEYid, TRAINERid;
				String N, B;
				//collect data about the horse that is being added to the database
				System.out.println("You have selected the option to add a horse.");
				System.out.println("The required fields are Horse ID.");
				System.out.println("If any non-required fields are not applicable to your horse please enter NULL.");
				System.out.println("Please enter an number that represents the horse ID:");
				HORSEid = scan.nextInt();
				System.out.println("Please enter the name of the horse:");
				N = scan.nextLine();
				System.out.println("Please enter the height of the horse:");
				H = scan.nextInt();
				System.out.println("Please enter the weight of the horse:");
				W = scan.nextInt();
				System.out.println("Please enter the value (net worth) of the horse:");
				V = scan.nextInt();
				System.out.println("Please enter the age of the horse:");
				A = scan.nextInt();
				System.out.println("Please enter the breed of the horse:");
				B = scan.nextLine();
				System.out.println("Please enter the Owner ID number that corresponds to the horse:");
				OWNERid = scan.nextInt();
				System.out.println("Please enter the Jockey ID number that corresponds to the horse:");
				JOCKEYid = scan.nextInt();
				System.out.println("Please enter the Trainer ID number that corresponds to the horse:");
				TRAINERid = scan.nextInt();
				
				//create the prepared statement 
				String query = " insert into horse (horseID, _name, height, weight, _value, age, breed, ownerID, jockeyID, trainerID)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				pStmnt = connect.prepareStatement(query);
				pStmnt.setInt(1, HORSEid);
				pStmnt.setString(2, N);
				pStmnt.setInt(3, H);
				pStmnt.setInt(4, W);
				pStmnt.setInt(5, V);
				pStmnt.setInt(6, A);
				pStmnt.setString(7, B);
				pStmnt.setInt(8, OWNERid);
				pStmnt.setInt(9, JOCKEYid);
				pStmnt.setInt(10, TRAINERid);
				
				// execute the preparedstatement
      				pStmnt.execute();
				System.out.println("The horse has been successfully inserted into the database!");
			}
			//Option 5: Add a new jockey
			else if(choice == 5){//declare temp variables 
				int JOCKEYid, H, W, R, E;
				String N, C;
				//collect data about the jockey that is being added to the database
				System.out.println("You have selected the option to add a jockey.");
				System.out.println("The required fields are Jockey ID.");
				System.out.println("If any non-required fields are not applicable to your jockey please enter NULL.");
				System.out.println("Please enter an number that represents the jockey ID:");
				JOCKEYid = scan.nextInt();
				System.out.println("Please enter the name of the jockey:");
				N = scan.nextLine();
				System.out.println("Please enter the colors worn by the jockey:");
				C = scan.nextLine();
				System.out.println("Please enter the height of the jockey:");
				H = scan.nextInt();
				System.out.println("Please enter the weight of the jockey:");
				W = scan.nextInt();
				System.out.println("Please enter the rank of the jockey:");
				R = scan.nextInt();
				System.out.println("Please enter the earnings accrued by the jockey:");
				E = scan.nextInt();
				
				//create the prepared statement 
				String query = " insert into jockey (jockeyID, _name, colors, height, weight, _rank, earnings)"+ " values (?, ?, ?, ?, ?, ?, ?)";
				
				pStmnt = connect.prepareStatement(query);
				pStmnt.setInt(1, JOCKEYid);
				pStmnt.setString(2, N);
				pStmnt.setString(3, C);
				pStmnt.setInt(4, H);
				pStmnt.setInt(5, W);
				pStmnt.setInt(6, R);
				pStmnt.setInt(7, E);
				
				// execute the preparedstatement
      				pStmnt.execute();
				System.out.println("The jockey has been successfully inserted into the database!");
			}
			//Option 6: Delete a horse
			else if(choice == 6) {
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
			else if(choice == 7){
			    String sql = "DELETE FROM jockey where jockeyID = ?;";
			    pStmnt = connect.prepareStatement(sql);
			    System.out.print("Enter a jockeyID for the jockey to remove: ");
			    int jockeyID = scan.nextInt();
			    pStmnt.setInt(1, jockeyID);
			    if (pStmnt.executeUpdate() > 0) {
				System.out.println("Jockey with jockeyID: " + jockeyID + " deleted successfully");
			    }
			}
			//Option 8: Join Horse and Jockey (aka an employed jockey)
			else if(choice == 8){
			
			
				String sql = "SELECT * FROM jockey JOIN horse ON horse.jockeyID=jockey.jockeyID";

				rs = stmnt.executeQuery(sql);

				System.out.printf("|%10s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%15s|%9s|%9s|%9s|%5s|%11s|%9s|%9s|%9s|", 
						"Jockey ID", "Name", "Colors", "Height", "Weight", "Rank", "Earnings",
							"Horse ID", "Name", "Height", "Weight", "Value", "Age", "Breed", "ownerID",
							"jockeyID", "trainerID|\n");

				for(int i = 0; i < 176; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("|%10s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%15s|%9s|%9s|%9s|%5s|%11s|%9s|%9s|%9s|",
							rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9),rs.getString(10), rs.getString(11), rs.getString(12), 
							rs.getString(13), rs.getString(14),rs.getString(15),
							rs.getString(16),rs.getString(17));

					System.out.println();
				}
				
				System.out.print("|");
				for(int i = 0; i < 176; i++) { System.out.print("-"); }
				System.out.println();
			}
	


			//Option 9: Join Horse and Winner
			else if(choice == 9)
			{
			
				String sql = "SELECT * FROM horse JOIN winner ON horse.jockeyID=jockey.jockeyID";

				rs = stmnt.executeQuery(sql);

				System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|", "horseID", "_name", 
						"height", "weight", "_value", "age", "breed", "ownerID", "jockeyID", "trainerID", "raceID",
						 "completiontime", "prizewon|");
				for(int i = 0; i < 135; i++) { System.out.print("-"); }
				System.out.println();

				while(rs.next())
				{
					System.out.printf("|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|\n",
							rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							  rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));

					System.out.println();
				}

				for(int i = 0; i < 135; i++) { System.out.print("-"); }
				System.out.println();
			}
			else if(choice == 10){ // update horse
			    String[] params = new String[]{
				        "_name", "height", "weight",
					"_value", "age", "breed",
					"ownerID", "jockeyID", "trainerID"};

			    String sql = "UPDATE horse SET";
			    String sqlWhere = "WHERE horseID=?";

			    System.out.print("Enter the horseID for the horse to update: ");
			    String horseID = scan.next();
			    System.out.println();

			    int paramCount = 0;
			    Object[] varArgs = new Object[9];
			    for (int i=0; i<params.length; i++) {
				System.out.print("Would you like to update " + params[i] + "?(y/n) ");
				String paramChoice = scan.next();
				if (paramChoice.toLowerCase().equals("y")) {
				    sql += " " + params[i] + "=?,";
				    System.out.print("Enter a value for " + params[i] + ": ");
				    String val = scan.next();
				    if (!params[i].equals("_name") && !params[i].equals("_value")
					&& !params[i].equals("breed")) {
					Integer intVal = Integer.parseInt(val);
					varArgs[paramCount] = (intVal);
				    } else {
					varArgs[paramCount] = (val);
				    }
				    paramCount++;
				}
				System.out.println();
			    }

			    String fullStatement = sql.substring(0, sql.length()-1) + " " + sqlWhere + ";";
			    pStmnt = connect.prepareStatement(fullStatement);
			    System.out.println(fullStatement);

			    int i;
			    for (i=0; i<paramCount; i++) {
				if (varArgs[i].getClass().equals(java.lang.Integer.class)) {
				    pStmnt.setInt(i+1, (int)varArgs[i]);
				} else {
				    pStmnt.setString(i+1, (String)varArgs[i]);
				}
				System.out.println("Arg: " + varArgs[i] + " Arg type: " + varArgs[i].getClass());
			    }

			    pStmnt.setInt(++i, Integer.parseInt(horseID));

			    if (pStmnt.executeUpdate() > 0) {
				System.out.println("Horse with horseID: " + horseID + " updated successfully.");
			    }
			}
			else if(choice == 11){ // update jockey
			    String[] params = new String[]{
				        "_name", "colors", "height",
					"weight", "_rank", "earnings"};

			    String sql = "UPDATE jockey SET";
			    String sqlWhere = "WHERE jockeyID=?";

			    System.out.print("Enter the jockeyID for the jockey to update: ");
			    String jockeyID = scan.next();
			    System.out.println();

			    int paramCount = 0;
			    Object[] varArgs = new Object[9];
			    for (int i=0; i<params.length; i++) {
				System.out.print("Would you like to update " + params[i] + "?(y/n) ");
				String paramChoice = scan.next();
				if (paramChoice.toLowerCase().equals("y")) {
				    sql += " " + params[i] + "=?,";
				    System.out.print("Enter a value for " + params[i] + ": ");
				    String val = scan.next();
				    if (!params[i].equals("_name") && !params[i].equals("colors")) {
					Integer intVal = Integer.parseInt(val);
					varArgs[paramCount] = (intVal);
				    } else {
					varArgs[paramCount] = (val);
				    }
				    paramCount++;
				}
				System.out.println();
			    }

			    String fullStatement = sql.substring(0, sql.length()-1) + " " + sqlWhere + ";";
			    pStmnt = connect.prepareStatement(fullStatement);
			    System.out.println(fullStatement);

			    int i;
			    for (i=0; i<paramCount; i++) {
				if (varArgs[i].getClass().equals(java.lang.Integer.class)) {
				    pStmnt.setInt(i+1, (int)varArgs[i]);
				} else {
				    pStmnt.setString(i+1, (String)varArgs[i]);
				}
			    }

			    pStmnt.setInt(++i, Integer.parseInt(jockeyID));

			    if (pStmnt.executeUpdate() > 0) {
				System.out.println("Jockey with jockeyID: " + jockeyID + " updated successfully.");
			    }
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
