package edu.gt.tmb.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.gt.tmb.dao.ConnectionFactory;

public class StationOnLine {
//	station_name varchar(255),    
//	line_name varchar(255),    
//	order_number int, 
	private String stationName;
	private String lineName;
	private int orderNumber;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String toString() {
		
		return "{StationOnLine: " + stationName + ", " + lineName + ", " + orderNumber+"}";
	}

}
