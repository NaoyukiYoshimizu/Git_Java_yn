package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MysqlTest { 
	public static void main(String[] args) {
		// 変数の準備
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// SQL文の作成
		String sql = "show tables";

		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root",
					"root");
			//SQL実行　table作成			
			sql = "create table AddressBook(name varchar(7),age int,address varchar(100))";
			// SQL実行準備
			stmt = con.prepareStatement(sql);
			//SQL実行　addressbookにデータを入れる
			con.createStatement().executeUpdate(sql);
			sql = "insert into addressbook values (\'山田　花子\',47,\'大阪府堺市北区○○町1-2-\')";
			con.createStatement().executeUpdate(sql);
			sql = "insert into addressbook values (\'大阪　太郎\',71,\'大阪府大阪市中央区道頓堀１丁目８?２５')";
			con.createStatement().executeUpdate(sql);
			sql = "insert into addressbook values (\'唐木　崇行\',60,\'大阪府茨木市□□町4-5-6')";
			con.createStatement().executeUpdate(sql);
			sql = "SELECT * FROM AddressBook";
			// SQL実行準備
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			System.out.println("　名前　　|年|住所");
			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");

				System.out.println(name + "|" + age+ "|" +address);
			}
			System.out.println("データを消しますか？Y/N");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			if(name.equals("Y")) {
				sql = "drop table AddressBook";
				con.createStatement().executeUpdate(sql);
			}
			
		} catch (ClassNotFoundException e) {
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
}
