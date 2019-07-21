package edu.gt.tmb.entity;

public class User {
//	ID varchar(255),    
//	first_name varchar(255),    
//	minit char(1),    
//	last_name varchar(255),    
//	password varchar(225) NOT NULL,    
//	passenger_email varchar(255),    
//	PRIMARY KEY (ID)

	private String id;
	private String firstName;
	private String minit;
	private String lastName;
	private String password;
	private String passengerEmail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMinit() {
		return minit;
	}

	public void setMinit(String minit) {
		this.minit = minit;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}
	
	public String toString() {
		return "{User: " + id + ", "+ firstName +", "+ minit +", "+ lastName +", "+ passengerEmail +", "+ password + "}";
	}

}
