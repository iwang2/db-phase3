package edu.gt.tmb.entity;

public class Line {
//name varchar(255) PRIMARY KEY
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//@Override
	public String print() {
		return "Line [name=" + name + "]";
	}
	@Override
	public String toString() {
		return name;
	}

}
