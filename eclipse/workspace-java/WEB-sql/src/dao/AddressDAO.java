package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AdressBook;

public class AddressDAO {
	int age, id;
	String name;
	String address;
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public List<AdressBook> findAll() {
		List<AdressBook> adressBookList = new ArrayList<>();

		// SQL文の作成
		String sql = "";

		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root",
					"root");
			sql = "SELECT * FROM AddressBook";
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.id = rs.getInt("ID");
				this.name = rs.getString("name");
				this.age = rs.getInt("age");
				this.address = rs.getString("address");
				AdressBook adressBook = new AdressBook(id, name, age, address);
				adressBookList.add(adressBook);

			}

		} catch (ClassNotFoundException ex) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}
		return adressBookList;
	}

	public boolean select(AdressBook adressBook) {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root",
					"root");
			String sql = "select * from AddressBook where ? like ?";
			// SQL実行準備
			stmt = con.prepareStatement(sql);

			// ?に入力値を当てはめて、部分一致で検索
			stmt.setString(1, adressBook.getSeach());
			stmt.setString(2, adressBook.getText());
			rs = stmt.executeQuery();
			if (rs.next() == false) {
				return false;
			}

			con.close();

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
