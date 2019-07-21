package edu.gt.tmb.entity;

import java.sql.Timestamp;

public class Card {

	/*
	 * user_ID varchar(255), type varchar(255), purchase_date_time Datetime,
	 * uses_left int, expiration_date Date, PRIMARY KEY (user_ID, type,
	 * purchase_date_time), FOREIGN KEY(user_ID) REFERENCES User(ID) ON DELETE
	 * CASCADE ON UPDATE CASCADE
	 */
	private String userId;
	private String type;
	private Timestamp purchaseDateTime;
	private int usesLeft;
	private Timestamp expirationDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(Timestamp purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}

	public int getUsesLeft() {
		return usesLeft;
	}

	public void setUsesLeft(int usesLeft) {
		this.usesLeft = usesLeft;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "Card {userId=" + userId + ", type=" + type + ", purchaseDateTime=" + purchaseDateTime + ", usesLeft="
				+ usesLeft + ", expirationDate=" + expirationDate + "}";
	}

}
