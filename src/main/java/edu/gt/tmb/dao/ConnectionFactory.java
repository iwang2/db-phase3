package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionFactory {
	  public static final String URL = "jdbc:mysql://localhost:3306/TMB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    public static final String USER = "root";
	    public static final String PASS = "207354838";
	    /**
	     * Get a connection to database
	     * @return Connection object
	     */
	    public static Connection getConnection()
	    {
	      try {
	          DriverManager.registerDriver(new Driver());
	          return DriverManager.getConnection(URL, USER, PASS);
	      } catch (SQLException ex) {
	          throw new RuntimeException("Error connecting to the database", ex);
	      }
	    }
}
