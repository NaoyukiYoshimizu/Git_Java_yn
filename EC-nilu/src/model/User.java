package model;

import java.io.Serializable;

public class User implements Serializable{
	private long id; // ID
	private String name; // ユーザー名
	private String pass; // パスワード
	private String mail;
	private String addres;
	private int authority; // 権限レベル
	private String choose; // 更新項目
	private String after; // 更新データ

	public User() {
	}

	public User(long id, String name, String pass, String mail, String addres, int authority) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.addres = addres;
		this.authority = authority;
	}

	public User(long id, String choose, String after) {
		this.id = id;
		this.choose = choose;
		this.after = after;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public int getAuthority() {
		return authority;
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
