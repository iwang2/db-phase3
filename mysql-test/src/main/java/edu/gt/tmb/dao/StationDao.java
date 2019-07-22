package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Station;
//import edu.gt.tmb.entity.User;
import edu.gt.tmb.entity.User;

public class StationDao {
	public List<Station> orderStation() {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STATION ORDER BY name ASC;");
            List<Station> stations = new ArrayList<>();
            while(rs.next())
            {
                Station station = new Station();
                station.setName( rs.getString("name") );
                station.setStatus( rs.getString("status") );
                station.setStateProvince(rs.getString("state_province"));
                station.setAddress( rs.getString("address") ); //these are the database fields from the actual database attributes
                station.setZipcode( rs.getInt("zipcode") );
                station.setCity(rs.getString("city"));
    
                stations.add(station);// user.getName();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public Station getStation(String name) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM station WHERE name='" + name+"'");
	            if(rs.next())
	            {
	            	Station station = new Station();
	                station.setName( rs.getString("name") );
	                station.setStatus( rs.getString("status") );
	                station.setStateProvince(rs.getString("state_province"));
	                station.setAddress( rs.getString("address") ); //these are the database fields from the actual database attributes
	                station.setZipcode( rs.getInt("zipcode") );
	                station.setCity(rs.getString("city"));
	                return station;
	            }
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}
	
	public List<Station> getAllStations() {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM station");
            List<Station> stations = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                Station station = new Station();
                station.setName( rs.getString("name") );
                station.setStatus( rs.getString("status") );
                station.setStateProvince(rs.getString("state_province"));
                station.setAddress( rs.getString("address") ); //these are the database fields from the actual database attributes
                station.setZipcode( rs.getInt("zipcode") );
                station.setCity(rs.getString("city"));
                
                
                
                
                //return user;
                stations.add(station);
            }
            return stations;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public boolean addStation(Station station) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO station(name, address,state_province, zipcode, city) VALUES (?,?,?,?,?)");
	     
	     
	        ps.setString(1, station.getName());
	        //ps.setString(2, station.getStatus());
	        ps.setString(2, station.getAddress());
	        ps.setString(3, station.getStateProvince());
	        ps.setInt(4,station.getZipcode());
	        ps.setString(5, station.getCity() );

	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;

	}
	
	public boolean updateStation(Station station) {//user you are editing 
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE STATION  SET status = ? WHERE name = ?");
	        //ps.setString(1, user.getId());
	        ps.setString(1, station.getStatus());
	        ps.setString(2, station.getName());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	    	  return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
}
