package com.excilys.formation.cdb.model;

public class Company {
	
	private long id;
	private String name;

	
	public Company() {
		this.id = 0;
		this.name = null;
	}

	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("COMPANY\n");
		sb.append("  id: ");
		sb.append(this.getId());
		sb.append("\n  name: ");
		sb.append(this.getName());
		sb.append("\n");
		return sb.toString();
	}
}
