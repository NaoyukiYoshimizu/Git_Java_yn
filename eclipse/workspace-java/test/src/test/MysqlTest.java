package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MysqlTest {
	public static void main(String[] args) {
		Setsql set = new Setsql();
		Scanner sc = new Scanner(System.in);
		System.out.println("初期データを入れますか？Y/N");
		String s = sc.nextLine();
		if (s.equals("Y")) {
			if (set.init()) {
				System.out.println("初期データ作成しました");
			} else {
				System.out.println("エラー終了しました");
				System.exit(0);
			}
		} else
			set.allselect();

		System.out.println("データを入れますか？Y/N");
		s = sc.nextLine();
		if (s.equals("Y")) {
			set.insert();
			System.out.println("処理しました");
		}
		System.out.println("データを更新しますか？Y/N");
		s = sc.nextLine();
		if (s.equals("Y")) {
			set.update();
			System.out.println("処理しました");
		}
		System.out.println("データを検索しますか？Y/N");
		s = sc.nextLine();
		if (s.equals("Y")) {
			set.seach();
			System.out.println("処理しました");
		}
		System.out.println("データを削除しますか？Y/N");
		s = sc.nextLine();
		if (s.equals("Y")) {
			set.drop();
			System.out.println("処理しました");
		}		

	}

}

class Setsql {
	// 変数の準備
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	// SQL文の作成
	String sql = "show tables";
	int i = 0;
	int[] age = new int[8];
	String[] name = new String[8];
	String[] address = new String[8];

	public void driver() {
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root", "root");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException ex) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	public boolean init() {
		try {
			driver();
			// SQL実行 table作成
			this.sql = "create table AddressBook(name varchar(7),age int,address varchar(100))";
			// SQL実行
			stmt = con.prepareStatement(this.sql);
			con.createStatement().executeUpdate(this.sql);
			// SQL実行 addressbookにデータを入れる
			this.sql = "insert into AddressBook values (\'山田　花子\',47,\'大阪府堺市北区○○町1-2-\')";
			con.createStatement().executeUpdate(this.sql);
			this.sql = "insert into AddressBook values (\'大阪　太郎\',71,\'大阪府大阪市中央区道頓堀１丁目８?２５')";
			con.createStatement().executeUpdate(this.sql);
			this.sql = "insert into AddressBook values (\'唐木　崇行\',60,\'茨城県茨木市□□町4-5-6')";
			con.createStatement().executeUpdate(this.sql);
			this.sql = "select * from AddressBook";
			// SQL実行準備
			stmt = con.prepareStatement(this.sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			System.out.println("　名前　　|年|住所");
			while (rs.next()) {
				name[i] = rs.getString("name");
				age[i] = rs.getInt("age");
				address[i] = rs.getString("address");

				System.out.println(name[i] + "|" + age[i] + "|" + address[i]);
				i++;
			}
			return true;

		} catch (SQLException ex) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(ex);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (con != null) {
					stmt.close();
					// con.close();
				}

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				return false;
			}
		}
	}

	public void allselect() {
		int i = 0;
		try {
			driver();
			this.sql = "select * from AddressBook";
			// SQL実行準備
			stmt = con.prepareStatement(this.sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			System.out.println("　名前　　|年|住所");
			while (rs.next()) {
				name[i] = rs.getString("name");
				age[i] = rs.getInt("age");
				address[i] = rs.getString("address");

				System.out.println(name[i] + "|" + age[i] + "|" + address[i]);
				i++;
			}
		} catch (SQLException ex) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			try {
				if (con != null) {
					stmt.close();
					// con.close();
				}

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}
	}

	public void insert() {
		String name = "";
		int age = 0;
		String address = "";
		while (true) {
			String msg = "";
			System.out.println("挿入する名前を入力");
			name = sc.nextLine();
			if (name.matches("[0-9]{1,}")) {
				msg += "無効な名前";
			} else if (name.matches("end")) {
				System.out.println("挿入をやめます");
				return;
			}
			System.out.println("挿入する年齢を入力");
			String ageString = sc.nextLine();
			if (ageString.matches("[a-zA-Z]{1,}")) {
				msg += "無効な年齢";
			} else {
				age = Integer.parseInt(ageString);
				System.out.println("挿入する住所を入力");
				address = sc.nextLine();
			}
			if (msg == null || msg.length() == 0) {
				System.out.println("処理します");
				break;
			}
			System.out.println(msg);
		}
		try {
			// SQL実行 addressbookにデータを入れる
			this.sql = "insert into AddressBook values (?,?,?)";
			// SQL実行準備
			stmt = con.prepareStatement(this.sql);

			// ?に入力値を当てはめ
			stmt.setString(1, name);
			stmt.setInt(2, age);
			stmt.setString(3, address);
			stmt.executeUpdate();
			allselect();

		} catch (SQLException ex) {
			System.out.println("エラーが発生しました。");
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			try {
				if (con != null) {
					stmt.close();
					// con.close();
				}
			} catch (SQLException e) {
				System.out.println("エラーが発生しました。");
			}
		}
	}

	public void update() {
		String s;
		int j = 0;
		String name;
		int age;
		String address;
		boolean b = true;
		System.out.println("どこを更新しますか？name/age/address いずれか");
		s = sc.nextLine();
		if (s.equals("name")) {
			while (b) {
				System.out.println("どの名前ですか？");
				name = sc.nextLine();
				for (int i = 0; i < this.name.length; i++) {
					System.out.println(this.name[i]);
					if (name.equals(this.name[i])) {
						i = j;
						b = false;
						break;
					}
				}
				if (!name.equals(this.name[j]))
					System.out.println("不一致な名前");
			}
			this.sql = "update AddressBook set name = ? where name = ?";

			System.out.println("処理します");
			try {
				// SQL実行準備
				stmt = con.prepareStatement(this.sql);
				System.out.println("名前を入力");
				name = sc.nextLine();
				stmt.setString(1, name);
				stmt.setString(2, this.name[j]);
				stmt.executeUpdate();
				allselect();
			} catch (SQLException ex) {
				System.out.println("エラーが発生しました。");
				System.out.println(ex);
				ex.printStackTrace();
			} finally {
				try {
					if (con != null) {
						stmt.close();
						// con.close();
					}
				} catch (SQLException e) {
					System.out.println("エラーが発生しました。");
				}
			}
		} else if (s.equals("age")) {
			while (b) {
				System.out.println("どの年齢ですか？");
				age = sc.nextInt();
				for (int i = 0; i < this.age.length; i++) {
					System.out.println(this.age[i]);
					if (age == this.age[i]) {
						i = j;
						b = false;
						break;
					}
				}
				if (age != this.age[j])
					System.out.println("不一致な名前");
			}
			this.sql = "update AddressBook set age = ? where age = ?";

			System.out.println("処理します");
			try {
				// SQL実行準備
				stmt = con.prepareStatement(this.sql);
				System.out.println("年齢を入力");
				age = sc.nextInt();
				stmt.setInt(1, age);
				stmt.setInt(2, this.age[j]);
				stmt.executeUpdate();
				allselect();
			} catch (SQLException ex) {
				System.out.println("エラーが発生しました。");
				System.out.println(ex);
				ex.printStackTrace();
			} finally {
				try {
					if (con != null) {
						stmt.close();
						// con.close();
					}
				} catch (SQLException e) {
					System.out.println("エラーが発生しました。");
				}
			}
		}else if (s.equals("address")) {
			while (b) {
				System.out.println("どの名前ですか？");
				address = sc.nextLine();
				for (int i = 0; i < this.address.length; i++) {
					System.out.println(this.address[i]);
					if (address.equals(this.address[i])) {
						i = j;
						b = false;
						break;
					}
				}
				if (!address.equals(this.address[j]))
					System.out.println("不一致な名前");
			}
			this.sql = "update AddressBook set name = ? where name = ?";

			System.out.println("処理します");
			try {
				// SQL実行準備
				stmt = con.prepareStatement(this.sql);
				System.out.println("名前を入力");
				address = sc.nextLine();
				stmt.setString(1, address);
				stmt.setString(2, this.address[j]);
				stmt.executeUpdate();
				allselect();
			} catch (SQLException ex) {
				System.out.println("エラーが発生しました。");
				System.out.println(ex);
				ex.printStackTrace();
			} finally {
				try {
					if (con != null) {
						stmt.close();
						// con.close();
					}
				} catch (SQLException e) {
					System.out.println("エラーが発生しました。");
				}
			}
		}else
			System.out.println("何もしません");
	}

	public void seach() {
		boolean b = false;
		System.out.println("住所を検索しますか？Y/N");
		String s = this.sc.nextLine();
		if (s.equals("Y")) {
			try {

				// ?に変数を入れる
				System.out.println("検索する住所を入力");
				s = this.sc.nextLine();
				this.sql = "select * from AddressBook where address like ?";
				// SQL実行準備
				stmt = con.prepareStatement(this.sql);

				// 1つ目の?に入力値を当てはめて、部分一致で検索
				stmt.setString(1, "%" + s + "%");
				System.out.println(s + "が入力されました");
				rs = stmt.executeQuery();

				// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
				System.out.println("　名前　　|年|住所");
				while (rs.next()) {
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String address = rs.getString("address");

					System.out.println(name + "|" + age + "|" + address);
					b = true;
				}
				// データがないとき
				if (!b)
					System.out.println(s + "を含む住所はありません");

			} catch (SQLException ex) {
				System.out.println("エラーが発生しました。");
				System.out.println(ex);
				ex.printStackTrace();
			} finally {
				try {
					if (con != null) {
						stmt.close();
						// con.close();
					}
				} catch (SQLException e) {
					System.out.println("エラーが発生しました。");
				}
			}
		}
	}

	public void drop() {
		String s;
		// データ削除
		System.out.println("全データを消しますか？Y/N");
		s = sc.nextLine();
		if (s.equals("Y")) {
			try {
				System.out.println("テーブルごと消しますか？Y/N");
				s = sc.nextLine();
				if (s.equals("Y")) {
					sql = "drop table AddressBook";
					con.createStatement().executeUpdate(sql);
				} else {
					sql = "delete from AddressBook";
					con.createStatement().executeUpdate(sql);
				}

			} catch (SQLException ex) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				System.out.println(ex);
				ex.printStackTrace();
			} finally {
				try {
					if (con != null) {
						stmt.close();
						con.close();
					}
				} catch (SQLException e) {
					System.out.println("データベースへのアクセスでエラーが発生しました。");

				}
			}
		}
	}
}