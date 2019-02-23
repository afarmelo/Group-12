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

public class TripleCrownDatabase {

    private static String user;
    private static String[] typeMenu = {"1. Select\n",
					"2. Insert\n",
					"3. Update\n",
					"4. Delete\n"};

    private static String[] tableMenu = {"1. Race\n",
					 "2. Horse\n",
					 "3. Jockey\n",
					 "4. Owner\n",
					 "5. Trainer\n",
					 "6. Winner\n",
					 "7. Track\n",
					 "8. RaceDay\n",
					 "9. Prize\n",
					 "10. Record\n"};
    
    /**
     * Display the type Menu
     */
    private static void printTypeMenu() {
	String out = "Hello " + user + ", you've connected to ";
	out += "the database successfully.\n";
	out += "Your options are below.\n";

	System.out.println("\n---------------------------\n" + out);

	for(String str : typeMenu) {
	    System.out.print(str);
	}

	System.out.println("\n---------------------------\n");
	System.out.print("Please enter the number corresponding to"
			 + " the action you would like to perform"
			 + " on the database(1-4): ");
    }

    /**
     * Display the table menu
     */
    private static void printTableMenu() {
	System.out.println("\n---------------------------\n");

	for(String str : tableMenu) {
	    System.out.print(str);
	}

	System.out.println("\n---------------------------\n");
	System.out.print("Please enter the number corresponding to"
			 + " a table in the database(1-10): ");
    }

    /** 
     * Trim a string to get only the words contained within.
     * We know that every string from the menus contains 3
     * characters preceding and a newline following the word we want,
     * so we just trim the string to remove them.
     */
    private static String trimString(String toTrim) {
	String ret = "";
	ret = toTrim.substring(3, toTrim.length()-1);
	return ret;
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
            
	try{	   
	    // Get login info
	    String pass;
	    /**
	    Console cons = System.console();       
	    System.out.print("Please enter your login username: ");
	    user = cons.readLine();
	    char[] passArr = cons.readPassword("Enter your password: ");
	    pass = new String(passArr);
	    System.out.println();
	    **/
	    user = "afarmelo";
	    pass = "Iloveyou12";
	    
	    // Connect to database
	    Class.forName(driver);
	    System.out.println("Connecting to database...");
	    connect = DriverManager.getConnection(url, user, pass);

	    // Create a blank statement
	    System.out.println("Creating statement...");
	    stmnt = connect.createStatement();      

	    // Show the type menu && get a choice
	    printTypeMenu();
	    Scanner scan = new Scanner(System.in);
	    int choice  = scan.nextInt();
	    while (choice < 1  || choice > 4) {
		System.out.println("Your choice has to be between 1-4");
		System.out.print("Please enter a selection(1-4): ");
		choice = scan.nextInt();
	    }
	    String typeChoice = trimString(typeMenu[(choice-1)]);
	    System.out.println("You entered: '" + typeChoice + "'");

	    // Show the table menu && get a choice
	    printTableMenu();
	    scan = new Scanner(System.in);
	    choice  = scan.nextInt();
	    while (choice < 1  || choice > 10) {
		System.out.println("Your choice has to be between 1-10");
		System.out.print("Please enter a selection(1-10): ");
		choice = scan.nextInt();
	    }
	    String tableChoice = trimString(tableMenu[(choice-1)]);
	    System.out.println("You entered: '" + tableChoice + "'");

	    if (typeChoice.equals("Select")) {

	    } else if(typeChoice.equals("Insert")) {

	    } else if(typeChoice.equals("Update")) {

	    } else if(typeChoice.equals("Delete")) {

	    }
	    /*
	    // Execute queries
	    if(choice == 1){
	    String query1;
	    query1 = "SELECT cashvaluegiven FROM prize WHERE cashvaluegiven >= 5000";
	    rs = stmnt.executeQuery(query1);
      
	    while(rs.next()){         
	    int cash = rs.getInt("cashvaluegiven");       
	    System.out.println("cash value given: " + cash);
	    }
	    } else if (method.equals("query2")){	       
	    pStmnt=connect.prepareStatement("SELECT _name FROM trainer WHERE trainerID=?");
	    System.out.print("Enter a trainerID: ");
	    String trainerId = cons.readLine();
	    System.out.println();
	    pStmnt.setString(1, trainerId);
	    rs = pStmnt.executeQuery();
      
	    while(rs.next()){
	    String name=rs.getString("_name");
	    System.out.println("NAME: " + name);
	    }
	    } else if (method.equals("insert")){
	    pStmnt=connect.prepareStatement("INSERT INTO record VALUES (?,?,?)");
	    System.out.print("Enter totalRaces: ");
	    String totRaces = cons.readLine();
	    pStmnt.setString(1,totRaces);
	    System.out.println();
	    System.out.print("Enter racesWon: ");
	    String racesWon = cons.readLine();   
	    pStmnt.setString(2,racesWon);
	    System.out.println();
	    System.out.print("Enter win percent(without percent sign): ");
	    String winPerc = cons.readLine();
	    pStmnt.setString(3, winPerc);
	    System.out.println();
	    pStmnt.executeUpdate();
     
	    System.out.println("success");
	    }
	    */
	} catch(Exception e) {
	    System.out.println("Exception executing statement on database");
	    e.printStackTrace();
	} finally {

	    try {
		if (rs != null)
		    rs.close();
		if (stmnt != null)
		    stmnt.close();
		if (pStmnt != null)
		    pStmnt.close();
		if (connect != null)
		    connect.close();
	    } catch(SQLException se){
		System.out.println("Exception closing resources");
		se.printStackTrace();
	    }
	}
	System.out.println("disconnected..goodbye");
    }
}
