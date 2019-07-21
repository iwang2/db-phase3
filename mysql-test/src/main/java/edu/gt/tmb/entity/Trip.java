package edu.gt.tmb.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Trip {
	/*
	 * user_ID varchar(255), card_type varchar(255), card_purchase_date_time
	 * Datetime, start_date_time Datetime, end_date_time Datetime, from_station_name
	 * varchar(255) NOT NULL, to_station_name varchar(255), PRIMARY KEY(user_ID,
	 * card_type, card_purchase_date_time, start_date_time), FOREIGN KEY
	 * (from_station_name) REFERENCES Station(name) ON DELETE CASCADE ON UPDATE
	 * CASCADE, FOREIGN KEY (to_station_name) REFERENCES Station(name) ON DELETE SET
	 * NULL ON UPDATE CASCADE, FOREIGN KEY (user_ID, card_type,
	 * card_purchase_date_time) REFERENCES Card(user_ID, type, purchase_date_time)
	 * ON DELETE CASCADE ON UPDATE CASCADE
	 */

	private String userId;
	private String cardType;
	private Timestamp cardPurchaseDateTime;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private String fromStationName;
	private String toStationName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Timestamp getCardPurchaseDateTime() {
		return cardPurchaseDateTime;
	}

	public void setCardPurchaseDateTime(Timestamp cardPurchaseDateTime) {
		this.cardPurchaseDateTime = cardPurchaseDateTime;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getFromStationName() {
		return fromStationName;
	}

	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	public String getToStationName() {
		return toStationName;
	}

	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	@Override
	public String toString() {
		return "Trip {userId=" + userId + ", cardType=" + cardType + ", cardPurchaseDateTime=" + cardPurchaseDateTime
				+ ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", fromStationName="
				+ fromStationName + ", toStationName=" + toStationName + "}";
	}
	
	

}
