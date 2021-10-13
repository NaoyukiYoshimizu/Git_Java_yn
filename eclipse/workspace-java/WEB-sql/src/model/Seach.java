package model;

import java.io.Serializable;

public class Seach implements Serializable {
	private int id, age;
	private String name, address;
	private String columns;
	private String text;

	public Seach(int id, String name, int age, String address) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
	}


	public Seach(String columns,String text) {
		this.columns = columns;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getColumns() {
		return columns;
	}

	public String getText() {
		return text;
	}

}
