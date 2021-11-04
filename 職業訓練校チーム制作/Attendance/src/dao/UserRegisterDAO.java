package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserRegisterDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/Attendance?characterEncoding=UTF8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	int id;
	int pass;
	String name;
	int authority;

	// ユーザー全件取得
	public List<User> findByAll() {
		List<User> userList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT * FROM USER";
			PreparedStatement ps = con.prepareStatement(sql);
			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.id = rs.getInt("id");
				this.name = rs.getString("name");
				this.pass = rs.getInt("pass");
				this.authority = rs.getInt("authority");
				User user = new User(id, name, pass, authority);
				userList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return userList;
	}

	// アカウントを探す
	public void findByUser(User user) {
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT * FROM USER WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getInt("pass"));
				user.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// 新規作成
	public boolean UserRegister(User user) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "SELECT * FROM USER";
			PreparedStatement ps = con.prepareStatement(sql);

			// SELECTを実行
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (user.getId() == rs.getInt("ID")) {
					return false;
				}
			}

			sql = "INSERT INTO USER (id, name, pass,authority) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getPass());
			ps.setInt(4, user.getAuthority());

			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 更新
	public boolean update(User user) {
		// SQL文の作成
		String sql = "";
		if (user.getChoose().matches("id")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				sql = "UPDATE USER SET ID=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				ps.setString(1, user.getAfter());
				ps.setInt(2, user.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = ps.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else if (user.getChoose().matches("name")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				sql = "UPDATE USER SET NAME=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				ps.setString(1, user.getAfter());
				ps.setInt(2, user.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = ps.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else if (user.getChoose().matches("pass")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				sql = "UPDATE USER SET PASS=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				ps.setString(1, user.getAfter());
				ps.setInt(2, user.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = ps.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				int authority = 1;
				sql = "UPDATE USER SET AUTHORITY=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				if (user.getAfter().matches(".*あり.*")) {
					authority = 2;
				}
				ps.setInt(1, authority);
				ps.setInt(2, user.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = ps.executeUpdate();
				if (result != 1) {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
