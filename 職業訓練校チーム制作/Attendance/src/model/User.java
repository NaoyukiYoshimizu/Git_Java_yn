package model;

//ユーザーの情報(アカウント)

import java.io.Serializable;

public class User implements Serializable {
	private int id; // ID
	private String name; // ユーザー名
	private int pass; // パスワード
	private int authority; // 権限レベル
	private String choose; // 更新項目
	private String after; // 更新データ

	public User() {
	}

	public User(int id, String name, int pass, int authority) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.authority = authority;
	}
	

	public User(int id, String choose, String after) {
		this.id = id;
		this.choose = choose;
		this.after = after;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPass() {
		return pass;
	}

	public int getAuthority() {
		return authority;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}
	
}