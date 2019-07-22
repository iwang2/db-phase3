package edu.gt.tmb.dao;

import java.sql.Connection;
import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Review;

public class ReviewDao {
	public List<Review> getReviews(String passengerId) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE passenger_ID='" + passengerId+"'");
	            List<Review> reviews = new ArrayList<>();
	            while(rs.next())
	            {
	            	Review review = new Review();
	                review.setPassengerId( rs.getString("passenger_id") );
	                review.setApprovalStatus( rs.getString("approval_status") );
	                review.setApproverId(rs.getString("approver_id"));
	                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
	                review.setConnectionSpeed( rs.getInt("connection_speed") );
	                review.setShopping( rs.getInt("shopping") );
	                review.setEditTimestamp(rs.getDate("edit_timestamp"));
	                review.setRid(rs.getInt("rid"));
	                review.setStationName(rs.getString("station_name"));
	                reviews.add(review);
	            }
	            return reviews;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}
	
	public List<Review> getAllReviews() {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM review");
            List<Review> reviews = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                Review review = new Review();
                review.setPassengerId( rs.getString("passenger_ID") );
                review.setApprovalStatus( rs.getString("approval_status") );
                review.setApproverId(rs.getString("approver_id"));
                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
                review.setConnectionSpeed( rs.getInt("connection_speed") );
                review.setEditTimestamp(rs.getDate("edit_timestamp"));
                review.setRid(rs.getInt("rid"));
                review.setShopping( rs.getInt("shopping") );
                review.setStationName(rs.getString("station_name"));
                
                
                
                
                //return user;
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public List<Review> orderReview(String columnName, String passengerID) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Review WHERE passenger_id = '"+passengerID+"'ORDER BY "+columnName+" ASC");
            List<Review> reviews = new ArrayList<>();
            while(rs.next())
            {
                Review review = new Review();
                review.setPassengerId( rs.getString("passenger_id") );
                review.setApprovalStatus( rs.getString("approval_status") );
                review.setApproverId(rs.getString("approver_id"));
                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
                review.setConnectionSpeed( rs.getInt("connection_speed") );
                review.setShopping( rs.getInt("shopping") );
                review.setEditTimestamp(rs.getDate("edit_timestamp"));
                review.setRid(rs.getInt("rid"));
                review.setStationName(rs.getString("station_name"));
   
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public List<Review> getReviewName(int rid, String passId) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE passenger_id = '"+passId+"' AND rid = '" + rid+"'");
	            List<Review> reviews = new ArrayList<>();
	            while(rs.next())
	            {
	            	Review review = new Review();
	                
	                review.setStationName(rs.getString("station_name"));
	                review.setPassengerId( rs.getString("passenger_id") );
	                review.setApprovalStatus( rs.getString("approval_status") );
	                review.setApproverId(rs.getString("approver_id"));
	                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
	                review.setConnectionSpeed( rs.getInt("connection_speed") );
	                review.setShopping( rs.getInt("shopping") );
	                review.setEditTimestamp(rs.getDate("edit_timestamp"));
	                review.setRid(rs.getInt("rid"));
	                //review.setStationName(rs.getString("station_name"));
	                reviews.add(review);// review;
	            }
	            return reviews;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}
	
	
	public boolean deleteReview(int rid) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM review WHERE rid='" + rid+"'");
	      if(i == 1) {
	    return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public List<Review> getApprovedReview(String stationName, String approval) {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM REVIEW WHERE station_name = '"+stationName+"' AND approval_status = '" + approval + "'" );
            List<Review> reviews = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                Review review = new Review();
                review.setPassengerId( rs.getString("passenger_id") );
                review.setApprovalStatus( rs.getString("approval_status") );
                review.setApproverId(rs.getString("approver_id"));
                review.setShopping( rs.getInt("shopping") );
                review.setConnectionSpeed( rs.getInt("connection_speed") );
                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
                
                review.setEditTimestamp(rs.getDate("edit_timestamp"));
                review.setRid(rs.getInt("rid"));
                
                review.setStationName(rs.getString("station_name"));
                
                
                
                
                //return user;
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public int reviewAverage(String approval, String stationName, String columnName) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT AVG("+columnName+") FROM REVIEW WHERE approval_status = '"+approval + "' AND station_name = '" + stationName+"'");
	            if(rs.next())
	            {
//	            	
	            	return rs.getInt(1);
	            }
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return 0;
	}
	
	public boolean addReview(Review review) {
		//Connector connector = new Connector();
		//int rand = 0;
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO REVIEW (passenger_id, shopping, connection_speed, comment, station_name, rid) VALUES (?,?,?,?,?,?)");
	        Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(rid) FROM review WHERE passenger_ID='" + review.getPassengerId()+"'");
//            if(rs.next()) {
//            	rand = rs.getInt(1); //how to get review id because if you count number of reviews, when you delete one you get an error for duplicate primary key
//            }
	        Random rand = new Random();
	        //java.Math.Random(1000000000);
	        ps.setString(1, review.getPassengerId());
	        //ps.setString(2, review.getStatus());
	        ps.setInt(2, review.getShopping());
	        ps.setInt(3, review.getConnectionSpeed());
	        ps.setString(4,review.getComment());
	        ps.setString(5,review.getStationName());
	        // ps.setInt()
	        ps.setInt(6, rand.nextInt(1000000) );

	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;

	}
	public boolean updateReview(Review review) {//user you are editing 
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE REVIEW SET shopping = ?, connection_speed=?, comment = ? WHERE rid = ? AND passenger_id = ?");
	        //ps.setString(1, user.getId());
	        ps.setInt(1, review.getShopping());
	        ps.setInt(2, review.getConnectionSpeed());
	        ps.setString(3, review.getComment());
	        ps.setInt(4, review.getRid());
	        ps.setString(5, review.getPassengerId());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	    	  return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public boolean updateReviewStatus(Review review) {
		Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE REVIEW SET approval_status = ? WHERE passenger_id = ? AND rid = ?");
	        //ps.setString(1, review.getApprovalStatus());
	        ps.setString(1, review.getApprovalStatus());
	        ps.setString(2, review.getPassengerId());
	        ps.setInt(3, review.getRid());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	    	  return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	//CREATE VIEW pending_reviews AS SELECT passenger_id, station_name, shopping, connection_speed, comment FROM REVIEW WHERE approval_status = "pending";
	public List<Review> getPendingReview(String status) {
	    Connection connection = ConnectionFactory.getConnection();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT passenger_id, station_name, shopping, connection_speed, comment FROM REVIEW WHERE approval_status = '" + status +"'");
	            List<Review> reviews = new ArrayList<>();
	            while(rs.next())
	            {
	            	Review review = new Review();
	                review.setPassengerId( rs.getString("passenger_id") );
	                review.setApprovalStatus( rs.getString("approval_status") );

	                review.setComment( rs.getString("comment") ); //these are the database fields from the actual database attributes
	                review.setConnectionSpeed( rs.getInt("connection_speed") );
	                review.setShopping( rs.getInt("shopping") );

	                review.setStationName(rs.getString("station_name"));
	                reviews.add(review);
	            }
	            return reviews;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}
	
}
