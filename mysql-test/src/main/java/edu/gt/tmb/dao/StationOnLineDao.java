package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Station;
import edu.gt.tmb.entity.StationOnLine;
import edu.gt.tmb.entity.User;

public class StationOnLineDao {
	public List<StationOnLine> getAllStationOnLine() {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM station_on_line");
            List<StationOnLine> stols = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                StationOnLine stol = new StationOnLine();
                stol.setStationName(rs.getString("station_name"));
                stol.setLineName( rs.getString("line_name") );
                stol.setOrderNumber( rs.getInt("order_number") ); //these are the database fields from the actual database attributes
                //stol.setStationName(rs.getString("station_name"));
                
                //return user;
                stols.add(stol);
            }
            return stols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public List<StationOnLine> getStationLineName(String stationName) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT line_name, order_number,station_name FROM STATION_ON_LINE WHERE station_name = '"+stationName+"'");
            List<StationOnLine> stols = new ArrayList<>();
            while(rs.next())
            {	
            	
                StationOnLine stol = new StationOnLine();
                stol.setLineName( rs.getString("line_name") );
                stol.setOrderNumber( rs.getInt("order_number") );
                stol.setStationName( rs.getString("station_name") );
                //user.setFirstName( rs.getString("first_name") ); //these are the database fields from the actual database attributes
                //user.setMinit(rs.getString("minit"));
                //user.setLastName(rs.getString("last_name"));
                //user.setLastName( rs.getString("last_name") );
                //user.setPassengerEmail( rs.getString("passenger_email") );
                stols.add(stol);
            }
            return stols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public List<StationOnLine> getLineInfo(String lineName) {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT station_name, order_number, line_name FROM STATION_ON_LINE WHERE lineName = '"+lineName+"'");
            List<StationOnLine> stols = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                StationOnLine stol = new StationOnLine();
                stol.setStationName(rs.getString("station_name"));
                stol.setLineName( rs.getString("line_name") );
                stol.setOrderNumber( rs.getInt("order_number") ); //these are the database fields from the actual database attributes
                //stol.setStationName(rs.getString("station_name"));
                
                //return user;
                stols.add(stol);
            }
            return stols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
	public List<StationOnLine> orderLineStation(String columnName) {
		Connection connection = ConnectionFactory.getConnection();
		
        try {
        	List<StationOnLine> sols = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STATION_ON_LINE ORDER BY "+columnName+" ASC");
            while(rs.next())
            {
                StationOnLine user = new StationOnLine();
                user.setStationName(rs.getString("station_name"));
                user.setLineName( rs.getString("line_name") );
                user.setOrderNumber( rs.getInt("order_number") );
    
                sols.add(user);
            }
            return sols;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	
	public int numberStops(String lineName) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(order_number) FROM STATION_ON_LINE WHERE line_name = '"+lineName+"'");
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
	
	public boolean addStationOnLine(StationOnLine sol) {
	//INSERT INTO STATION_ON_LINE (line_name, order_number) VALUES ("from gui","from gui");
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO STATION_ON_LINE (line_name, order_number) VALUES (?,?)");
	     
	     
	        ps.setString(1, sol.getLineName());
	        ps.setInt(2, sol.getOrderNumber());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public boolean orderCheckStation(int orderNumber, String stationName) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STATION_ON_LINE WHERE ='"+orderNumber+ " and station_name = '"+stationName+"'");
            if(rs.next())
            {
            	
//                StationOnLine stol = new StationOnLine();
//                stol.setLineName( rs.getString("line_name") );
//                stol.setOrderNumber( rs.getInt("order_number") );
//                stol.setStationName( rs.getString("station_name") );
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; //if false then you can add the the station on that line
	}
	
	public boolean checkStationOnLine(String stationName) {
		//SELECT line_name FROM STATION_ON_LINE WHERE station_name = "?";
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT line_name FROM STATION_ON_LINE WHERE station_name = '"+stationName+"'");
            if(rs.next())
            {
//                StationOnLine stol = new StationOnLine();
//                stol.setLineName( rs.getString("line_name") );
                //stol.setOrderNumber( rs.getInt("order_number") );
                //stol.setStationName( rs.getString("station_name") );
                return true;//if true then no error because the station is on at least one line
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; 
	}
	
	public List<StationOnLine> lineAddInfo(String lineName) {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT station_name, order_number FROM Station_On_Line WHERE line_name = '"+lineName+"'");
            List<StationOnLine> stols = new ArrayList<>();
            while(rs.next()) //to check if something results from the query
            {
                StationOnLine stol = new StationOnLine();
                stol.setStationName(rs.getString("station_name"));
                //stol.setLineName( rs.getString("line_name") );
                stol.setOrderNumber( rs.getInt("order_number") ); //these are the database fields from the actual database attributes
                //stol.setStationName(rs.getString("station_name"));
                
                //return user;
                stols.add(stol);
            }
            return stols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
		
	}
	
	public boolean addLinetoStation(StationOnLine sol) {
		//SELECT station_name, order_number FROM Station_On_Line WHERE line_name = "from gui enter";
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO STATION_ON_LINE (station_name, order_number) VALUES (?,?)");
	     
	     
	        ps.setString(1, sol.getStationName());
	        ps.setInt(2, sol.getOrderNumber());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public List<StationOnLine> orderStationsOnLine(String lineName, String columnName) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STATION_ON_LINE WHERE line_name = '"+lineName+"' ORDER BY "+columnName+" ASC");
            List<StationOnLine> sols = new ArrayList<>();
            while(rs.next())
            {
                StationOnLine user = new StationOnLine();
                user.setStationName( rs.getString("station_name") );
                user.setLineName(rs.getString("line_name"));
                user.setOrderNumber((rs.getInt("order_number")));
                sols.add(user);// user.getStationName();
            }
            return sols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	
	
	public boolean deleteStationOnLine(String lineName, String stationName) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM STATION_ON_LINE WHERE line_name = '" +lineName+"' AND station_name = '"+stationName+"'");
	      if(i == 1) {
	    return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	//UPDATE STATION_ON_LINE SET order_number = "?" WHERE line_name = "?" AND station_name = "?";
	public boolean updateStationOnLine(StationOnLine sol) {//user you are editing 
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	    	//UPDATE USER SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?" WHERE ID = "?"; 
	        PreparedStatement ps = connection.prepareStatement("UPDATE STATION_ON_LINE SET order_number = ? WHERE line_name =? AND station_name = ?");
	        //ps.setString(1, user.getId());
	        ps.setInt(1, sol.getOrderNumber());
	        ps.setString(2, sol.getLineName());
	        ps.setString(3, sol.getStationName());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	    	  return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public StationOnLine getStationOnLinebyOrder(int ordernum) {
		Connection connection = ConnectionFactory.getConnection();
        try { 
            Statement stmt = connection.createStatement();
      
            ResultSet rs = stmt.executeQuery("SELECT * FROM STATION_ON_LINE WHERE order_number = '"+ordernum+"'");
           
            if(rs.next()) //to check if something results from the query
            {
                StationOnLine stol = new StationOnLine();
                stol.setStationName(rs.getString("station_name"));
                stol.setLineName( rs.getString("line_name") );
                stol.setOrderNumber( rs.getInt("order_number") ); //these are the database fields from the actual database attributes
                //stol.setStationName(rs.getString("station_name"));
                
                return stol;
            }
            //return stols;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
}


