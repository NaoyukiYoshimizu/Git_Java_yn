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
				Syouhinn syouhinn = new Syouhinn(kanri_id,goods,goods_detail,selling_price,cost_price,stock,user_id);
				syouhinnList.add(syouhinn);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return syouhinnList;
	}
	// カートに入れる
	public  List<Syouhinn>  findByIncart(Syouhinn syouhinn) {
		List<Syouhinn> incartList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			sql = "SELECT * FROM SYOUHINN WHERE USER_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, syouhinn.getUser_id());
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
				syouhinn = new Syouhinn(kanri_id,goods,goods_detail,selling_price,cost_price,stock,user_id);
				incartList.add(syouhinn);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return incartList;
	}
	// 詳細
		public void detail(Syouhinn syouhinn) {
			// SQL文の作成
			String sql = "";
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				
				sql = "SELECT * FROM SYOUHINN WHERE KANRI_ID = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, syouhinn.getKanri_id());
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
				}

			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
				System.out.println(e);
				e.printStackTrace();
			}
		}
	//削除
	public boolean delete(Syouhinn syouhinn) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		// DELETE文の準備
		String sql = "DELETE FROM SYOUHINN WHERE KANRI_ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		// DELETE文中の「?」に使用する値を設定しSQLを完成
		ps.setLong(1, syouhinn.getKanri_id());
		// DELETE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
		int result = ps.executeUpdate();
		if (result == 1) {
			return true;
		}else 
			return false;
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}
	// 新規作成
	public boolean create(Syouhinn syouhinn) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// データベースから商品情報を呼び出す
			String sql = "SELECT * FROM SYOUHINN WHERE KANRI_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, syouhinn.getKanri_id());
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.kanri_id = rs.getLong("kanri_id");
				this.goods = rs.getString("goods");
				this.goods_detail = rs.getString("");
				this.selling_price = rs.getInt("selling_price");
				this.cost_price = rs.getInt("cost_price");
				this.stock = rs.getInt("stock");
				this.user_id = rs.getLong("user_id");
			}
			
			// INSERT文の準備
			sql = "INSERT INTO SYOUHINN (goods,selling_price,cost_price,stock,goods_detail,user_id) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			/*
			ps.setLong(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPass());
			ps.setInt(4, user.getAuthority());
	*/
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
}
