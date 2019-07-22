package edu.gt.tmb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gt.tmb.entity.Line;

public class LineDao {
	public List<Line> orderLine() {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LINE ORDER BY name ASC");
            List<Line> lines = new ArrayList<>();
            while(rs.next())
            {
                Line user = new Line();
                user.setName( rs.getString("name") );
    
                lines.add(user); //user.getName();
            }
            return lines;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	public List<Line> getLine(String name) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM LINE WHERE name = '" + name + "'");
            List<Line> lists = new ArrayList<>();
            while(rs.next())
            {
                Line user = new Line();
                user.setName( rs.getString("name") );
                
                lists.add(user);
            }
            return lists;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null;
	}
	
}
