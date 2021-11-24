package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import model.AdressBook;
import model.GetAdressListLogic;
import model.Seach;

public class AddressDAO {
	int age, id;
	String name;
	String address;
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	// 「[^ -~｡-ﾟ]+」は「全角以上1回以上の繰り返し」
	Pattern namePattern = Pattern.compile("^[^ -~｡-ﾟ]+[　][^ -~｡-ﾟ]+$");

	// DBCドライバのロード&データベース接続
	public void driver() {
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root",
					"root");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException ex) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	// 全件取得
	public List<AdressBook> findAll() {
		List<AdressBook> adressBookList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try {
			driver();
			sql = "SELECT * FROM AddressBook";
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.id = rs.getInt("ID");
				this.name = rs.getString("NAME");
				this.age = rs.getInt("AGE");
				this.address = rs.getString("ADDRESS");
				AdressBook adressBook = new AdressBook(id, name, age, address);
				adressBookList.add(adressBook);
			}

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

	// 検索用
	public List<Seach> findByAdress(Seach seach) {
		// Seach seach = null;
		List<Seach> findseachList = new ArrayList<>();
		if (seach.getColumns().matches("id")) {
			try {
				driver();
				String sql = "SELECT * FROM AddressBook WHERE ID = ?";
				// SQL実行準備
				stmt = con.prepareStatement(sql);
				// ?に入力値を当てはめて、部分一致で検索
				stmt.setString(1, seach.getText());
				rs = stmt.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("ID");
					this.name = rs.getString("NAME");
					this.age = rs.getInt("AGE");
					this.address = rs.getString("ADDRESS");
					Seach seach1 = new Seach(id, name, age, address);
					findseachList.add(seach1);
				}
				con.close();

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				System.out.println(e);
				e.printStackTrace();
				this.id = 0000;
				this.name = null;
				this.age = -1;
				this.address = "error id データベースへのアクセスでエラーが発生しました";
				Seach seach1 = new Seach(id, name, age, address);
				findseachList.add(seach1);
				return findseachList;
			}
		} else if (seach.getColumns().matches("name")) {
			if (namePattern.matcher(seach.getText()).matches()) {
				try {
					driver();
					String sql = "SELECT * FROM AddressBook WHERE NAME = ?";
					// SQL実行準備
					stmt = con.prepareStatement(sql);
					// ?に入力値を当てはめて、部分一致で検索
					stmt.setString(1, seach.getText());
					rs = stmt.executeQuery();
					while (rs.next()) {
						this.id = rs.getInt("ID");
						this.name = rs.getString("NAME");
						this.age = rs.getInt("AGE");
						this.address = rs.getString("ADDRESS");
						Seach seach1 = new Seach(id, name, age, address);
						findseachList.add(seach1);
					}
					con.close();

				} catch (SQLException e) {
					System.out.println("データベースへのアクセスでエラーが発生しました。");
					System.out.println(e);
					e.printStackTrace();
				}
			} else {
				this.id = 0000;
				this.name = null;
				this.age = -1;
				this.address = "error name 全角で入力してください";
				Seach seach1 = new Seach(id, name, age, address);
				findseachList.add(seach1);
				return findseachList;
			}
		} else if (seach.getColumns().matches("age")) {
			try {
				driver();
				String sql = "SELECT * FROM AddressBook WHERE AGE = ?";
				// SQL実行準備
				stmt = con.prepareStatement(sql);
				// ?に入力値を当てはめて、部分一致で検索
				stmt.setString(1, seach.getText());
				rs = stmt.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("ID");
					this.name = rs.getString("NAME");
					this.age = rs.getInt("AGE");
					this.address = rs.getString("ADDRESS");
					Seach seach1 = new Seach(id, name, age, address);
					findseachList.add(seach1);
				}
				con.close();

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				System.out.println(e);
				e.printStackTrace();
				this.id = 0000;
				this.name = null;
				this.age = -1;
				this.address = "error age データベースへのアクセスでエラーが発生しました。";
				Seach seach1 = new Seach(id, name, age, address);
				findseachList.add(seach1);
				return findseachList;
			}
		} else {
			try {
				driver();
				String sql = "SELECT * FROM AddressBook WHERE ADDRESS like ?";
				// SQL実行準備
				stmt = con.prepareStatement(sql);
				// ?に入力値を当てはめて、部分一致で検索
				stmt.setString(1, "%" + seach.getText() + "%");
				rs = stmt.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("ID");
					this.name = rs.getString("NAME");
					this.age = rs.getInt("AGE");
					this.address = rs.getString("ADDRESS");
					Seach seach1 = new Seach(id, name, age, address);
					findseachList.add(seach1);
				}
				con.close();

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				System.out.println(e);
				e.printStackTrace();
				this.id = 0000;
				this.name = null;
				this.age = -1;
				this.address = "error address データベースへのアクセスでエラーが発生しました。";
				Seach seach1 = new Seach(id, name, age, address);
				findseachList.add(seach1);
				return findseachList;
			}
		}
		return findseachList;
	}

	// 新規作成
	public boolean create(AdressBook adressBook) {
		try {
			driver();
			// INSERT文の準備
			String sql = "INSERT INTO AddressBook (NAME,AGE,ADDRESS) VALUES(?,?,?)";
			stmt = con.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定
			stmt.setString(1, adressBook.getName());
			stmt.setInt(2, adressBook.getAge());
			stmt.setString(3, adressBook.getAddress());
			// INSERT文を実行
			int result = stmt.executeUpdate();

			if (result != 1) {
				return false;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// 削除
	public boolean remove(AdressBook adressBook) {
		// データベースへ接続
		try {
			driver();
			// DELETE文の準備
			String sql = "DELETE FROM AddressBook WHERE ID=?";
			stmt = con.prepareStatement(sql);
			// DELETE文中の「?」に使用する値を設定しSQLを完成
			stmt.setString(1, adressBook.getText());
			// DELETE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
			int result = stmt.executeUpdate();
			if (result == 1) {
				// 連番用
				GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
				List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
				for (int i = 0; i < AdressBookList.size(); i++) {
					sql = "UPDATE AddressBook SET ID=? WHERE ID=?";
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, i + 1);
					stmt.setInt(2, AdressBookList.get(i).getId());
					int update = stmt.executeUpdate();
					if (update != 1) {
						return false;
					}
				}
				sql = "ALTER TABLE AddressBook AUTO_INCREMENT = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, AdressBookList.size());
				stmt.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 更新
	public boolean update(AdressBook adressBook) {
		// データベースへ接続
		if (adressBook.getChoose().matches("id")) {
			try {
				driver();
				// UPDATE文の準備
				String sql = "UPDATE AddressBook SET ID=? WHERE ID=?";
				stmt = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				stmt.setString(1, adressBook.getAfter());
				stmt.setString(2, adressBook.getBefore());
				//stmt.setInt(3, adressBook.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = stmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else if (adressBook.getChoose().matches("name")) {
			if (namePattern.matcher(adressBook.getAfter()).matches()) {
				try {
					driver();
					// UPDATE文の準備
					String sql = "UPDATE AddressBook SET NAME=? WHERE NAME=? AND ID=?";
					stmt = con.prepareStatement(sql);
					// UPDATE文中の「?」に使用する値を設定しSQLを完成
					stmt.setString(1, adressBook.getAfter());
					stmt.setString(2, adressBook.getBefore());
					stmt.setInt(3, adressBook.getId());
					// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
					int result = stmt.executeUpdate();
					if (result != 1) {
						return false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
		} else if (adressBook.getChoose().matches("age")) {
			try {
				driver();
				// UPDATE文の準備
				String sql = "UPDATE AddressBook SET AGE=? WHERE AGE=? AND ID=?";
				stmt = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				stmt.setString(1, adressBook.getAfter());
				stmt.setString(2, adressBook.getBefore());
				stmt.setInt(3, adressBook.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = stmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				driver();
				// UPDATE文の準備
				String sql = "UPDATE AddressBook SET ADDRESS=? WHERE ADDRESS=? AND ID=?";
				stmt = con.prepareStatement(sql);
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				stmt.setString(1, adressBook.getAfter());
				stmt.setString(2, adressBook.getBefore());
				stmt.setInt(3, adressBook.getId());
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = stmt.executeUpdate();
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
