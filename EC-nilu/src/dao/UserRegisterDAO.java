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
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shop";
	private final String DB_USER = "root";
	private final String DB_PASS = "1Root2";
	
	// ユーザー全件取得
	public List<User> findByAll() {
		List<User> userList = new ArrayList<>();
		
		return userList;
	}
	// アカウントを探す
	public void findByUser(User user) {
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT * FROM user WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, user.getId());
			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				// 見つかったアカウント情報を戻り値にセット
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setMail(rs.getString("mail"));
				user.setAddres(rs.getString("address"));
				user.setCredit(rs.getString("credit"));
				user.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}	
	}
	// 新規作成
	public boolean create(User user) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備
			String sql = "INSERT INTO user (NAME,PASS,MAIL,ADDRESS) VALUES(?, ?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定
			ps.setString(1, user.getName());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getAddres());

			// INSERT文を実行
			int result = ps.executeUpdate();

			if (result != 1) {
				return false;
			}else {
				sql = "SELECT * FROM user WHERE NAME = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user.getName());
				// 実行結果取得
				ResultSet rs = ps.executeQuery();
				// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
				while (rs.next()) {
					// 見つかったアカウント情報を戻り値にセット
					user.setId(rs.getLong("id"));
				}
			}
			con.close();
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
		if (user.getChoose().matches("name")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				sql = "UPDATE user SET NAME=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				ps.setString(1, user.getAfter());
				ps.setLong(2, user.getId());
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
						sql = "UPDATE user SET PASS=? WHERE ID=?";
						PreparedStatement ps = con.prepareStatement(sql);
						// UPDATE文中の「?」に使用する値を設定しSQLを完成
						ps.setString(1, user.getAfter());
						ps.setLong(2, user.getId());
						// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
						int result = ps.executeUpdate();
						if (result != 1) {
							return false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
		} else if (user.getChoose().matches("mail")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
						// UPDATE文の準備
						sql = "UPDATE user SET MAIL=? WHERE ID=?";
						PreparedStatement ps = con.prepareStatement(sql);
						// UPDATE文中の「?」に使用する値を設定しSQLを完成
						ps.setString(1, user.getAfter());
						ps.setLong(2, user.getId());
						// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
						int result = ps.executeUpdate();
						if (result != 1) {
							return false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
		} else if (user.getChoose().matches("addres")) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
						// UPDATE文の準備
						sql = "UPDATE user SET ADDRESS=? WHERE ID=?";
						PreparedStatement ps = con.prepareStatement(sql);
						// UPDATE文中の「?」に使用する値を設定しSQLを完成
						ps.setString(1, user.getAfter());
						ps.setLong(2, user.getId());
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
				sql = "UPDATE user SET AUTHORITY=? WHERE ID=?";
				PreparedStatement ps = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				if (user.getAfter().matches(".*あり.*")) {
					authority = 2;
				}
				ps.setInt(1, authority);
				ps.setLong(2, user.getId());
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
