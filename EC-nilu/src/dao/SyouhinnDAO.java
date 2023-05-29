package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Syouhinn;

public class SyouhinnDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shop?characterEncoding=UTF8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "1Root2";
	long user_id,kanri_id;
	String goods,goods_detail;
	int selling_price,cost_price,stock;
	

	// ユーザー全件取得
	public List<Syouhinn> findByAll() {
		List<Syouhinn> syouhinnList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT * FROM SYOUHINN WHERE USER_ID = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.kanri_id = rs.getLong("kanri_id");
				this.goods = rs.getString("goods");
				this.goods_detail = rs.getString("goods_detail");
				this.selling_price = rs.getInt("selling_price");
				this.cost_price = rs.getInt("cost_price");
				this.stock = rs.getInt("stock");
				this.user_id = rs.getLong("user_id");
				Syouhinn syouhinn = new Syouhinn(user_id,goods,goods_detail,selling_price,cost_price,stock,user_id);
				syouhinnList.add(syouhinn);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return syouhinnList;
	}
}
