package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.gt.tmb.entity.AdminAddStation;

public class AdminAddStationDao {
	public boolean addAdminAddStation(AdminAddStation adminAddStation) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO ADMIN_ADD_STATION VALUES (?,?,?)");
	     
	     
	        ps.setString(1, adminAddStation.getStationName());
	        ps.setString(2, adminAddStation.getAdminId());
	        ps.setTimestamp(3, adminAddStation.getDateTime());

	        int i = ps.executeUpdate();
	      if(i == 1) { //how many rows were updated
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;

	}
}
