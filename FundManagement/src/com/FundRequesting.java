package com;

import java.sql.*;

public class FundRequesting {
	
	private Connection connect() 
	 { 
	     Connection con = null; 
	  try
	  { 
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 
	     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fundrequest","root","root");
	     
	     System.out.println("sucssessfully connected");
	 
	  } 
	  catch (Exception e){
		 e.printStackTrace();
		 } 
	 
	 
	  return con; 
	  
	 } 

	public String insertDetails(String r_ID, String r_name, String r_age , String r_address, String r_email, String r_phoneNum,String proj_name,String proj_desc, String fund)  {
		 
		String output = "";
		 
		try{
			   
		   Connection con = connect(); 
		   if (con == null) {
			   
			   return "Error while connecting to the database for inserting";
			   
			   } 
		   
		//create a prepared statement
			 String query = " insert into researcher(`rID`,`rName`,`rAge`,`rAddress`,`rEmail`,`rPhoneNum`,`projName`,`projDesc`,`fund`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			   // binding values
			   preparedStmt.setInt(1, Integer.parseInt(r_ID)); 
			   preparedStmt.setString(2, r_name); 
			   preparedStmt.setInt(3, Integer.parseInt(r_age)); 
			   preparedStmt.setString(4, r_address); 
			   preparedStmt.setString(5, r_email); 
			   preparedStmt.setInt(6, Integer.parseInt(r_phoneNum)); 
			   preparedStmt.setString(7, proj_name);
			   preparedStmt.setString(8, proj_desc); 
			   preparedStmt.setDouble(9, Double.parseDouble(fund)); 
			   
			   // execute the statement3
			   preparedStmt.execute(); 
			   con.close(); 
			   output = "Inserted successfully"; 
		   
		} catch (Exception e) {
		  
		    output = "Error while inserting"; 
		    System.err.println(e.getMessage()); 
		  }
		  
		 return output; 
			
	}
	
	
	public String readFundReqs() {
		
	     String output = ""; 
	     
	     try{ 
	            
	    	      Connection con = connect(); 
	              if (con == null) {
	            	         return "Error while connecting to the database for reading";
	            	         
	              } 
	      // Prepare the html table to be displayed
	      output = "<table border='1'><tr><th>rID</th>" +
	      "<th>rName</th>" + 
	      "<th>rAge</th>" + 
	      "<th>rAddress</th>" +
	      "<th>rEmail</th>" +
	      "<th>rPhoneNum</th>" +
	      "<th>projName</th>" +
	      "<th>projDesc</th>" +
	      "<th>fund</th></tr>"; 
	 
	      String query = "select * from researcher"; 
	      Statement stmt = con.createStatement(); 
	      ResultSet rs = stmt.executeQuery(query); 
	      // iterate through the rows in the result set
	      while (rs.next()) {
	    	  
	             String r_ID = Integer.toString(rs.getInt("rID")); 
	             String r_name = rs.getString("rName"); 
	             String r_age = Integer.toString(rs.getInt("rAge")); 
	             String r_address = rs.getString("rAddress"); 
	             String r_email = rs.getString("rEmail"); 
	             String r_phonenum = Integer.toString(rs.getInt("rPhoneNum")); 
	             String proj_name = rs.getString("projName"); 
	             String proj_desc = rs.getString("projDesc"); 
	             String fund = Double.toString(rs.getDouble("fund")); 
	           
	             // Add into the html table
	             output += "<tr><td>" + r_ID + "</td>"; 
	             output += "<td>" + r_name + "</td>"; 
	             output += "<td>" + r_age + "</td>"; 
	             output += "<td>" + r_address + "</td>"; 
	             output += "<td>" + r_email + "</td>"; 
	             output += "<td>" + r_phonenum + "</td>";
	             output += "<td>" + proj_name + "</td>";
	             output += "<td>" + proj_desc + "</td>";
	             output += "<td>" + fund + "</td></tr>";
	             
	             // buttons
	            // output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>"; 
	      } 
	      con.close(); 
	      // Complete the html table
	      output += "</table>"; 
	      } 
	      catch (Exception e) { 
	           
	    	  output = "Error while reading"; 
	          System.err.println(e.getMessage()); 
	      }
	     
	      return output; 
	 }
	
	 public String updateResearcher(String r_ID, String r_name, String r_age , String r_address, String r_email, String r_phoneNum,String proj_name,String proj_desc, String fund) { 
	 
		 String output = ""; 
	     try{
	    	 
	            Connection con = connect(); 
	            if (con == null) {
	            	
	            	return "Error while connecting to the database for updating"; 
	            	
	            } 
	            

	             // create a prepared statement
	            
	             String query = "UPDATE researcher SET rName=?,rAge=?,rAddress=?,rEmail=?,rPhoneNum=?,projName=?,projDesc=?,fund=? WHERE rID=?"; 
	           	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	           	  // binding values
	           	    
	           	   preparedStmt.setString(1, r_name); 
				   preparedStmt.setInt(2, Integer.parseInt(r_age)); 
				   preparedStmt.setString(3, r_address); 
				   preparedStmt.setString(4, r_email); 
				   preparedStmt.setInt(5, Integer.parseInt(r_phoneNum)); 
				   preparedStmt.setString(6, proj_name);
				   preparedStmt.setString(7, proj_desc); 
				   preparedStmt.setDouble(8, Double.parseDouble(fund)); 
	               preparedStmt.setInt(9, Integer.parseInt(r_ID)); 
	 
	              // execute the statement
	 
	              preparedStmt.execute(); 
	 
	              con.close(); 
	 
	              output = "Updated successfully"; 
	         } 
	
	         catch (Exception e) {
	        	 
	              output = "Error while updating"; 
	              System.err.println(e.getMessage()); 
	          } 
	         
	         return output; 
	 } 
	 
	 
	 public String deleteResearcher(String r_ID) {
		 
	       String output = ""; 
	       
	       try{
	    	   
	              Connection con = connect(); 
	              if (con == null) {
	            	  
	            	  return "Error while connecting to the database for deleting"; 
	            	  
	              } 
	              
	              // create a prepared statement
	              String query = "delete from researcher where rID=?"; 
	              PreparedStatement preparedStmt = con.prepareStatement(query); 
	              
	              // binding values
	              preparedStmt.setInt(1, Integer.parseInt(r_ID)); 
	              
	              // execute the statement
	              preparedStmt.execute(); 
	              con.close(); 
	              output = "Deleted successfully"; 
	      }
	       
	      catch (Exception e) { 
	    	  
	             output = "Error while deleting"; 
	             System.err.println(e.getMessage());
	             
	      }
	       
	      return output; 
	 } 
	
	
}
