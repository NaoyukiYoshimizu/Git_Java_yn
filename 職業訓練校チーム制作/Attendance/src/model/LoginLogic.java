package model;

import dao.UserDAO;

public class LoginLogic {
	public boolean execute(LoginUser loginUser) {
		UserDAO dao = new UserDAO();
		User user = dao.findByLogin(loginUser);
		return user != null;

	}

}
