import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
Name: Oscar Veloria
Date: April 29th
Description: 

This program is for a class database containing Students with their first/last names, student Id's,
and their 3 assignments along with their grades, max points for the assignments and the student's earned points.
After running the software, the user is presented with a menu of 5 options and a quit option.
The 5 options create special queries where we use JDBC to fetch those specific results to the user.

// --- key learning points in this hw --- //
This is a Java program using JDBC to interact with 
relational databases mainly using Statement object, ResultSet object and the Connection object.
After importing the necessary classes from JDBC and java.sql, we use DriverManager.getConnection to 
create a Connection object.
We then execute a query with the Statement object to select records from a table in the database.
The ResultSet comes from that executed query, where we can fetch the records.


*/

public class Source {
	
	public Source() 
	{
		
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Connection dbConnect = null;
	
		//
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			dbConnect = DriverManager.getConnection("jdbc:sqlite:classDB.db");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		Statement query = null;
		ResultSet results = null;
		
		try {
			query = dbConnect.createStatement(); // connection objectbuilds a statement obj connected to the dbase
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int answer;
		// ---- start while loop ---- //
		do {
			makeMenu();
			answer = input.nextInt();
			
			// ---- QUERY 1 ---- //
			if(answer == 1) {
				
				String question = "SELECT * FROM studentTable";
				
				try {
					results = query.executeQuery(question);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				try {
					while(results.next()) {
						System.out.println("First name: " + results.getString("first_name") 
										 + " Last name: " + results.getString("last_name") 
										 + " Student Id: " + results.getString("student_id"));
					
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				
				}
				// ---- END OF QUERY 1 ---- //
				// ---- QUERY 2 ---- //
			} else if(answer == 2) {
				
				
				String question = "SELECT studentTable.first_name, studentTable.last_name,"
						+ " assnTable.assn_num, assnTable.max_points, assnTable.earned_points, assnTable.student_id"
						+ " FROM studentTable" 
						+ " INNER JOIN assnTable ON studentTable.student_id=assnTable.student_id" + " ORDER BY first_name";
				
				try {
					results = query.executeQuery(question);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				
				int count = 0;
				try {
					while(results.next()) {

						if(count == 0) {
							count++;
							//print out student's name,
							System.out.println(results.getString("last_name") + ", " 
									 + results.getString("first_name"));

						} else if(count < 2) {
							count++;
						} else {
							count = 0;
						}
						
						//go through entire results table to print out that student's hw scores
								System.out.println(results.getInt("assn_num") + ":" 
										 + results.getInt("earned_points") + "/"
										 + results.getInt("max_points"));

										 
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			// ---- END OF QUERY 2 ---- //
			// ---- QUERY 3 ---- //
			} else if (answer == 3) {
				
				int query3Search = 0;
				
				String question = "SELECT studentTable.first_name, studentTable.last_name,"
						+ " assnTable.assn_num, assnTable.max_points, assnTable.earned_points, assnTable.student_id"
						+ " FROM studentTable" 
						+ " INNER JOIN assnTable ON studentTable.student_id=assnTable.student_id" + " ORDER BY first_name";
				try 
				{
					results = query.executeQuery(question);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				System.out.println("Choose which search option for a student" 			
									+ "\n1. Search by last name"
									+ "\n2. Search by student id" );
				query3Search = input.nextInt();
				
				String lastName = "";
				String studentId = "";
				boolean firstEntry = true; 
				if(query3Search == 1)
				{
					
					System.out.println("enter last name, Capital letter first");
					lastName = input.next();
					firstEntry = true; 
					try 
					{
						while(results.next()) {
							if(results.getString("last_name").equals(lastName)) {
								
								if(firstEntry) {
									System.out.println(results.getString("last_name") + ", " 
											 + results.getString("first_name"));
									firstEntry = false;
								}
								
								System.out.println(results.getInt("assn_num") + ":" 
										 + results.getInt("earned_points") + "/"
										 + results.getInt("max_points"));
							}
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
				} 
				else if (query3Search == 2) 
				{
					System.out.println("enter student id");
					studentId = input.next();
					firstEntry = true; 
					
					try 
					{
						while(results.next()) {
							if(results.getString("student_id").equals(studentId)) {
								
								if(firstEntry) {
									System.out.println(results.getString("last_name") + ", " 
											 + results.getString("first_name"));
									firstEntry = false;
								}
								
								System.out.println(results.getInt("assn_num") + ":" 
										 + results.getInt("earned_points") + "/"
										 + results.getInt("max_points"));
							}
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				} 
				else 
				{
					System.out.println("restarting Student Database Program...enter a 1 or 2 next time");
				}
				
				// ---- END OF QUERY 3 ---- //
				// ---- QUERY 5 ---- //
			} else if (answer == 5) {
				
				
				String question = "";
				
				System.out.println("Which assignment for class average? 1,2 or 3" 			
						+ "\n1. Assignment 1"
						+ "\n2.  Assignment 2"
						+ "\n3.  Assignment 3" );
				int assnNumber = input.nextInt();
				
				if(assnNumber == 1) {
					question = "SELECT AVG(earned_points) FROM assnTable WHERE assn_num=1";
				}else if(assnNumber == 2) {
					question = "SELECT AVG(earned_points) FROM assnTable WHERE assn_num=2";
				}else if(assnNumber == 3) {
					question = "SELECT AVG(earned_points) FROM assnTable WHERE assn_num=3";
				}
				
				try {
					results = query.executeQuery(question);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				try {
					while(results.next()) {
						System.out.println("Class average for assignment " + assnNumber + ": "  +results.getDouble(1));
				
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				
				}
			}
			// ---- END OF QUERY 5 ---- //

			
			
		} while(answer != 0); // ---- end of while loop code ---- //
		
		

	} // ---- end of main() ---- //
	
	// creates menu for the user
	private static void makeMenu() {
		System.out.println("|| --------------------------------- ||" 
				+ "\nWelcome to Student Database, enter input for query below "
				+ "\n\n1. Display all students in database"
				+ "\n2. Display all students and their grades in database" 
				+ "\n3. Display a specific student's grades"
				+ "\n4. Display grades of students with last names matching..."
				+ "\n5. Calculate the avg of a specific assignment"
				+ "\n0. End program"
				+ "\n|| --------------------------------- ||" );
	}
	
	
}
