package model;

import java.io.Serializable;

public class Users implements Serializable {
	private String name; // ユーザー名
	private String pass; // パスワード

	public Users() {
	}

	public Users(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
}