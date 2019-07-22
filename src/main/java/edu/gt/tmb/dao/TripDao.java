package edu.gt.tmb.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Trip;

public class TripDao {
	public boolean addTrip(Trip trip) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO TRIP " + 
	        		"VALUES (?,?,?, ?, NULL, ?,NULL)");
	     
	        Date date = new Date();
	        
	        ps.setTimestamp(3, trip.getCardPurchaseDateTime());
	        ps.setString(2, trip.getCardType());
	        ps.setTimestamp(5, trip.getEndDateTime());
	        ps.setString(6,trip.getFromStationName());
	        ps.setTimestamp(4, new Timestamp(date.getTime()) );
	        ps.setString(7, trip.getToStationName() );
	        ps.setString(1, trip.getUserId() ); 
	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;

	}
	
//	public List<Trip> getUserTrip(String id) {
//		Connection connection = ConnectionFactory.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT (start_date_time, end_date_time, card_type, from_station_name, to_station_name) FROM TRIP WHERE user_ID  = '" + id+"'");
//            List<Trip> trips = new ArrayList<>();
//            while(rs.next())
//            {
//                Trip trip = new Trip();
//                trip.setStartDateTime( rs.getTimestamp("start_date_time") );
//                trip.setEndDateTime( rs.getTimestamp("end_date_time") ); //these are the database fields from the actual database attributes
//                trip.setCardType(rs.getString("card_type"));
//                trip.setFromStationName(rs.getString("from_station_name"));
//                trip.setToStationName( rs.getString("to_station_name") );
//
//                trips.add(trip);// trip;
//            }
//            return trips;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    return null;
//	}
	
	public List<Trip> getOrderedTripbyID(String id, String columnName) { //get station name and get startdatetime and get fromstationname
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRIP WHERE user_ID  = '"+id+"' ORDER BY "+columnName+" ASC");
            List<Trip> dates = new ArrayList<>();
            while(rs.next())
            {
                Trip trip = new Trip();
                trip.setStartDateTime( rs.getTimestamp("start_date_time") );
                trip.setEndDateTime( rs.getTimestamp("end_date_time") ); //these are the database fields from the actual database attributes
                trip.setCardType(rs.getString("card_type"));
                trip.setFromStationName(rs.getString("from_station_name"));
                trip.setToStationName( rs.getString("to_station_name") );
                trip.setUserId(rs.getString("user_id"));
                trip.setCardPurchaseDateTime(rs.getTimestamp("card_purchase_date_time"));
    
                dates.add(trip);// trip.getStartDateTime();
            }
            return dates;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
//	public String getToStationName(String id) {
//		Connection connection = ConnectionFactory.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT to_station_name FROM TRIP WHERE user_ID  = '"+id+"' ORDER BY to_station_name ASC");
//            if(rs.next())
//            {
//                Trip trip = new Trip();
//                trip.setToStationName( rs.getString("to_station_name") );
//    
//                return trip.getToStationName();
//            }
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//	}
	
	public Trip getAllFromID(String id) { //get user trip
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM trip WHERE user_id='" + id+"'");
            if(rs.next())
            {
                Trip trip = new Trip();
                trip.setCardPurchaseDateTime( rs.getTimestamp("card_purchase_date_time") );
                trip.setCardType( rs.getString("card_type") ); //these are the database fields from the actual database attributes
                trip.setEndDateTime(rs.getTimestamp("end_date_time"));
                trip.setFromStationName(rs.getString("from_station-name"));
                trip.setStartDateTime( rs.getTimestamp("start_date_time") );
                trip.setToStationName( rs.getString("to_station_name") );
                trip.setUserId( rs.getString("user_id") );
                return trip;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	
//	public String getFromStationName(String id) {
//		Connection connection = ConnectionFactory.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT from_station_name FROM TRIP WHERE user_ID  = '"+id+"' ORDER BY from_station_name ASC");
//            if(rs.next())
//            {
//                Trip trip = new Trip();
//                trip.setFromStationName( rs.getString("from_station_name") );
//    
//                return trip.getFromStationName();
//            }
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//	}
	
	public boolean updateTrip(Trip trip) {//user you are editing 
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE trip SET to_station_name = ? WHERE user_ID = ? AND card_type = ? AND card_purchase_date_time = ? AND start_date_time = ?");
	        //ps.setString(1, user.getId());
	        ps.setString(1, trip.getToStationName());
	        ps.setString(2, trip.getUserId());
	        ps.setString(3, trip.getCardType());
	        ps.setTimestamp(4, trip.getCardPurchaseDateTime());
	        ps.setTimestamp(5, trip.getStartDateTime());

	        int i = ps.executeUpdate();
	      if(i == 1) {
	    	  return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public List<Trip> getAllForUpdate(String userId, String cardType, Timestamp cpdt, Timestamp sdt) {
		//SELECT from_station_name FROM TRIP WHERE user_ID = "?" AND card_type = "?" AND card_purchase_date_time = "?" AND start_date_time = "?";
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRIP WHERE user_ID = '"+userId+"' AND card_type = '"+cardType+"' AND card_purchase_date_time = '"+cpdt+"' AND start_date_time = '"+sdt+"'");
            List<Trip> trips = new ArrayList<>();
            while(rs.next())
            {
                Trip trip = new Trip();
                trip.setStartDateTime( rs.getTimestamp("start_date_time") );
                trip.setEndDateTime( rs.getTimestamp("end_date_time") );
                trip.setUserId( rs.getString("user_id") ); //these are the database fields from the actual database attributes
                trip.setCardType(rs.getString("card_type"));
                trip.setFromStationName(rs.getString("from_station_name"));
                trip.setToStationName(rs.getString("to_station_name"));
                trip.setCardPurchaseDateTime(rs.getTimestamp("card_puchase_dateTime"));
                
               //trip.setStartDateTime( rs.getString("to_station_name") );

                trips.add(trip);// trip;
            }
            return trips;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	

}
