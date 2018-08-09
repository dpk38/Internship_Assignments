package io.ztech.collections;

public class Sailor {
	private String name;
	private int id;
	
	Sailor(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
}
