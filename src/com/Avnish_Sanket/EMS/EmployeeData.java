package com.Avnish_Sanket.EMS;

import java.sql.Connection;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class EmployeeData {
	public static Connection connectDB() {
	 try {
		 Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\avnis\\eclipse-workspace\\Employee Management System\\Employee.db");
			JOptionPane.showMessageDialog(null, "Connection Made");
			return connection;
	 }
	 catch(Exception e) {
		 JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		
	 }
	
		
	}

}
