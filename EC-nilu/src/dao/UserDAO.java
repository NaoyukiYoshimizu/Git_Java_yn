package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginUser;
import model.User;

public class UserDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shop";
	private final String DB_USER = "root";
	private final String DB_PASS = "1Root2";

	// ログインアカウントを探す
	public User findByLogin(LoginUser loginUser) {
		User user = new User();

		// データベース接続に使用する情報
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM user WHERE ID = ? AND PASS = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setLong(1, loginUser.getId());
			ps.setString(2, loginUser.getPass());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// 見つかったアカウント情報を戻り値にセット
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setAuthority(rs.getInt("authority"));
			} else {
				// アカウントがなければnullを返す
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
