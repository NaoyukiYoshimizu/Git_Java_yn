package servret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sqlsample00 {
	int i = 0;
	int[] age = new int[10];
	String[] name = new String[10];
	String[] address = new String[10];

	public void main() {
		// 変数の準備
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// SQL文の作成
		String sql = "SELECT * FROM test";

		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root", 
					"root");
			sql = "insert into addressbook values (\'山田　花子\',47,\'大阪府堺市北区○○町1-2-\')";
			con.createStatement().executeUpdate(sql);
			sql = "insert into addressbook values (\'大阪　太郎\',71,\'大阪府大阪市中央区道頓堀１丁目８?２５')";
			con.createStatement().executeUpdate(sql);
			sql = "insert into addressbook values (\'唐木　崇行\',60,\'大阪府茨木市□□町4-5-6')";
			con.createStatement().executeUpdate(sql);
			sql = "SELECT * FROM AddressBook";
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			System.out.println("　名前　　|年|住所");
			while (rs.next()) {
				this.name[i] = rs.getString("name");
				this.age[i] = rs.getInt("age");
				this.address[i] = rs.getString("address");

				System.out.println(name[i] + "|" + age[i] + "|" + address[i]);
				i++;
			}
			System.out.println("データを消しますか？Y/N");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s.equals("Y")) {
				sql = "delete from AddressBook";
				con.createStatement().executeUpdate(sql);
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
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}
	}

	public String[] setName() {
		String[] msg = new String[8];
		for (i = 0; i < this.name.length; i++)
			msg[i] = this.name[i];
		return msg;
	}

	public int[] setAge() {
		int[] msg = new int[8];
		for (i = 0; i < this.name.length; i++)
			msg[i] = this.age[i];
		return msg;
	}

	public String[] setAddress() {
		String[] msg = new String[8];
		for (i = 0; i < this.name.length; i++)
			msg[i] = this.address[i];
		return msg;
	}

}
