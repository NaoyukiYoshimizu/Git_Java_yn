package model;

import java.io.Serializable;

public class AdressBook implements Serializable {
	private int id, age;
	private String name, address;
	private String columns;
	private String text;
	private String before;
	private String after;
	private String choose;
	
	public AdressBook() {
		
	}
	public AdressBook(int id, String name, int age, String address) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
	}

	public AdressBook(int id, String name, int age, String address, String columns) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
		this.columns = columns;
	}
	
	public AdressBook(String name, int age, String address) {
		this.age = age;
		this.name = name;
		this.address = address;
	}

	public AdressBook(String columns,String text) {
		this.text = text;
		this.columns = columns;
	}

	public AdressBook(String before, String after, String choose) {
		this.before = before;
		this.after = after;
		this.choose = choose;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getBefore() {
		return before;
	}

	public String getAfter() {
		return after;
	}

	public String getChoose() {
		return choose;
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
