package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.gt.tmb.entity.AdminAddLine;

public class AdminAddLineDao {
	public boolean addAdminAddLine(AdminAddLine adminAddLine) {
		//Connector connector = new Connector();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO ADMIN_ADD_LINE VALUES (?,?,?)");
	     
	     
	        ps.setString(1, adminAddLine.getLineName());
	        ps.setString(2, adminAddLine.getAdminId());
	        ps.setTimestamp(3, adminAddLine.getDateTime());

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
