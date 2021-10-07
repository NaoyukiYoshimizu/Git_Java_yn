package model;

import java.io.Serializable;

public class AdressBook implements Serializable {
	private int id, age;
	private String name, address;
	private String seach;
	private String text;
	
	public AdressBook() {
		
	}
	public AdressBook(int id, String name, int age, String address) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
	}

	public AdressBook(int id, String name, int age, String address, String seach) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
		this.seach = seach;
	}

	public AdressBook(String text, String seach) {
		this.text = text;
		this.seach = seach;
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

	public String getSeach() {
		return seach;
	}

	public String getText() {
		return text;
	}

}
