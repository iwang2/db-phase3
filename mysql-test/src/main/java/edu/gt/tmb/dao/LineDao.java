package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.gt.tmb.entity.Line;
import edu.gt.tmb.entity.User;

public class LineDao {
	public String orderLine() {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM LINE ORDER BY line ASC");
            if(rs.next())
            {
                Line user = new Line();
                user.setName( rs.getString("name") );
    
                return user.getName();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public Line getLine(String name) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM LINE WHERE name = '" + name + "'");
            if(rs.next())
            {
                Line user = new Line();
                user.setName( rs.getString("name") );
                
                return user;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
}
