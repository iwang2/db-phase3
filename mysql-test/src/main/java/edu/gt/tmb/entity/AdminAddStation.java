package edu.gt.tmb.entity;

import java.sql.Timestamp;

public class AdminAddStation {
//	station_name varchar(255) PRIMARY KEY,    
//	admin_ID varchar(255),    
//	date_time Datetime,  
	private String stationName;
	private String adminId;
	private Timestamp dateTime;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "AdminAddStation {stationName=" + stationName + ", adminId=" + adminId + ", dateTime=" + dateTime + "}";
	}

}
