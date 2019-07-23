package edu.gt.tmb.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Card;
import edu.gt.tmb.entity.User;

public class CardDao {
	public boolean addCard(Card tmes) {
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO CARD VALUES (?,?,?,?,?)");
	     
	     
	        ps.setTimestamp(1, tmes.getExpirationDate());
	        ps.setTimestamp(2, tmes.getPurchaseDateTime());
	        ps.setString(4, tmes.getType());
	        ps.setString(3,tmes.getUserId());
	        ps.setInt(5, tmes.getUsesLeft() );
	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public List<Card> getValidCard(String uid, Timestamp pdt, Timestamp ed) {
		Connection connection = ConnectionFactory.getConnection();
        try { 
        	Date date= new Date();
        	Timestamp ts = new Timestamp(date.getTime()); //may have to define in gui
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT type, expiration_date FROM CARD "
            		+ "WHERE user_ID = '"+uid+"' AND expiration_date = '"+pdt+"' AND uses_left > 0 AND expiration_date <> '"+ts+"'");
            List<Card> cards = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                Card card = new Card();
                card.setType( rs.getString("type") );
                card.setPurchaseDateTime( rs.getTimestamp("purchase_date_time") ); //these are the database fields from the actual database attributes
                
                //return user;
                cards.add(card);
            }
            return cards;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	//UPDATE CARD SET uses_left = "uses_left - 1" WHERE user_ID = "?" AND purchase_date_time = "?";

	public boolean updateCard(Card card) {//user you are editing
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE CARD SET uses_left = ? WHERE user_ID = ? AND purchase_date_time = ?");
	        //ps.setString(1, user.getId());
	        ps.setInt(1, card.getUsesLeft());
	        ps.setString(2, card.getUserId());
	        ps.setTimestamp(3, card.getPurchaseDateTime());
	        
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
