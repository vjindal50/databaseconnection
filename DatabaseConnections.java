package trials;

import java.sql.*;
import java.util.Scanner;


public class DatabaseConnections {
	
	// JDBC driver name and database URL  
	static final String DB_URL = "jdbc:mysql://localhost:3306/***";

  	//  Database credentials
  	static final String USER = "****";
  	static final String PASS = "******";
  	
  	// getting data from database
  	public static void GetData(String uname){
		 Connection conn = null;
		 Statement stmt = null; 
		 try{
			  //Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * from Info where Username = '" + uname +"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      //Extract data from result set
		      while(rs.next()){  //for each survey
		    	  String Username = rs.getString("Username");
		    	  String Password = rs.getString("Password");
		    	  String Email = rs.getString("Email");
		    	  int ContactNO  = rs.getInt("ContactNO");	 
		    	  System.out.print("Username, password, email is : " + Username +" ,  "+ Password + " , " + Email+ " , " +", and Contact no is : " + ContactNO);
			      System.out.println();
			         
		      }
		      //Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   System.out.println("Goodbye!");
  	}
  	
  	
  	// Storing data into the databasse
  	
  	public static void SetData(String uname, String pwd, String email, String coNo){
		 Connection conn = null;
		 Statement stmt = null; 
		 try{
			  //Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      String sql;
		      sql = "Insert INTO Info(Username, Password, Email, ContactNO) values ('" + uname +"', '" + pwd + "', '" + email + "', " + coNo + ");";
		      // executing insert command
		      PreparedStatement pstmt = conn.prepareStatement(sql);
		      pstmt.executeUpdate();
		      //System.out.println(sql);
		     
		      //clean up
		      pstmt.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   System.out.println("Goodbye!");
 	}

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("enter 0 to extract data and 1 to store data : ");
		int response = s.nextInt();
		
		if(response == 0){		
			System.out.println("Enter the username : ");
			String uname1 = s.nextLine();
			String Username = s.nextLine();
			GetData(Username);
		}
		
		else if (response == 1) {		
			System.out.print("Enter the username : ");
			String uname1 = s.nextLine();
			String uname = s.nextLine();
			System.out.print("Enter the password : ");
			String pwd = s.nextLine();
			System.out.print("Enter the email : ");
			String email = s.nextLine();
			System.out.print("Enter the phonenumber : ");
			String coNo = s.nextLine();
			SetData(uname, pwd, email, coNo);
		}
		
		else {
			System.out.println("enter a valid input ");
		}

	}

}