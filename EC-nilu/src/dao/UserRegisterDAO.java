package dao;
import java.util.ArrayList;
import java.util.List;

import model.User;
public class UserRegisterDAO {

	// ユーザー全件取得
	public List<User> findByAll() {
		List<User> userList = new ArrayList<>();
		return userList;
	}
	// アカウントを探す
	public void findByUser(User user) {
			
	}
	// 新規作成
	public boolean UserRegister(User user) {
		return true;	
	}
	// 更新
	public boolean update(User user) {
		return true;	
	}
	
}
