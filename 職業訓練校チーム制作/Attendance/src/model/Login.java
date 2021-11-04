package model;

public class Login {

	// ログイン用

	private int id; // ID
	private int pass; // パスワード

	public Login(int id, int pass) {

		this.id = id;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

}
