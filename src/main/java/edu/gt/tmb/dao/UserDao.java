package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.User;

public class UserDao {
	public User getUser(String id) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id='" + id+"'");
	            if(rs.next())
	            {
	                User user = new User();
	                user.setId( rs.getString("id") );
	                user.setFirstName( rs.getString("first_name") ); //these are the database fields from the actual database attributes
	                user.setMinit(rs.getString("minit"));
	                user.setLastName(rs.getString("last_name"));
	                user.setPassword( rs.getString("password") );
	                user.setPassengerEmail( rs.getString("passenger_email") );
	                return user;
	            }
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}
	
	public List<User> getAllUsers() {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            List<User> users = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                User user = new User();
                user.setId( rs.getString("id") );
                user.setFirstName( rs.getString("first_name") ); //these are the database fields from the actual database attributes
                user.setMinit(rs.getString("minit"));
                user.setLastName(rs.getString("last_name"));
                user.setPassword( rs.getString("password") );
                user.setPassengerEmail( rs.getString("passenger_email") );
                //return user;
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public boolean addUser(User user) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO user(id, first_name, minit, last_name, passenger_email, password) VALUES (?,?,?,?,?,?)");
	     
	     
	        ps.setString(1, user.getId());
	        ps.setString(2, user.getFirstName());
	        ps.setString(4, user.getLastName());
	        ps.setString(3,user.getMinit());
	        ps.setString(5, user.getPassengerEmail());
	        ps.setString(6, user.getPassword());
	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;

	}
	
	public boolean checkPassUser(String password, String use) {
		Connection connection = ConnectionFactory.getConnection();
        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT ID, password FROM USER WHERE USER.ID = '" + use + "' AND USER.password = '" + password +"'");
//            if(rs.next())
//            {
//                User user = new User();
//                user.setId( rs.getString("id") );                
//                user.setPassword( rs.getString("password") );
//                return true;
//            }
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id=? AND password=?");
            ps.setString(1, use);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	
	public String getUserName(String fname, String lname) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT first_name, last_name FROM USER WHERE first_name = '" + fname + "' AND last_name = '" + lname +"'");
            if(rs.next())
            {
                User user = new User();
                user.setFirstName( rs.getString("first_name") );
                //user.setFirstName( rs.getString("first_name") ); //these are the database fields from the actual database attributes
                //user.setMinit(rs.getString("minit"));
                //user.setLastName(rs.getString("last_name"));
                user.setLastName( rs.getString("last_name") );
                //user.setPassengerEmail( rs.getString("passenger_email") );
                return user.getFirstName() + " " + user.getLastName();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public boolean deleteUser(String id) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM user WHERE id='" + id+"'");
	      if(i == 1) {
	    return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public boolean updateUser(User user) {//user you are editing 
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE user SET first_name = ?, minit=?, last_name = ?, password=?, passenger_email = ? WHERE id=?");
	        //ps.setString(1, user.getId());
	        ps.setString(1, user.getFirstName());
	        ps.setString(2, user.getMinit());
	        ps.setString(3, user.getLastName());
	        ps.setString(4, user.getPassword());
	        ps.setString(5, user.getPassengerEmail());
	        ps.setString(6, user.getId());
	        int i = ps.executeUpdate();
	        if(i == 1) {
	            return true;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public boolean isUserAdmin(User user) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE id='" + user.getId()+"'");
            if(rs.next())
            {
                
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
		return false;
	}

}
