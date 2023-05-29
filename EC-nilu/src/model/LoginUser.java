package model;

public class LoginUser {
	// ログイン用

	private long id; // ID
	private String pass; // パスワード
	
	public LoginUser(long id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
