package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import database.dbconnect;

public class Doctor {
	
	dbconnect obj=new dbconnect();
	
	public String insertDoctor(String docName, String docAge, String docGender, String docSpecialization,String hosID) {
				
				String output = "";
				try {
					Connection con = obj.connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					// create a prepared statement
					
					 String query = " insert into doctor(`docName`,`docAge`,`docGender`,`docSpecialization`,`hosID`)" +
				                " values (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					preparedStmt.setString(1, docName);
					preparedStmt.setString(2, docAge);
					preparedStmt.setString(3, docGender);
					preparedStmt.setString(4, docSpecialization);
					preparedStmt.setString(5, hosID);
		
					preparedStmt.execute();
					con.close();
					
					 String newDoctor = readDoctor(); 
			            output = "{\"status\":\"success\",\"data\": \"" +newDoctor + "\"}";
					
					//output = "Inserted successfully";
					System.out.println("Inserted successfully.......................................");
				} catch (Exception e) {
					
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Doctors.\"}";
		                System.err.println(e.getMessage());
		                
					//output = "Error while inserting the Doctors.";
					//System.out.println("Error while inserting the Doctors........."+ e);
					//System.err.println(e.getMessage());
				}
				return output;
			}
	 //
			public String readDoctor() {
				String output = "";
				try {
					Connection con = obj.connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
					// Prepare the html table to be displayed
					//output = "<table border='1'>"
							//+ "<th>Doctor Name</th>"
							//+ "<th>Doctor Age</th>"
							//+ "<th>Doctor Gender</th>"
							//+ "<th>Doctor Specialization</th>"
							//+ "<th>Hospital ID</th>"
							//+ "<th>Update</th>"
							//+ "<th>Remove</th></tr>";
					
					output ="<table border='1'><tr><th>Doctor Name</th><th>Doctor Age</th><th>"
							+ "Doctor Gender</th><th>Doctor Specialization</th><th>Hospital ID</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from doctor";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// iterate through the rows in the result set
					while (rs.next()) {
						String docID = Integer.toString(rs.getInt("docID"));
						String docName = rs.getString("docName");
						String docAge = rs.getString("docAge");
						String docGender = rs.getString("docGender");
						String docSpecialization = rs.getString("docSpecialization");
						String hosID = rs.getString("hosID");
						
						
						
						
						// Add into the html table
						/*output += "<tr><td><input id=\"didDoctorIDUpdate\"name=\"didDoctorIDUpdate\"type=\"hidden\" value=\"" + docID + "\">"
	                            + docName + "</td>";
						output += "<td>" + docAge + "</td>";
						output += "<td>" + docGender + "</td>";
						output += "<td>" + docSpecialization + "</td>";
						output += "<td>" + hosID + "</td>";*/
						
						output += "<tr><td><input id='didDoctorIDUpdate'name='didDoctorIDUpdate' type='hidden'value='" + docID + "'>" + 
						docName  + "</td>";
						output += "<td>" + docAge + "</td>";
						output += "<td>" + docGender + "</td>";
						output += "<td>" + docSpecialization + "</td>";
						output += "<td>" + hosID + "</td>";
						
					
						
						// buttons
						/*output += "<td><input name=\"btnUpdate\" type=\"submit\"value=\"Update\" class=\"btn btn-warning btnUpdate\"></td>"
								+ "<td><form method=\"post\" action=\"Doctor_Insert.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"didDoctorIDDelete\" type=\"hidden\" value=\"" + docID + "\">" + "</form></td></tr>";*/
						
						output += "<td><input name='btnUpdate' type='button'"
						+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"
						+"<td><input name='btnRemove' type='button'"
						+"value='Remove'"+"class='btnRemove btn btn-danger' data-doctorid='"+ docID + "'>" + "</td></tr>";
						//1233
					}
					con.close();
					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the Doctors.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			public String updateDoctor(String docID, String docName, String docAge , String docGender , String docSpecialization,String hosID) {
				System.out.println("Update method...............................................................................");
				String output = "";
				try {
					Connection con = obj.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					//update
					// create a prepared statement
					String query = "UPDATE doctor SET docName=?,docAge=?,docGender=?,docSpecialization=?,hosID=? WHERE docID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, docName);
					preparedStmt.setString(2, docAge);
					preparedStmt.setString(3, docGender);
					preparedStmt.setString(4, docSpecialization);
					preparedStmt.setString(5, hosID);
					preparedStmt.setInt(6, Integer.parseInt(docID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					 String newDoctor = readDoctor(); 
			            
			            output = "{\"status\":\"success\", \"data\": \"" +newDoctor + "\"}";
					//output = "Updated successfully";
				} catch (Exception e) {
					 output = "{\"status\":\"error\", \"data\":\"Error while updating the Doctors.\"}";
					//output = "Error while updating the Doctor.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			public String deleteDoctor(String docID) {
				String output = "";
				try {
					Connection con = obj.connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
					// create a prepared statement
					String query = "delete from doctor where docID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(docID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					 String newDoctor = readDoctor();
			            output = "{\"status\":\"success\", \"data\": \"" +newDoctor + "\"}";
					//output = " Doctor Deleted successfully";
				} catch (Exception e) {
					
					output = "{\"status\":\"error\", \"data\":\"Error while deleting the Doctor.\"}"; 
					//output = "Error while deleting the Doctor.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
}
