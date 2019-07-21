package edu.gt.tmb.entity;

import java.sql.Timestamp;

public class AdminAddLine {
//	line_name varchar(255) PRIMARY KEY,    
//	admin_ID varchar(255),    
//	date_time Datetime,  
	private String lineName;
	private String adminId;
	private Timestamp dateTime;

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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
		return "AdminAddLine [lineName=" + lineName + ", adminId=" + adminId + ", dateTime=" + dateTime + "]";
	}

}
