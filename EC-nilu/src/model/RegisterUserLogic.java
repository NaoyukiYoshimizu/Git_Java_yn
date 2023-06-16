package model;
import java.util.List;

import dao.UserRegisterDAO;

public class RegisterUserLogic {
	public List<User> execute() {
		UserRegisterDAO dao = new UserRegisterDAO();
		List<User> userList = dao.findByAll();
		return userList;
	}

	public void edit(User user) {
		UserRegisterDAO dao = new UserRegisterDAO();
		dao.findByUser(user);
	}

	public boolean create(User user) {
		UserRegisterDAO dao = new UserRegisterDAO();
		if (dao.create(user)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean update(User user) {
		UserRegisterDAO dao = new UserRegisterDAO();
		if (dao.update(user)) {
			return true;
		} else {
			return false;
		}
	}
}
