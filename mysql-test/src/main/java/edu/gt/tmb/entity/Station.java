package edu.gt.tmb.entity;

public class Station {
//	name varchar(255),    
//	status varchar(255),    
//	state_province varchar(255),    
//	address varchar(255),    
//	zipcode int,    
//	city varchar(255),    
//	PRIMARY KEY(name),    
//	UNIQUE(state_province, address, zipcode, city)
	private String name;
	private String status;
	private String stateProvince;
	private String address;
	private String city;
	private int zipcode;

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return "{Station: " + name + ", "+ status +", "+ stateProvince +", "+ address +", "+ zipcode +", "+ city + "}";
	}
}
