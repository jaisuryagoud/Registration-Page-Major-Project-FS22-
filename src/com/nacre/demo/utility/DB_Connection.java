package com.nacre.demo.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_Connection {
	static Properties p = new Properties();
	static { //creating object of class and calling propertyReader() method to get the properties into getConnection() method
		new DB_Connection().propertyReader();
	}
	public void propertyReader() {
		//give the properties file path
		String filePath = "com/nacre/demo/utility/db.properties";
		/*get class name using this.getClass(),classLoader loads the class after getting class
		  and then get the resources(properties from properties file(db.properties))using
		  getResourceStream(filepath) which returns the inputstream to read*/
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
		try {
			p.load(is);//loading the properties into properties object
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static Connection con = null;
	public static Connection getConnection() {
		//getting the properties from properties objects and storing into variables
		String driver = p.getProperty("driverClassName");
		String url = p.getProperty("url");
		String userName = p.getProperty("userName");
		String password = p.getProperty("password");
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
/*Advantage:
  =========
  TO get the loosecoupling functionality and to avoid hardcoding the properties into dao,
  so that with out disturb the dao class we can change to required database.
  and every time no need to recompile,redeploy & restart.
*/
