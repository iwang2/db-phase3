package edu.gt.tmb.entity;

import java.util.Date;

public class Review {
	/*
	 * passenger_ID varchar(255), rid int, shopping int, connection_speed int,
	 * comment text, approver_ID varchar(255), approval_status varchar(255),
	 * edit_timestamp Datetime, station_name varchar(255) NOT NULL,
	 */
	private String passengerId;
	private int rid;
	private int shopping;
	private int connectionSpeed;
	private String comment;
	private String approverId;
	private String approvalStatus;
	private Date editTimestamp;
	private String stationName;

	public int getShopping() {
		return shopping;
	}

	public void setShopping(int shopping) {
		this.shopping = shopping;
	}


	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getConnectionSpeed() {
		return connectionSpeed;
	}

	public void setConnectionSpeed(int connectionSpeed) {
		this.connectionSpeed = connectionSpeed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getEditTimestamp() {
		return editTimestamp;
	}

	public void setEditTimestamp(Date editTimestamp) {
		this.editTimestamp = editTimestamp;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String toString() {
		return "{Review: " + passengerId + ", " + rid + ", "+shopping + ", " + connectionSpeed + ", " + comment + ", " + approverId
				+ ", " + approvalStatus + ", " + editTimestamp + ", " + stationName + "}";
	}

}
